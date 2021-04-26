package ru.mtuci.degree.project.entry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ru.mtuci.degree.project.entry.configuration.FileStorageProperties;
import ru.mtuci.degree.project.entry.configuration.RegistrationConfigurations;

@SpringBootApplication
@ConfigurationPropertiesScan
public class EntryApplication {

	public static void main(String[] args) {
		SpringApplication.run(EntryApplication.class, args);
	}

}
