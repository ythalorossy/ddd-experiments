package com.example.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;

@SpringBootApplication
@EnableCircuitBreaker
public class GatewayApplication {


	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

	// @Bean
	// public RouteLocator myRoutes(RouteLocatorBuilder builder) {
	// 	return builder.routes()
	// 			// Add a simple re-route from: /order to: http://localhost:8100
	// 			// Add a simple "Hello:World" HTTP Header
	// 			.route(p -> p.path("/order/**") // intercept calls to the /get path
	// 					.filters(f -> f.addRequestHeader("Hello", "World")) // add header
	// 					.uri("http://localhost:8100/")) // forward to httpbin
	// 			.build();
	// }

}
