package info.michaelmogessie.dbsynchronizer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"info.michaelmogessie.dbsynchronizer"})
public class DbSynchronizerApplication {
	public static void main(String[] args) {
		SpringApplication.run(DbSynchronizerApplication.class, args);
	}

}
