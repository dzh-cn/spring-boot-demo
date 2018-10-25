package dong.cache;

import dong.DemoBootApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * spring cache start
 *
 * @author: dongzhihua
 * @time: 2018/10/25 12:02:01
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoBootApplication.class)
public class SpringCacheStart {
    private static Logger logger = LoggerFactory.getLogger(SpringCacheStart.class);
    @Resource
    OrderService orderService;
    @Test
    public void testCache() throws InterruptedException {
        int i = 1;
        while (i++ < 20) {
            OrderBean orderBean = orderService.getOrderBean(1);
            logger.info("testCache : {}", orderBean);
        }
    }
}
