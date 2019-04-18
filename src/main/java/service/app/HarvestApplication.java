package service.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication(exclude={DataSourceAutoConfiguration.class,HibernateJpaAutoConfiguration.class})
@SpringBootApplication
public class HarvestApplication {

	public static void main(String[] args) {
		SpringApplication.run(HarvestApplication.class, args);
	}
}
