package br.com.geekwx.finacx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport
public class FinacxApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinacxApplication.class, args);
	}

}
