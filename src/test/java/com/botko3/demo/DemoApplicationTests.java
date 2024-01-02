package com.botko3.demo;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class DemoApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	public void uuidTest(){

	}

	@Test
	public void testGenJwt(){
		Map<String,Object> claims = new HashMap<>();
		claims.put("id","2");

		String jwt = Jwts.builder()
				.signWith(SignatureAlgorithm.HS256, "botko3")
				.setClaims(claims)
				.setExpiration(new Date(System.currentTimeMillis() + 3600 * 1000))
				.compact();

		System.out.println(jwt);
	}

	@Test
	public void testParseJwt(){
		Claims claims = Jwts.parser()
				.setSigningKey("botko3")
				.parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJpZCI6IjIiLCJleHAiOjE3MDI1NjQ4Mzh9.ItK9eb6bH5sWkLZUvCKXVZaxuUShbYUOkynRr8uhVAQ")
				.getBody();
		System.out.println(claims);



	}




}
