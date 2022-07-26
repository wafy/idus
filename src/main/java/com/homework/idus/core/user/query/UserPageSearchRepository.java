package com.homework.idus.core.user.query;

import com.homework.idus.core.user.command.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPageSearchRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

}
