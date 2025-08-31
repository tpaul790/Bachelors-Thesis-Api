package com.ubb.synergy;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SynergyApplication {

	public static void main(String[] args) { SpringApplication.run(SynergyApplication.class, args); }
}
