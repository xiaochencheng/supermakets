package cn.smbms.controller;

import java.io.File;
import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;

import cn.smbms.pojo.Role;
import cn.smbms.pojo.UploadFile;
import cn.smbms.pojo.User;
import cn.smbms.service.UserService;
import cn.smbms.tools.Constants;
import cn.smbms.tools.PageSupport;

@Controller
@RequestMapping("/user")
public class LoginController {

	Logger logger = Logger.getLogger(LoginController.class);

	@Autowired
	UserService userService;

	@RequestMapping("/error")
	public String error() {
		logger.info("error>>>>>>>>>>>>>>>>>>>>");
		return "error";
	}

	/**
	 * 登录
	 * @param userCode
	 * @param userPassword
	 * @param request
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/dologin.html")
	public String doLogin(@RequestParam(required=false) String userCode,
			@RequestParam(required=false) String userPassword, HttpServletRequest request,
			HttpSession session) throws Exception {
		logger.debug("doLogin====================================");
		logger.debug("userCode====================================" + userCode);
		logger.debug("userPassword===================================="+ userPassword);
		User user=new User();
		user.setUserCode(userCode);
		user.setUserPassword(userPassword);
		user.setId(user.getId());
		// 调用service方法，进行用户匹配
		List<User> list= userService.getLogin(user);
		for (int i = 0; i < list.size(); i++) {
				user=list.get(i);
		}
		if (list.size()>0) {// 登录成功
			// 放入session
			// 页面跳转（frame.jsp）
			session.setAttribute("user", user);
			return "redirect:/user/sys/main.html";
		} else {
			// 页面跳转（login.jsp）带出提示信息--转发
			request.setAttribute("error", "用户名或密码不正确");
			return "login";
		}
	}

	/**
	 * 跳转到主显示页面
	 * @return
	 */
	@RequestMapping(value = "/sys/main.html")
	public String main() {
		return "frame";
	}
	
