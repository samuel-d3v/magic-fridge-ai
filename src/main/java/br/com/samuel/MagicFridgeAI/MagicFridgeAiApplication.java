package br.com.samuel.MagicFridgeAI;

import br.com.samuel.MagicFridgeAI.config.OpenAiProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@ConfigurationPropertiesScan
@SpringBootApplication
@EnableConfigurationProperties(OpenAiProperties.class)
public class MagicFridgeAiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MagicFridgeAiApplication.class, args);
	}

}
