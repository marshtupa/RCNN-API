package ru.mtuci.degree.project.rcnn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class RcnnApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(RcnnApiApplication.class, args);
	}

}
