package com.dominik.kRow;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.assertThat;
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApplicationTests {
	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	void jpacontextload() {
		//when
		ParkingTicket[] result = restTemplate.getForObject("http://localhost:" + port + "/lucene/2", ParkingTicket[].class);
		//then
		assertThat(result).hasSize(19);
	}

	@Test
	void contextload() {
		for(int i = 0; i<2; i++){
		ParkingTicket[] resultJpa = restTemplate.getForObject("http://localhost:" + port + "/jpa/"+i, ParkingTicket[].class);
		//when
		ParkingTicket[] resultLucene = restTemplate.getForObject("http://localhost:" + port + "/lucene/"+i, ParkingTicket[].class);
		//then
		assertThat(resultJpa).hasSize(resultLucene.length);}
	}
}