	@RequestMapping(value = "/list.html")
	public String getUserList(
			Model model,
			@RequestParam(value = "queryname", required = false) String queryUserName,
			@RequestParam(value = "queryUserRole", required = false) Integer queryUserRole,
			@RequestParam(value = "pageIndex", required = false) String pageIndex) {
		logger.info("getUserList ---- > queryUserName: " + queryUserName);
		logger.info("getUserList ---- > queryUserRole: " + queryUserRole);
		logger.info("getUserList ---- > pageIndex: " + pageIndex);
		Integer _queryUserRole = null;
		List<User> userList = null;
		// List<Role> roleList = null;
		// 设置页面容量
		int pageSize = Constants.pageSize;
		// 当前页码
		int currentPageNo = 1;
		
		//显示用户角色
		List<Role> roles=userService.getRole();
		model.addAttribute("roleList", roles);

		if (queryUserName == null) {
			queryUserName = "";
		}
		if (queryUserRole != null && !queryUserRole.equals("")) {
			_queryUserRole = queryUserRole;
		}

		if (pageIndex != null) {
			try {
				currentPageNo = Integer.valueOf(pageIndex);
			} catch (NumberFormatException e) {
				return "redirect:/syserror.html";
			}
		}
		
		// 总数量（表）
		int totalCount = 0;
		try {
			totalCount = userService
					.getUserCount(queryUserName, _queryUserRole);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 总页数
		PageSupport pages = new PageSupport();
		pages.setCurrentPageNo(currentPageNo);
		pages.setPageSize(pageSize);
		pages.setTotalCount(totalCount);
		int totalPageCount = pages.getTotalPageCount();
		// 控制首页和尾页
		if (currentPageNo < 1) {
			currentPageNo = 1;
		} else if (currentPageNo > totalPageCount) {
			currentPageNo = totalPageCount;
		}
		try {
			userList = userService.getUserList(queryUserName, _queryUserRole,
					currentPageNo, pageSize);

			// roleList = roleService.getRoleList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("userList", userList);
		// model.addAttribute("roleList", roleList);
		model.addAttribute("queryUserName", queryUserName);
		model.addAttribute("queryUserRole", queryUserRole);
		model.addAttribute("totalPageCount", totalPageCount);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("currentPageNo", currentPageNo);
//		List<User> list=userService.getUserList(queryUserName, queryUserRole, currentPageNo, pageSize);
		return "userlist";
	}
	
	/**
	 * 跳转到添加页面
	 * @return
	 */
	@RequestMapping("/useradd")
	public String useradd(Model model) {
		System.out.println("useradd>>>>>>>>>>>>>>>>>>>>>>>>>>");
		//显示用户角色
		List<Role> roles=userService.getRole();
		logger.debug(roles.size()+">>>>>>>>>>>>>");
	    model.addAttribute("roleList", roles);
		return "useradd";
	}
	
	/**
	 * 添加的方法
	 * @param user
	 * @param request
	 * @param attachs
	 * @return
	 */
	@RequestMapping("/useraddsave")
	public String useraddsave(
			User user,UploadFile file,
			HttpServletRequest request,
			Model model,
			@RequestParam(value = "attachs", required = false) MultipartFile[] attachs) {
		String idPicPath = null;
		String workPicPath = null;
		String errorInfo = null;
		boolean flag = true;
		
		
		
		// 存储路径，源代码中没有该路径。只能在tomcat发布目录才会自动创建该目录
		// 例如：D:\\apache-tomcat-7.0.54\\webapps\\SMBMS_C11_07\\statics\\uploadfiles
		String path = request.getSession().getServletContext()
				.getRealPath("statics" + File.separator + "uploadfiles");
		logger.info("uploadFile path ============== > " + path);
		for (int i = 0; i < attachs.length; i++) {
			MultipartFile attach = attachs[i];
			if (!attach.isEmpty()) {
				if (i == 0) {
					errorInfo = "uploadFileError";
				} else if (i == 1) {
					errorInfo = "uploadWpError";
				}
				String oldFileName = attach.getOriginalFilename();// 原文件名
				logger.info("uploadFile oldFileName ============== > "
						+ oldFileName);
				file.setRealfilename(oldFileName);
				String prefix = FilenameUtils.getExtension(oldFileName);// 原文件后缀
				logger.debug("uploadFile prefix============> " + prefix);
				int filesize = 500000;
				logger.debug("uploadFile size============> " + attach.getSize());
				file.setFilesize(attach.getSize());
				if (attach.getSize() > filesize) {// 上传大小不得超过 500k
					request.setAttribute(errorInfo, " * 上传大小不得超过 500k");
					flag = false;
				} else if (prefix.equalsIgnoreCase("jpg")
						|| prefix.equalsIgnoreCase("png")
						|| prefix.equalsIgnoreCase("jpeg")
						|| prefix.equalsIgnoreCase("pneg")) {// 上传图片格式不正确
					String fileName = System.currentTimeMillis()
							+ "_Personal.jpg";
					logger.debug("new fileName======== " + attach.getName());
					File targetFile = new File(path, fileName);
					if (!targetFile.exists()) {
						targetFile.mkdirs();
					}
					// 保存
					try {
						attach.transferTo(targetFile);
					} catch (Exception e) {
						e.printStackTrace();
						request.setAttribute(errorInfo, " * 上传失败！");
						flag = false;
					}
					if (i == 0) {
						idPicPath = fileName;
					} else if (i == 1) {
						workPicPath = fileName;
					}
					logger.debug("idPicPath: " + idPicPath);
					logger.debug("workPicPath: " + workPicPath);
				} else {
					request.setAttribute(errorInfo, " * 上传图片格式不正确");
					flag = false;
				}
			}
		}
		if (flag) {
			user.setIdPicPath(idPicPath);
			user.setWorkPicPath(workPicPath);
			if (userService.useradd(user) > 0) {
				userService.pathLoadAdd(file);
				return "/userlist";
			}
		}
		return "useraddsave";
	}
	
	/**
	 * 查看
	 */
	@RequestMapping("/showJsp")
	public String showJsp(Integer id,Model model) {
		System.out.println("userView>>>>>>>>>>>>>>>>>>>>>>>>>>");
		User user=userService.getUserImg(id);
		model.addAttribute("user", user);
		return "userView";
	}
	
	/**
	 * 跳入修改页面
	 */
	@RequestMapping("/updateJsp")
	public String updateJsp(@RequestParam(value = "id", required = false)Integer id,Model model) {
		User user=userService.getUserImg(id);
		model.addAttribute("user", user);
		return "userUpdate";
	}
	
	/**
	 * 修改方法
	 */
     @RequestMapping("/updates")
	//userCode,userName,gender,birthday,phone,address,userPassword,idPicPath,userRole,idPicPath,workPicPath
	public String udates(@RequestParam(value = "id", required = false) int id,
			@RequestParam(value="userName",required=false)String userName,
			@RequestParam(value="gender",required=false)String gender,
			@RequestParam(value="birthday",required=false)String birthday,
			@RequestParam(value="phone",required=false)String phone,
			@RequestParam(value="address",required=false)String address,
			Model model){
    	// System.out.println("OK><!");
    	 logger.debug("OK>>>>>>>>>>>>>>>>>>>>>>>>>>!");
    	User user = new User();
 		user.setId(id);
 		user.setUserName(userName);
 		user.setGender(Integer.valueOf(gender));
 		SimpleDateFormat dateFormat =new SimpleDateFormat("yyyy-MM-dd");
 		Date date=null;
 		try {
			date=dateFormat.parse(birthday);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
 	    user.setBirthday(date);
 		user.setPhone(phone);
 		user.setAddress(address);
 		model.addAttribute("id", id);
 		model.addAttribute("userName", userName);
 		model.addAttribute("gender", gender);
 		model.addAttribute("birthday", birthday);
 		model.addAttribute("phone", phone);
 		model.addAttribute("address", address);
		int count=userService.getUpdateUser(user);
		if(count>0){
			System.out.println("woo!");
			return "/userlist";
		}
		return "error";
	}
     
     /**
 	 * 删除
 	 * @return
 	 */
 	@RequestMapping("/delete")
 	@ResponseBody
 	public String userDelete(Integer id) {
 		System.out.println("userDeltet>>>>>>>>>>>>>>>>>>>>>>>>>>");
 		User user=new User();
 		user.setId(id);
 		int count=userService.getIdDelete(user);
 		logger.debug(count+">>><<<");
 		String sjosn="false";
 		if(count>0)
 		{
 			sjosn="true";
 		}
 		sjosn=JSON.toJSONString(sjosn);
 		logger.debug(sjosn+">>><<<");
 		return sjosn;
 	}
 	
 	/**
 	 * 跳入修改密码方法
 	 */
 	@RequestMapping("/pwdmodify")
 	public  Object updateUser(String oldpassword,
			HttpServletResponse response,HttpSession session){
		return "pwdmodify";
	}

 	
 	 /**
 	 * 判断是否是原密码
 	 * @return
 	 */
 	@RequestMapping("/pwdmodify.html")
 	@ResponseBody
	public  Object updateUserPwd(String oldpassword,
			HttpServletResponse response,HttpSession session) throws IOException {
        String delResult = "false";
        logger.debug(oldpassword+">>>>>>>>>");
        logger.debug((User)session.getAttribute("user")+">>>>>>>>>");
	    User id =(User)session.getAttribute("user");
	    if(id.getUserPassword().equals(oldpassword)){
	    	delResult = "true";
	    }
	    delResult = JSON.toJSONString(delResult);
	    logger.debug(delResult+">>>>>>>>>");
		return delResult;
	}
 	
 	
 	
 	/**
 	 * 修改密码方法
 	 */
 	@RequestMapping("/UpdatesPwd")
 	public  Object upPWD(String newpassword,
			HttpServletResponse response,HttpSession session){
 		logger.debug("<><><><><><><");
 		logger.debug("sssssssssssss<><>"+(User)session.getAttribute("user"));
 	    User user=(User)session.getAttribute("user");
 		Integer isss=userService.getPwdUser(user.getId(), newpassword);
 		logger.debug("id<><>"+user.getId()+"<><><><>"+newpassword);
 		if(isss>0){
 			return "redirect:/user/dologin.html";
 		}
		return "error";
	}

 	
 	
 	 /**
 	 * 退出
 	 * @return
 	 */
 	@RequestMapping("/logout")
 	public String userExist() {
 		System.out.println("userExist>>>>>>>>>>>>>>>>>>>>>>>>>>");
 		return "redirect:/user/dologin.html";
 	}
 	
 	
 	
 	
 	
}
