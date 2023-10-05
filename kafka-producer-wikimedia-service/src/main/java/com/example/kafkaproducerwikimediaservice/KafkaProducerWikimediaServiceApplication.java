package com.example.kafkaproducerwikimediaservice;

import com.example.kafkaproducerwikimediaservice.service.WikimediaChangesProducerService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@AllArgsConstructor
@SpringBootApplication
public class KafkaProducerWikimediaServiceApplication implements CommandLineRunner {

	private final WikimediaChangesProducerService wikimediaChangesProducerService;

	public static void main(String[] args) {
		SpringApplication.run(KafkaProducerWikimediaServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		wikimediaChangesProducerService.sendMessage();
	}

}
