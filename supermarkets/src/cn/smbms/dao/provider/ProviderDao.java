package cn.smbms.dao.provider;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.context.annotation.Primary;

import cn.smbms.pojo.Provider;
import cn.smbms.pojo.User;

public interface ProviderDao {
	
	/**
	 * 增加供应商
	 * @param connection
	 * @param provider
	 * @return
	 * @throws Exception
	 */
	public int add(Provider provider);

	 //根据。。。查询总记录数
     public int getProront(@Param("proCode")String proCode,@Param("proName")String proName);
     //分页
	 public List<Provider> getUserList(@Param("proCode")String proCode,
				                   @Param("proName")String proName);
	 
	 //根据ID查询
	 public Provider getId(Integer id);
	 
	 //修改
	 public Integer getUpdatPro(Provider pro);
	 
	 //删除
	 public Integer getIdDelete(Integer id);
	 
	
}
