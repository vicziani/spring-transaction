package jtechlog.springtransaction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
//@EnableTransactionManagement(proxyTargetClass = true)
public class JpaSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpaSpringApplication.class, args);
	}
}
