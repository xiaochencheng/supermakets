package cn.smbms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cn.smbms.dao.bill.BillMapper;
import cn.smbms.pojo.Bill;
import cn.smbms.pojo.Provider;

@Service("billService")
public class BillServiceImpl implements BillService {

	@Autowired
	@Qualifier("billMapper")
	private BillMapper billMapper;
	
	public BillMapper getBillMapper() {
		return billMapper;
	}

	public void setBillMapper(BillMapper billMapper) {
		this.billMapper = billMapper;
	}

	@Override
	public List<Bill> getBillList(String proName, Integer providerId,
			Integer isPayment) {
		return billMapper.getBillList(proName, providerId, isPayment);
	}

	@Override
	public List<Provider> getProviders() {
		return billMapper.getProviders();
	}

	@Override
	public Bill getBill(Integer id) {
		return billMapper.getBill(id);
	}

	@Override
	public Integer getBillUpdate(Bill bill) {
		return billMapper.getBillUpdate(bill);
	}

	@Override
	public Integer getProUpdate(Provider provider) {
		return billMapper.getProUpdate(provider);
	}

	@Override
	public Integer getIdBill(Integer id) {
		return billMapper.getIdBill(id);
	}

	@Override
	public Integer getBillAdd(Bill bill) {
		return billMapper.getBillAdd(bill);
	}


}
