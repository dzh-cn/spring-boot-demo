package dong.demoboot.configeration;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.support.WebBindingInitializer;

/**
 * WebBindingInitializer
 * 将controller参数中的前后空格trim掉，空字符串转为null
 *
 * @author: dongzhihua
 * @time: 2018/11/7 16:49:22
 */
@ControllerAdvice
public class MyWebBindingInitializer implements WebBindingInitializer{
    @Override
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class,new StringTrimmerEditor(true));
    }
}
