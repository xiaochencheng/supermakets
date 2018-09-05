package cn.smbms.test;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.smbms.pojo.Bill;
import cn.smbms.pojo.Provider;
import cn.smbms.service.BillService;
import cn.smbms.service.ProviderService;

public class BillTest {

	private static Logger logger=Logger.getLogger(BillTest.class);
	
	/**
	 * list
	 */
	@Test
	public void test() {
		ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
		BillService billService=(BillService)context.getBean("billService");
	    List<Bill> list=billService.getBillList("山", 13,2);
		for (Bill bill : list) {
			logger.debug("<><>"+bill.getProductName());
		}
	}
	
	
	/**
	 * 供应商
	 */
	@Test
	public void testProvider() {
		ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
		BillService billService=(BillService)context.getBean("billService");
	    List<Provider> list=billService.getProviders();
		for (Provider bill : list) {
			logger.debug("<><>"+bill.getProName());
		}
	}
	

	/**
	 * 查看
	 */
	@Test
	public void testBillView() {
		ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
		BillService billService=(BillService)context.getBean("billService");
		int id=3;
		Bill list=billService.getBill(id);
			logger.debug("<><>"+list.getProductName());
	}
	
	
	/**
	 * 修改billCode ='BILL2016_018',productName='哇哈哈',productUnit='瓶',productCount=1000,
                       totalPrice=23456 
	 */
	@Test
	public void testBillUpdate() {
		ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
		BillService billService=(BillService)context.getBean("billService");
		Provider pro=new Provider();
		pro.setProName("乐摆日用品厂");
		pro.setId(15);
		
		Bill bill=new Bill();
		bill.setId(18);
		bill.setBillCode("BILL2016_018");
		bill.setProductName("哇哈哈");
		bill.setProductUnit("瓶");
		BigDecimal date=BigDecimal.valueOf(1000.0);
		BigDecimal proCount=BigDecimal.valueOf(155555.0);
		bill.setProductCount(proCount);
		bill.setTotalPrice(date);
		int count =billService.getBillUpdate(bill);
		int counts=billService.getProUpdate(pro);
			logger.debug("<><>Update<><:"+count+"coutns:   " +counts);
	}
	
	
	/**
	 * 删除
	 */
	@Test
	public void testBillDelete() {
		ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
		BillService billService=(BillService)context.getBean("billService");
		int id=17;
		int count=billService.getIdBill(id);
			logger.debug("<><>"+count);
	}
	
	/**
	 * 添加
	 */
	@Test
	public void testAdd() {
		ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
		BillService billService=(BillService) context.getBean("billService");
		//(billCode,productName,productUnit,productCount,totalPrice)
	    // VALUES ('BILL2016_018','吸吸冰','瓶',2000.00,6000.00)
		Bill bill=new Bill();
		bill.setBillCode("BILL2016_019");
		bill.setProductName("吸吸冰1");
		bill.setProductUnit("瓶");
		BigDecimal bigCount=BigDecimal.valueOf(3234445.00);
		bill.setProductCount(bigCount);
		BigDecimal bigPrice=BigDecimal.valueOf(13245.00);
		bill.setTotalPrice(bigPrice);
	    int count=billService.getBillAdd(bill);
		System.out.println("coutn<><>>Add:"+count);
	}
	
	
}
