package cn.smbms.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.smbms.pojo.Provider;
import cn.smbms.pojo.User;

public interface ProviderService {

	public int add(Provider provider);
	
	//根据。。。查询总记录数
    public int getProront(@Param("proCode")String proCode,@Param("proName")String proName);
    //
    public List<Provider> getUserList(String proCode, String proName);
    
    public Provider getId(Integer id);
    
    public Integer getUpdatPro(Provider pro);
    
    public Integer getIdDelete(Integer id);
	
}
