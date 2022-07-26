package com.homework.idus.core.user;

import com.homework.idus.axiom.target.ForTestOnly;
import com.homework.idus.core.user.command.UserCreator;
import com.homework.idus.core.user.query.UserSearcher;
import com.homework.idus.core.user.query.UserSearcherRepository;
import lombok.AccessLevel;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;


public abstract class UserCrudSupplier implements ForTestOnly {

    @Autowired
    @Getter(AccessLevel.PROTECTED)
    private UserRepository userRepository;

    @Autowired
    @Getter(AccessLevel.PROTECTED)
    private UserSearcherRepository userSearcherRepository;


    private UserCreator userCreator;

    private UserSearcher userSearcher;


    protected UserCreator getUserCreator() {
        return userCreator == null ? new UserCreator(userRepository, userSearcherRepository): userCreator;
    }

    protected UserSearcher getUserSearcher() {
        return userSearcher == null ? new UserSearcher(userSearcherRepository) : userSearcher;
    }

    protected void getUserDeleteAll() {
        userRepository.deleteAll();
    }

}
