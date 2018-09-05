package cn.smbms.dao.bill;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.smbms.pojo.Bill;
import cn.smbms.pojo.Provider;

public interface BillMapper {
	
	//查询list
	public List<Bill> getBillList(@Param("proName")String proName,
                                  @Param("providerId")Integer providerId,
                                  @Param("isPayment")Integer isPayment);
	
	//供应商
	public List<Provider> getProviders();
	
	//查看
	public Bill getBill(Integer id);
	
	//修改订单表
	public Integer getBillUpdate(Bill bill);

	
	//修改供应商表
    public Integer getProUpdate(Provider provider);
    
    //删除
    public Integer getIdBill(Integer id);
    
    //添加订单表
    public Integer getBillAdd(Bill bill);
}
