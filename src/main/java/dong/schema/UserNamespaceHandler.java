package dong.schema;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * UserNamespaceHandler
 *
 * @author: dongzhihua
 * @time: 2018/10/25 18:23:41
 */
public class UserNamespaceHandler extends NamespaceHandlerSupport{
    public void init() {
        registerBeanDefinitionParser("user", new UserBeanDefinitionParser());
    }
}
