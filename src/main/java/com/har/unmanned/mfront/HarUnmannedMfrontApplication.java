package com.har.unmanned.mfront;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.har.unmanned.mfront.dao")
public class HarUnmannedMfrontApplication {

	public static void main(String[] args) {
		SpringApplication.run(HarUnmannedMfrontApplication.class, args);
	}
}
