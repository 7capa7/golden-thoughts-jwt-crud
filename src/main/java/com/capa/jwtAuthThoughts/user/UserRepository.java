package com.capa.jwtAuthThoughts.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    //dzieki jpaRepository mamy metody jak save find all itp
    Optional<User> findByEmail(String email);

}
