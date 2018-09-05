package cn.smbms.controller;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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

import cn.smbms.pojo.Provider;
import cn.smbms.pojo.UploadFile;
import cn.smbms.pojo.User;
import cn.smbms.service.ProviderService;
import cn.smbms.service.UserService;
import cn.smbms.tools.Constants;
import cn.smbms.tools.PageSupport;

@Controller
@RequestMapping("/pro")
public class ProviderController {
	
	Logger logger = Logger.getLogger(LoginController.class);

	@Autowired
	ProviderService providerService;
	
	/**
	 * 显示列表页
	 * @param model
	 * @param queryUserName
	 * @param queryUserRole
	 * @param pageIndex
	 * @return
	 */
	@RequestMapping(value = "/list.html")
	public String getUserList(
			Model model,
			@RequestParam(value = "proCode", required = false) String proCode,
			@RequestParam(value = "proName", required = false) String proName) {
		logger.info("getUserList ---- > queryUserName: " + proCode);
		logger.info("getUserList ---- > queryUserRole: " + proName);
		List<Provider> userList = providerService.getUserList(proCode, proName);
		model.addAttribute("providerList", userList);
		model.addAttribute("proCode", proCode);
		model.addAttribute("proName", proName);
		return "providerlist";
	}
	
	
	/**
	 * 显示列表页
	 * @return
	 */
	@RequestMapping(value = "/proadd")
	public String getProAdd() {
		logger.debug("添加的页面！");
		return "/provideradd";
	}
	
	
	
	/**
	 * 添加的方法
	 * @param user
	 * @param request
	 * @param attachs
	 * @return
	 */
	@RequestMapping("/proAdd")
	public String useraddsave(Provider provider,HttpServletRequest request) {
		
		provider.setProCode(provider.getProCode());
		provider.setProName(provider.getProName());
		provider.setProContact(provider.getProContact());
		provider.setProAddress(provider.getProAddress());
		provider.setProFax(provider.getProFax());
		provider.setProPhone(provider.getProPhone());
		provider.setProDesc(provider.getProDesc());
		logger.debug("<><><><>"+provider.getProAddress());
	    int count=providerService.add(provider);
		if (count>0) {
				return "redirect:/pro/list.html";
		}
		return "error";
	}
	
	
	
	/**
	 * 显示列表页
	 * @return
	 */
	@RequestMapping(value = "/proView")
	public String getProView(Integer proid,Model model) {
		logger.debug("<><>"+proid);
		Provider pro=providerService.getId(proid);
		logger.debug("DWW:"+pro.getProAddress());
		model.addAttribute("provider", pro);
		return "providerview";
	}
	
	/**
	 * 跳入修改页面
	 * @return
	 */
	@RequestMapping(value = "/proUp")
	public String getProUpdate(Integer proid,Model model) {
		Provider pro=providerService.getId(proid);
		model.addAttribute("prov", pro);
		return "providermodify";
	}
	
	
	/**
	 * 修改方法
	 * @return
	 */
	@RequestMapping(value = "/proUpdate.html")
	public String getProViews(Provider provider,@RequestParam(value = "proid", required = false) Integer proid,Model model) {
		
		logger.debug(provider+">>>>>>>>>>>>>>>>>>>>>");
		logger.debug(proid+">>>>>>>>>>>>>>>>>>>>>");
		provider.setProCode(provider.getProCode());
		provider.setProName(provider.getProName());
		provider.setProContact(provider.getProContact());
		provider.setProAddress(provider.getProAddress());
		provider.setProFax(provider.getProFax());
		provider.setProPhone(provider.getProPhone());
		provider.setProDesc(provider.getProDesc());
		provider.setId(proid);
		int count=providerService.getUpdatPro(provider);
		if(count>0){
			return "redirect:/pro/list.html";
		}
		return "error";
	}
	
	
	/**
	 * 删除
	 * @return
	 */
	@RequestMapping(value = "/del")
	@ResponseBody
	public String getProDelete(Provider provider,Integer proid,Model model) {
//		provider.setId(proid);
		int count=providerService.getIdDelete(proid);
		logger.debug("coutn>>>>>>>>>>>>>>>>>>"+count);
		String sjon="false";
		if(count>0){
			sjon="true";
		}
		sjon=JSON.toJSONString(sjon);
		logger.debug("><>:"+sjon);
		return sjon;
	}

}
