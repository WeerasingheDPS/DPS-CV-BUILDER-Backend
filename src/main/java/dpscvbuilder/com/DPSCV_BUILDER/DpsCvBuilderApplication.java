package dpscvbuilder.com.DPSCV_BUILDER;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class DpsCvBuilderApplication {

	public static void main(String[] args) {
		SpringApplication.run(DpsCvBuilderApplication.class, args);
	}

	@GetMapping("/welcome")
	public ResponseEntity<String> welcome(){
		String msg = "Hi! Welcome to DPS CV BUILDER You Are IN!";
		return new ResponseEntity<>(msg, HttpStatus.OK);
	}

}
