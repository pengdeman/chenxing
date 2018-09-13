
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@SpringBootApplication
@ComponentScan(basePackages= {"com.cx"})
public class ChenXingWebApplication {
	public static void main(String[] args) {
		SpringApplication.run(ChenXingWebApplication.class, args);
	}
}
