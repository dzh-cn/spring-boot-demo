package dong.utils.string;

import dong.cache.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBeanUtilTest {

    @Test
    public void getBean() {
        System.out.println(SpringBeanUtil.getBean(OrderService.class));
    }
}