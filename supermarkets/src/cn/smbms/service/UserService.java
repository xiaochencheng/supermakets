package cn.smbms.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.smbms.pojo.Role;
import cn.smbms.pojo.UploadFile;
import cn.smbms.pojo.User;

public interface UserService {

	public List<User> getLogin(User user);
	
	public int getUserCount(@Param("userName")String userName,@Param("userRole")Integer userRole);
	
	public List<User> getUserList(String queryUserName, Integer queryUserRole,Integer currentPageNo, Integer pageSize);
	
	public int useradd(User user);
	
	public User getUserImg(Integer id);
	
	public Integer getUpdateUser(User user);
	
	public Integer getIdDelete(User user);
	
	public Integer pathLoadAdd(UploadFile files);
	
	public Integer getPwdUser(Integer id,String userPassword);
	
	public User getPwd(Integer id);
	
	public List<Role> getRole();
	
}
