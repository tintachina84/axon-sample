package com.tintachina.axonsample;

import com.tintachina.axonsample.config.AxonConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({ AxonConfig.class })
public class AxonSampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(AxonSampleApplication.class, args);
	}

}
