package com.homework.idus.core.user.query;

import com.homework.idus.core.user.command.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserSearcherRepository extends JpaRepository<User, Long> {

    Optional<User> findByName(String name);

    long countByName(String name);

    Optional<User> findByUserNo(Long userNo);


}
