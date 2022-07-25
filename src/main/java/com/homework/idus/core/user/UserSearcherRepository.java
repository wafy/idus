package com.homework.idus.core.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserSearcherRepository extends JpaRepository<User, Long> {

    Optional<User> findByNameAndPassword(String name, String password);
    Optional<User> findByName(String name);

    long countByName(String name);


}
