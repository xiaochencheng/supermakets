package cn.smbms.test;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.smbms.pojo.Role;
import cn.smbms.pojo.UploadFile;
import cn.smbms.pojo.User;
import cn.smbms.service.UserService;

public class UserTest {

	private Logger logger=Logger.getLogger(UserTest.class);
	
	/**
	 * 登录
	 */
	@Test
	public void test() {
		ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
		UserService userService=(UserService) context.getBean("userService");
		User user=new User();
		user.setUserCode("admin");
		user.setUserPassword("1234567");
	    List<User> list=userService.getLogin(user);
		for (User user2 : list) {
			logger.info("<><>><><>"+user2.getUserName()+"<>><"+user2.getUserRole()+"<><>"
		+user2.getAddress()+"<>"+user2.getBirthday());
		}
	}
	
	/**
	 * 总记录数
	 */
	@Test
	public void testCount() {
		ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
		UserService userService=(UserService) context.getBean("userService");
		/*User user=new User();
        user.setUserRole(3);
		user.setUserName("可");*/
	    int count=userService.getUserCount(null, null);
		System.out.println("coutn<><>>"+count);
	}
	
	/**
	 * 分页list
	 */
	@Test
	public void testList() {
		ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
		UserService userService=(UserService) context.getBean("userService");
		User user=new User();
		user.setUserCode("admin");
		user.setUserPassword("1234567");
		int pageSize=5;
		int currentPageNo=1;
		String queryUserName="可";
		int  queryUserRole=3;
	    List<User> list=userService.getUserList(queryUserName, queryUserRole, currentPageNo, pageSize);
		for (User user2 : list) {
			logger.info("<><>><><>"+user2.getUserName()+"<>><"+user2.getUserRole()+"<><>"
		+user2.getAddress()+"<>"+user2.getBirthday());
		}
	}
	
	/**
	 * 添加
	 */
	@Test
	public void testAdd() {
		//userCode,userName,userPassword,gender,birthday,phone,address,userRole,createdBy,creationDate,idPicPath,workPicPath
		ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
		UserService userService=(UserService) context.getBean("userService");
		User user=new User();
		user.setUserCode("UIUIUI");
        user.setUserRole(3);
		user.setUserName("可");
		user.setUserPassword("12121");
		user.setGender(2);
//		String sp=new SimpleDateFormat().format("yyyy-MM-dd");
		user.setBirthday(new Date());
		user.setAddress("搜我");
		user.setUserRole(1);
		user.setCreatedBy(2);
		user.setCreationDate(new Date());
		user.setIdPicPath("D://wo");
		user.setWorkPicPath("D://wo");
		
	    int count=userService.useradd(user);
		System.out.println("coutn<><>>"+count);
	}
	
	/**
	 * 查看
	 */
	@Test
	public void testImg() {
		ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
		UserService userService=(UserService) context.getBean("userService");
		int id=5;
		User user=userService.getUserImg(id);
		System.out.println("coutn<><>>"+user.getUserCode()+"<>"+user.getIdPicPath());
	}
	
	/**
	 * 修改
	 */
	@Test
	public void testUpdate() {
		ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
		UserService userService=(UserService) context.getBean("userService");
		User user=new User();
		user.setAddress("郴州");
		user.setIdPicPath("woow");
		user.setId(55);
	    int count=userService.getUpdateUser(user);
		System.out.println("coutn<> Upate<>>"+count);
	}
	
	
	/**
	 *删除
	 */
	@Test
	public void testDelete() {
		ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
		UserService userService=(UserService) context.getBean("userService");
		User user=new User();
		user.setId(58);
	    int count=userService.getIdDelete(user);
		System.out.println("coutn<> Delete<>>"+count);
	}
	
	
	/**
	 * 添加文件上传
	 */
	@Test
	public void testAddUpdaload() {
		ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
		UserService userService=(UserService) context.getBean("userService");
		UploadFile file=new UploadFile();
		file.setUserid(10);
		file.setFilesize(140);
		file.setFileType(1);
		file.setRealfilename("001.jpg");
		file.setSavefilename("1534931166720_Personal.jpg");
		String time="2015-12-12";
		SimpleDateFormat timeDateFormat=new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date date = timeDateFormat.parse(time);
			file.setUploadDate(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		file.setUploadPerson("sunxing");
		int count=userService.pathLoadAdd(file);
		System.out.println("coutn<><>>"+count);
	}
	
	
	/**
	 * 密码修改
	 */
	@Test
	public void testpwdUser() {
		ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
		UserService userService=(UserService) context.getBean("userService");
		int id=70;
		String pwd="1111111";
	    int count=userService.getPwdUser(id,pwd);
		System.out.println("coutn<> Upate<>>"+count);
	}
	
	/**
	 * 密码查询
	 */
	@Test
	public void testpwdSelect() {
		ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
		UserService userService=(UserService) context.getBean("userService");
		int id=1;
	    User user =userService.getPwd(id);
	    System.out.println("count:<><><><>"+user.getUserPassword());
		
	}
	
	/**
	 * 用户角色
	 */
	@Test
	public void testRole() {
		ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
		UserService userService=(UserService) context.getBean("userService");
		List<Role> user=userService.getRole();
		for (Role role : user) {
			logger.debug("<><>:"+role.getRoleName());
		}
	}
	
}
