package cn.smbms.test;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.smbms.pojo.Provider;
import cn.smbms.pojo.User;
import cn.smbms.service.ProviderService;

public class ProviderTest {
	
	private static Logger logger=Logger.getLogger(ProviderTest.class);

	/**
	 * 添加
	 */
	@Test
	public void testAdd() {
		ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
		ProviderService providerService=(ProviderService) context.getBean("providerService");
		//proCode,proName,proDesc,proContact,proPhone,proAddress,proFax,createdBy,creationDate
		//'GH_UIUI009','百度有限公司','长期合作伙伴，主营产品：坚果炒货.果脯蜜饯.天然花茶.营养豆豆.特色美食.进口食品.海味零食.肉脯肉','张狗狗','1454334333',
		//'河北省石家庄新华区','0523-21299098','1','2015-09-09
		Provider pro=new Provider();
		pro.setProCode("GH_UIUI");
		pro.setProName("百度有限公司");
		pro.setProDesc("长期合作伙伴，主营产品：坚果炒货.果脯蜜饯.");
		pro.setProContact("张狗");
		pro.setProPhone("1454399999");
		pro.setProAddress("河北省石家庄新华区");
		pro.setProFax("0523-21299098");
		pro.setCreatedBy(1);
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
		String timeString="2015-09-09";
		Date date=null;
		try {
			date = simpleDateFormat.parse(timeString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		pro.setCreationDate(date);
	    int count=providerService.add(pro);
		System.out.println("coutn<><>>Add:"+count);
	}
	
	
	/**
	 * 总记录数
	 */
	@Test
	public void testCount() {
		ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
		ProviderService providerService=(ProviderService)context.getBean("providerService");
	    int count=providerService.getProront("SD_GYS001", "山东豪克华光联合发展有限公司");
		System.out.println("coutn<><>>"+count);
	}
	
	/**
	 * 分页list
	 */
	@Test
	public void testList() {
		ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
		ProviderService providerService=(ProviderService)context.getBean("providerService");
//		Provider provider=new Provider();
		String queryUserName="SD_GYS001";
		String  queryUserRole="山东豪克华光联合发展有限公司";
	    List<Provider> list=providerService.getUserList(queryUserName, queryUserRole);
		for (Provider user2 : list) {
			logger.info("<><>><><>"+user2.getProAddress()+"<>><"+user2.getProName()+"<><>"
		+user2.getProCode()+"<>"+user2.getProContact());
		}
	}
	
	
	/**
	 * 根据id查询
	 */
	@Test
	public void tesId() {
		ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
		ProviderService providerService=(ProviderService)context.getBean("providerService");
		Integer id=12;
	    Provider provider= providerService.getId(id);
	    logger.debug("<<><>:"+provider.getProAddress()+"<>"+provider.getProContact()+"<>"+provider.getProPhone());
	}
	
	
	/**
	 * 修改
	 */
	@Test
	public void tesUpdate() {
		ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
		ProviderService providerService=(ProviderService)context.getBean("providerService");
		Provider pro=new Provider();
		pro.setProCode("BJ_GYS005");
		pro.setId(12);
		pro.setProAddress("北京市大兴区旧宫");
		pro.setProContact("孙欣");
		pro.setProDesc("长期合作伙伴，主营产品：日化环保清洗剂，家居洗涤专卖、洗涤用品网、墙体除霉剂、墙面霉菌清除剂等");
		pro.setProFax("010-35576786");
		pro.setProName("北京隆盛日化科技");
	    int count= providerService.getUpdatPro(pro);
	    logger.debug("<<><>:"+count);
	}
	
	/**
	 * 删除
	 */
	@Test
	public void tesDelete() {
		ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
		ProviderService providerService=(ProviderService)context.getBean("providerService");
		Integer id=12;
		Provider pro=new Provider();
		pro.setId(12);
	    int count= providerService.getIdDelete(id);
	    logger.debug("<<><>:"+count);
	}
	
   
}
