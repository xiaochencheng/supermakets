package cn.smbms.dao.user;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.smbms.pojo.Role;
import cn.smbms.pojo.UploadFile;
import cn.smbms.pojo.User;

public interface UserMapper{
	                  
	//登录
	public List<User> getLogin(User user);
    //根据。。。查询总记录数
	public int getUserCount(@Param("userName")String userName,@Param("userRole")Integer userRole);
	//分页
	public List<User> getUserList(@Param("userName")String userName,
			                      @Param("userRole")Integer userRole,
			                      @Param("from")Integer from,
			                      @Param("pageSize")Integer pageSize);
	//添加
	public int useradd(User user);
	//查看
	public User getUserImg(Integer id);
	//修改
	public Integer getUpdateUser(User user);
	//删除
	public Integer getIdDelete(User user);
	//文件添加
	public Integer pathLoadAdd(UploadFile files);
	//密码修改
	public Integer getPwdUser(@Param("id")Integer id,
			                  @Param("userPassword")String userPassword);
	//查询密码
	public User getPwd(Integer id);
	
	//用户角色
	public List<Role> getRole();
	
}