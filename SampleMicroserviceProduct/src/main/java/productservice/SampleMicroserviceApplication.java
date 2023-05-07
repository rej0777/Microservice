package productservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SampleMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SampleMicroserviceApplication.class, args);
	}

}
/*
We have to use this dependency.

    <dependency>
      <groupId>de.flapdoodle.embed</groupId>
      <artifactId>de.flapdoodle.embed.mongo.spring30x</artifactId>
      <version>4.4.1</version>
    </dependency>
We also need to specify this version in the application properties.

de.flapdoodle.mongodb.embedded.version=4.4.1
*/