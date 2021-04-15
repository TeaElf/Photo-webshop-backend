package rs.ac.bg.etf.webphoto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class WebphotoApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebphotoApplication.class, args);
	}

}
