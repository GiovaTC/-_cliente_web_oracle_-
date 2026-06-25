package principal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *  Clase principal de la aplicación .
 */
@SpringBootApplication
public class ClienteWebOracleApplication {

	public static void main(String[] args) {
		SpringApplication.run(
				ClienteWebOracleApplication.class,
				args
		);
	}
}
