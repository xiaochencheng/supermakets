package cn.smbms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cn.smbms.dao.user.UserMapper;
import cn.smbms.pojo.Role;
import cn.smbms.pojo.UploadFile;
import cn.smbms.pojo.User;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	@Qualifier("userMapper")
	private UserMapper userMapper;
	
	public UserMapper getUserMapper() {
		return userMapper;
	}

	public void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}

	/**
	 * 登录
	 */
	@Override
	public List<User> getLogin(User user) {
		return userMapper.getLogin(user);
	}

	/**
	 * 查询总记录数
	 */
	@Override
	public int getUserCount(String userName, Integer userRole) {
		return userMapper.getUserCount(userName, userRole);
	}

	/**
	 * 分页显示list
	 */
	@Override
	public List<User> getUserList(String userName, Integer userRole, Integer from,Integer pageSize) {
		from = (from-1)*pageSize;
		return userMapper.getUserList(userName, userRole, from, pageSize);
	}

	@Override
	public int useradd(User user) {
		return userMapper.useradd(user);
	}

	@Override
	public User getUserImg(Integer id) {
		return userMapper.getUserImg(id);
	}

	@Override
	public Integer getUpdateUser(User user) {
		return userMapper.getUpdateUser(user);
	}

	@Override
	public Integer getIdDelete(User user) {
		return userMapper.getIdDelete(user);
	}

	@Override
	public Integer pathLoadAdd(UploadFile files) {
		return userMapper.pathLoadAdd(files);
	}

	@Override
	public Integer getPwdUser(Integer id,String pwd) {
		return userMapper.getPwdUser(id,pwd);
	}

	@Override
	public User getPwd(Integer id) {
		return userMapper.getPwd(id);
	}

	@Override
	public List<Role> getRole() {
		return userMapper.getRole();
	}

	
}
