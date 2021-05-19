package rs.ac.bg.etf.webphoto;

import com.paypal.core.PayPalEnvironment;
import com.paypal.core.PayPalHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.concurrent.TimeUnit;

@SpringBootApplication
@EnableTransactionManagement
public class WebphotoApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebphotoApplication.class, args);
	}

	@Bean
	public PayPalHttpClient getPayPalHttpClient(@Value("${paypal.client.id}") String clientId, @Value("${paypal.client.secret}") String secretId) {
//		 PayPalEnvironment environment = new PayPalEnvironment.Live(clientId,secretId);
		PayPalEnvironment environment = new PayPalEnvironment.Sandbox(clientId,secretId);
		/*
		 * PayPal HTTP client instance with environment that has access
		 * credentials context. Use to invoke PayPal APIs.
		 */
		PayPalHttpClient client = new PayPalHttpClient(environment);
		client.setConnectTimeout((int) TimeUnit.SECONDS.toMillis(900));
		return client;
	}


}
