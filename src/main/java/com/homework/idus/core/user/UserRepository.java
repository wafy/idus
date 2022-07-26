package com.homework.idus.core.user;

import com.homework.idus.core.user.command.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    User save(User user);
}
