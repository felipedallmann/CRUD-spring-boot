package com.example.spring_boot_crud;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = "spring.datasource.url=none")
class SpringBootCrudApplicationTests {

	@Test
	void contextLoads() {
	}

}
