package se.sundsvall.alkt;

import static org.springframework.boot.SpringApplication.run;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;

import se.sundsvall.dept44.ServiceApplication;

@EnableCaching
@EnableFeignClients
@ServiceApplication
public class Application {
	public static void main(final String... args) {
		run(Application.class, args);
	}
}
