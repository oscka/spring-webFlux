package com.devkuma.webflux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@SpringBootApplication
public class HelloWebFluxApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelloWebFluxApplication.class, args);
	}

	@Bean
	RouterFunction<ServerResponse> routes() {
		return route(GET("/"),
				req -> ok().body(Flux.just("Hello", "World!"), String.class));
	}

	@Bean
	RouterFunction<ServerResponse> routes(HelloHandler helloHandler) {
		return helloHandler.routes();
	}
}
