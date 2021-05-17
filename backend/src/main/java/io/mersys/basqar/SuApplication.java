package io.mersys.basqar;

import com.github.mongobee.Mongobee;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import io.mersys.basqar.config.AppProperties;

@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
@ComponentScan(basePackages = { "io.mersys.basqar.config.db", "io.mersys.basqar", "io.mersys.basqar.changelogs" })
public class SuApplication {

	public static void main(String[] args) {
		SpringApplication.run(SuApplication.class, args);
	}
	
}
