package com.example.demo.repository.db;

import com.example.demo.domain.db.DbUser;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface DbUserRepository extends ReactiveCrudRepository<DbUser, Integer> {

  Mono<DbUser> findByUsername(String username);

  @Query("SELECT count(*) > 0 FROM user u WHERE u.username = :username")
  Mono<Boolean> existsByUsernameQuery(String username);

  Mono<Boolean> existsByUsername(String username);
}
