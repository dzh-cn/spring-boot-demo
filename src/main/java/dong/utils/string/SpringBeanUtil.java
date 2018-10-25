package dong.utils.string;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author: dongzhihua
 * @time: 2018/10/25 12:46:51
 */
@Component
public class SpringBeanUtil {
    static ApplicationContext applicationContext;

    @Resource
    public void setApplicationContext(ApplicationContext ac) {
        applicationContext = ac;
    }

    public static <T> T getBean(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }

    public static <T> T getBean(String name, Class<T> clazz) {

        if (!applicationContext.containsBean(name)) {
            return null;
        }
        return applicationContext.getBean(name, clazz);
    }
}
