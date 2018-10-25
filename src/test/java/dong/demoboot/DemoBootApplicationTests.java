package dong.demoboot;

import dong.demo.task.forkjoin.mapper.ExecuteResultMapper;
import dong.demoboot.dao.UserRepository;
import dong.demoboot.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.rowset.Predicate;
import java.util.ArrayList;
import java.util.List;

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
		List<Predicate> predicates = new ArrayList<>();

		UserQuery u = new UserQuery();
		Example e = Example.of(u);
		userRepository.findAll(e);
	}

	class UserQuery extends User {
		private Integer startAge;
		public Integer getStartAge() {
			return startAge;
		}
		public void setStartAge(Integer startAge) {
			this.startAge = startAge;
		}
	}

	@Test
	public void testExecute() {
		logger.warn("testExecute executeResult: {}", executeResultMapper.selectByOnceMilli(50));
	}
}
