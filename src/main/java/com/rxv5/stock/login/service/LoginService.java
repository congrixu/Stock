package com.rxv5.stock.login.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.rxv5.stock.entity.User;
import com.rxv5.stock.login.dao.LoginDao;

@Service
public class LoginService {

	@Resource
	private LoginDao loginDao;

	/**
	 * 根据用户账号查询用户实体
	 * @param userId 用户账号
	 * @return
	 * @throws Exception
	 */
	public User getUserById(String userId) throws Exception {
		return loginDao.selectUserById(userId);
	}

	/**
	 * 验证用户是否可以登录系统
	 * @param userId 用户账号
	 * @param pwd 密码
	 * @return
	 * @throws Exception  成功返回User对象，失败返回null
	 */
	public User checkUserLogin(String userId, String pwd) throws Exception {
		User user = loginDao.selectUserById(userId);
		if (user != null) {
			String _pwd = user.getPwd();
			if (!pwd.equals(_pwd))
				user = null;
		}
		return user;
	}
}
