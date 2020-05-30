package com.cx.chenxing.service.zj;

import com.cx.chenxing.db.zj.entity.User;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Transactional
public interface IUserService {
	public User getUserById(int userId);

	public User findLoginUserByLoginNameAndPassword(String loginName, String password);

	public List<User> findName(String loginName, String mail);

	public void insert(User lg);  
	
	public void updateSelective(User user);

	public List<User> findAll();
}
