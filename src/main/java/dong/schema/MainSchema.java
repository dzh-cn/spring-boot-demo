package dong.schema;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * main
 *
 * @author: dongzhihua
 * @time: 2018/10/25 18:25:52
 */
public class MainSchema {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext con = new ClassPathXmlApplicationContext("classpath:META-INF/applicationContext.xml");
        System.out.println(con.getBean("eric"));
    }
}
