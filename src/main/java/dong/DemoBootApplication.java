package dong;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ImportResource;

@ImportResource("classpath:beanConfig/applicationBeans.xml")
@SpringBootApplication
@EnableCaching
public class DemoBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoBootApplication.class, args);
	}
}
