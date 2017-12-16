package de.javaakademie.comic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan(basePackageClasses = { ComicApplication.class })
@SpringBootApplication
public class ComicApplication {

	public static void main(String[] args) {
		SpringApplication.run(ComicApplication.class, args);
	}

}