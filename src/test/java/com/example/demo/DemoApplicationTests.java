package com.example.demo;

import com.example.demo.domain.db.DbUser;
import com.example.demo.repository.db.DbUserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest
class DemoApplicationTests {

  final String missingName = "MissingName";
  final String existingName = "ExistingName";

  @Autowired
  DbUserRepository dbUserRepository;

  @Test
  void contextLoads() {
  }

  @Test
  void insertIntoDb() {
    StepVerifier
        .create(dbUserRepository.save(new DbUser(null, existingName)))
        .expectNextCount(1)
        .verifyComplete();

    testExisting(dbUserRepository.existsByUsernameQuery(existingName), true);
  }

  void testExistingFalse(Mono<Boolean> test) {
    testExisting(test, false);
  }

  void testExisting(Mono<Boolean> test, boolean res) {
    StepVerifier
        .create(test)
        .expectNext(res)
        .expectComplete()
        .verify();
  }

  @Test
  void existQuery() {
    testExistingFalse(dbUserRepository.existsByUsernameQuery(missingName));
  }

  @Test
  void existGenerated() {
    System.out.println(dbUserRepository.existsByUsername(missingName).block());
    testExistingFalse(dbUserRepository.existsByUsername(missingName));
  }

}
