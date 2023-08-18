package com.dongpl.repository;

import com.dongpl.entity.Person;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.CoreSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.BiFunction;

@Component
public class PersonRepository {

    public Flux<Person> listAllPeople() {
        return new Flux<>() {
            @Override
            public void subscribe(CoreSubscriber<? super Person> coreSubscriber) {
                System.out.println(coreSubscriber);
            }
        };
    }

    public BiFunction<ServerWebExchange, ServerResponse.Context, Mono<Void>> savePerson(Mono<Person> person) {
        return (serverWebExchange, context) -> null;
    }

    public Mono<ServerResponse> getPerson(int personId) {
        return new Mono<>() {
            @Override
            public void subscribe(CoreSubscriber<? super ServerResponse> coreSubscriber) {
                System.out.println(coreSubscriber);
            }
        };
    }
}
