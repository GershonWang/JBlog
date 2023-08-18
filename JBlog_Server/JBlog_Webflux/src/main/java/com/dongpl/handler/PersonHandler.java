package com.dongpl.handler;

import com.dongpl.entity.Person;
import com.dongpl.repository.PersonRepository;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

@Component
public class PersonHandler {

    @Resource
    private PersonRepository personDao;

    public Mono<ServerResponse> listPeople(ServerRequest request) {
        Flux<Person> people = personDao.listAllPeople();
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(people, Person.class);
    }

    public Mono<ServerResponse> createPerson(ServerRequest request) {
        Mono<Person> person = request.bodyToMono(Person.class);
        return ServerResponse.ok()
                .build(personDao.savePerson(person));
    }

    public Mono<ServerResponse> getPerson(ServerRequest request) {
        int personId = Integer.parseInt(request.pathVariable("id"));
        return personDao.getPerson(personId)
                .flatMap(person -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).bodyValue(person))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

}
