package com.study.restdocs;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

@Sql("/data.sql")
@ActiveProfiles("test")
@SpringBootTest
class RestdocsApplicationTests {

	@Test
	void contextLoads() {
	}

}
