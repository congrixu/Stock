package com.rxv5.stock.system.user.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rxv5.stock.Constant.UserStateEnum;
import com.rxv5.stock.entity.User;
import com.rxv5.stock.system.user.dao.UserDao;

@Service
@SuppressWarnings("unchecked")
public class UserService {

	@Resource
	private UserDao userDao;

	public Map<String, Object> query(Map<String, String> param, Integer page, Integer rows, String sort, String order)
			throws Exception {
		Map<String, Object> result = userDao.select(param, (page - 1) * rows, rows, sort, order);

		List<User> list = (List<User>) result.get("list");
		if (list != null && list.size() > 0) {
			for (User user : list) {
				String state = user.getState();
				user.setStateStr(UserStateEnum.byId(state).getText());
			}
		}
		return result;
	}

	public User get(String id) {
		return userDao.get(User.class, id);
	}

	@Transactional(rollbackFor = Exception.class)
	public void saveOrModify(User user) {
		String id = user.getId();
		if (id == null || id.trim().length() <= 0) {
			user.setState(UserStateEnum.entryState.getId());
			user.setEntryDate(new Date());
			userDao.save(user);
		} else {
			User _user = get(id);
			_user.setName(user.getName());
			_user.setPhone(user.getPhone());
			_user.setAddr(user.getAddr());
			_user.setIdCard(user.getIdCard());
			_user.setPwd(user.getPwd());
			_user.setBirthday(user.getBirthday());
			userDao.update(_user);
		}
	}

	@Transactional(rollbackFor = Exception.class)
	public void delete(String userid) {
		User user = get(userid);
		user.setId(userid);
		user.setState(UserStateEnum.resignState.getId());
		user.setResignDate(new Date());
		userDao.update(user);
	}

}
