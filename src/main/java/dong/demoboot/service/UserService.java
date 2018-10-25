package dong.demoboot.service;

import dong.demoboot.dao.UserRepository;
import dong.demoboot.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class UserService {
	private String msg;
	@Autowired
    UserRepository userRepository;
	Logger logger = LoggerFactory.getLogger(UserService.class);

	public User getOne(Integer id) {
		logger.info("msg:" + msg);
		return userRepository.getOne(id);
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
