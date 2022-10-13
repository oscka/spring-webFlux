package com.devkuma.webflux;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Stream;

//@RestController
public class HelloController {

    @GetMapping("/")
    Flux<String> hello() {
        return Flux.just("Hello", "World");
    }

    @GetMapping("/stream")
    Flux<Map<String, Integer>> stream() {
        Stream<Integer> stream = Stream.iterate(0, i -> i + 1); //무한 stream
        return Flux.fromStream(stream.limit(10))
                .map(i -> Collections.singletonMap("value", i));
    }

    @GetMapping("/stream2")
    Flux<Map<String, Integer>> stream2() {
        Stream<Integer> stream = Stream.iterate(0, i -> i + 1);
        return Flux.fromStream(stream).zipWith(Flux.interval(Duration.ofSeconds(1)))
                .map(tuple -> Collections.singletonMap("value", tuple.getT1() /* 튜플의 첫 번째 요소 = Stream<Integer> 요소 */));
    }

    @PostMapping("/echo")
    Mono<String> echo(@RequestBody Mono<String> body) {
        return body.map(String::toUpperCase);
    }
}
