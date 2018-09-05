package cn.smbms.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import cn.smbms.pojo.Bill;
import cn.smbms.pojo.Provider;
import cn.smbms.service.BillService;

@Controller
@RequestMapping("/bill")
public class BillController {
	
	Logger logger = Logger.getLogger(BillController.class);

	@Autowired
	BillService billService;
	
	/**
	 * 显示列表页
	 * @param model
	 * @param queryUserName
	 * @param queryUserRole
	 * @param pageIndex
	 * @return
	 */
	@RequestMapping(value = "/list.html")
	public String getBillList(
			Model model,
			@RequestParam(value = "proName", required = false) String proName,
			@RequestParam(value = "providerId", required = false) Integer providerId,
			@RequestParam(value="isPayment" ,required=false)Integer isPayment) {
		logger.info("getUserList ---- > queryUserName: " + proName);
		logger.info("getUserList ---- > queryUserRole: " + providerId);
		logger.info("getRoleList------>querRole:"+isPayment);
		List<Bill> userList = billService.getBillList(proName, providerId, isPayment);
		List<Provider> proLostList=billService.getProviders();
		model.addAttribute("proname", proLostList);
		model.addAttribute("roleList", userList);
		model.addAttribute("proName", proName);
		model.addAttribute("providerId", providerId);
		model.addAttribute("isPayment", isPayment);
		return "billlist";
	}
	
	
	
	/**
	 * 显示列表页
	 * @param model
	 * @param queryUserName
	 * @param queryUserRole
	 * @param pageIndex
	 * @return
	 */
	@RequestMapping(value = "/billVi.html")
	public String getBillView(Model model,Integer billid) {
		logger.info("getRoleList------>querRole:"+billid);
		Bill userList = billService.getBill(billid);
		List<Provider> proLostList=billService.getProviders();
		model.addAttribute("list", proLostList);
		model.addAttribute("bill", userList);
		return "billview";
	}
	
	
	/**
	 * 跳入修改页面
	 * @param model
	 * @param billid
	 * @return
	 */
	@RequestMapping(value = "/billUpdate")
	public String getBillUpdate(Model model,Integer billid) {
		logger.info("getRoleList------>querRole:"+billid);
		Bill userList = billService.getBill(billid);
		List<Provider> list=billService.getProviders();
		model.addAttribute("bill", userList);
		model.addAttribute("proList", list);
		return "billmodify";
	}
	
	
	
	/**
	 * 修改方法
	 * @param model
	 * @param billid
	 * @return
	 */
	@RequestMapping(value = "/billUpdaJsp.html")
	public String getBillUpdates(@RequestParam(value="pid",required=false)Integer pid,
								@RequestParam(value="id",required=false)Integer billid,
								Model model,
								Bill bill,
								Provider provider) {
		logger.info("getRoleList------>querRole:"+billid);
		logger.info("getRoleList------>querRole:"+pid);
		bill.setId(billid);
		bill.setBillCode(bill.getBillCode());
	    bill.setProductName(bill.getProductName());
	    bill.setProductUnit(bill.getProductUnit());
	    bill.setProductCount(bill.getProductCount());
	    bill.setTotalPrice(bill.getTotalPrice());
	    logger.debug("<>><:"+bill.getId()+" "+bill.getProductName()+"<>"+
	    bill.getProductUnit()+" "+bill.getBillCode()+"<>"+bill.getProductCount()+"<>"+
	    bill.getTotalPrice());

	    provider.setId(pid);
	    logger.info("proid ID:"+provider.getId());
		provider.setProName(provider.getProName());
		int billCount=billService.getBillUpdate(bill);
		int proCount=billService.getProUpdate(provider);
		if(billCount>0 && proCount>0){
			return "redirect:/bill/list.html";
		}
		return "billmodify";
	}
	
	/**
	 * 删除
	 * @return
	 */
	@RequestMapping(value = "/delte")
	@ResponseBody
	public String getBillDelete(Bill bill,Integer billid,Model model) {
//		provider.setId(proid);
		int count=billService.getIdBill(billid);
		logger.debug("coutn>>>>>>>>>>>>>>>>>>"+count);
		String sjon="false";
		if(count>0){
			sjon="true";
		}
		sjon=JSON.toJSONString(sjon);
		logger.debug("><>:"+sjon);
		return sjon;
	}
	
	
	/**
	 * 跳入增加页面
	 * @param model
	 * @param billid
	 * @return
	 */
	@RequestMapping(value = "/billadd")
	public String getBillAdd(Model model) {
		logger.info("添加页面！");
		List<Provider> list=billService.getProviders();
		model.addAttribute("billList", list);
		return "/billadd";
	}
	
	/**
	 * 添加的方法
	 * @param user
	 * @param request
	 * @param attachs
	 * @return
	 */
	@RequestMapping("/billAdd.html")
	public String useraddsave(Bill bill,HttpServletRequest request) {
		bill.setBillCode(bill.getBillCode());
		bill.setProductName(bill.getProductName());
		bill.setProductUnit(bill.getProductUnit());
		bill.setProductCount(bill.getProductCount());
		bill.setTotalPrice(bill.getTotalPrice());
		logger.debug("<><><><>");
	    int count=billService.getBillAdd(bill);
		if (count>0) {
				return "redirect:/bill/list.html";
		}
		return "error";
	}
	
	
}
