package cn.smbms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cn.smbms.dao.provider.ProviderDao;
import cn.smbms.pojo.Provider;
import cn.smbms.pojo.User;

@Service("providerService")
public class ProviderServiceImpl implements ProviderService {

	@Autowired
	@Qualifier("providerDao")
	private ProviderDao providerDao;
	
	public ProviderDao getProviderDao() {
		return providerDao;
	}

	public void setProviderDao(ProviderDao providerDao) {
		this.providerDao = providerDao;
	}

	@Override
	public int add(Provider provider) {
		return providerDao.add(provider);
	}

	@Override
	public int getProront(String proCode, String proName) {
		return providerDao.getProront(proCode, proName);
	}

	@Override
	public List<Provider> getUserList(String proCode, String proName) {
		return providerDao.getUserList(proCode, proName);
	}

	@Override
	public Provider getId(Integer id) {
		return providerDao.getId(id);
	}

	@Override
	public Integer getUpdatPro(Provider pro) {
		return providerDao.getUpdatPro(pro);
	}

	@Override
	public Integer getIdDelete(Integer id) {
		return providerDao.getIdDelete(id);
	}

}
