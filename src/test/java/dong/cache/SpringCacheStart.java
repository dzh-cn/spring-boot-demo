package dong.cache;

import dong.DemoBootApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
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

    @Resource
    CacheManager cacheManager;

    @Test
    public void testCache() throws InterruptedException {
        int i = 1;
        while (i++ < 20) {
            OrderBean orderBean = orderService.getOrderBean(1);
            logger.info("testCache : {}", orderBean);
        }
    }

    @Test
    public void manualCache() {
    	logger.info("cache ----: {}", cacheManager);
        Cache cus = cacheManager.getCache("cus");
        logger.info("cache put before: {}", cus.get("name"));
        cus.put("name", "value");
        cus.get("", Object.class);
        logger.info("cache put after: {}", cus.get("name").get());
    }
}
