package cn.smbms.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.smbms.pojo.Bill;
import cn.smbms.pojo.Provider;

public interface BillService {
	public List<Bill> getBillList(@Param("proName")String proName,
                                  @Param("providerId")Integer providerId,
                                  @Param("isPayment")Integer isPayment);
	
	public List<Provider> getProviders();
	
	public Bill getBill(Integer id);
	
	public Integer getBillUpdate(Bill bill);
	
	public Integer getProUpdate(Provider provider);
	
	public Integer getIdBill(Integer id);
	
	public Integer getBillAdd(Bill bill);
}
