package com.homework.idus.core.user;

import com.homework.idus.axiom.target.ForTestOnly;
import com.homework.idus.core.order.query.*;
import com.homework.idus.core.user.command.UserCreator;
import com.homework.idus.core.user.command.UserRepository;
import com.homework.idus.core.user.query.UserPageSearchRepository;
import com.homework.idus.core.user.query.UserPageSearcher;
import com.homework.idus.core.user.query.UserSearcher;
import com.homework.idus.core.user.query.UserSearcherRepository;
import lombok.AccessLevel;
import lombok.Getter;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;


public abstract class TestSupplier implements ForTestOnly {

    @Autowired
    @Getter(AccessLevel.PROTECTED)
    private UserRepository userRepository;

    @Autowired
    @Getter(AccessLevel.PROTECTED)
    private UserSearcherRepository userSearcherRepository;

    @Autowired
    @Getter(AccessLevel.PROTECTED)
    private UserPageSearchRepository userPageSearchRepository;

    @Autowired
    @Getter(AccessLevel.PROTECTED)
    private OrderPageRepository orderPageRepository;

    private UserCreator userCreator;

    private UserSearcher userSearcher;

    private UserPageSearcher userPageSearcher;

    private OrderPageSearcher orderPageSearcher;


    protected UserCreator getUserCreator() {
        return userCreator == null ? new UserCreator(userRepository, userSearcherRepository): userCreator;
    }

    protected UserSearcher getUserSearcher() {
        return userSearcher == null ? new UserSearcher(userSearcherRepository) : userSearcher;
    }

    protected UserPageSearcher getUserPageSearcher() {
        return userPageSearcher == null ? new UserPageSearcher(userPageSearchRepository) : userPageSearcher;
    }

    protected void getUserDeleteAll() {
        userRepository.deleteAll();
    }

    protected void getOrderDeleteAll() {
        orderPageRepository.deleteAll();
    }

    protected OrderPageSearcher getOrderPageSearcher() {
        return orderPageSearcher == null ? new OrderPageSearcher(orderPageRepository) : orderPageSearcher;
    }

    protected Order getOrderSave(Order order) {
        return orderPageRepository.save(order);
    }

}
