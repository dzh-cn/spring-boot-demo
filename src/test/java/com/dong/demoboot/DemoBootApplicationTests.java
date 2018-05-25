package com.dong.demoboot;

import com.demo.task.forkjoin.mapper.ExecuteResultMapper;
import com.dong.demoboot.dao.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoBootApplicationTests {

	private static Logger logger = LoggerFactory.getLogger(DemoBootApplicationTests.class);

	@Autowired
	UserRepository userRepository;

	@Autowired
	ExecuteResultMapper executeResultMapper;

	@Test
	public void contextLoads() {
		logger.warn("contextLoads all: {}", userRepository.findAll());
	}
	@Test
	public void testExecute() {
		logger.warn("testExecute executeResult: {}", executeResultMapper.selectByOnceMilli(50));
	}
}
