package com.homework.idus.web.v1.admin.search.query;

import com.homework.idus.core.user.Gender;
import com.homework.idus.core.user.command.User;
import lombok.Getter;

/**
 * 사용자 정보 응답을 담당합니다.
 */
@Getter
public class UserSearchResponse {
    private String name;
    private String nickname;
    private String email;
    private String mobilePhoneNo;
    private Gender gender;
    private OrderSearchResponse order;

    public UserSearchResponse(User user) {
        this.name = user.getName();
        this.nickname = user.getNickname();
        this.email = user.getEmail();
        this.mobilePhoneNo = user.getMobilePhoneNo();
        this.gender = user.getGender();
        // TODO: 최근 주문정보 기능 미구현
        //this.order = new OrderSearchResponse(user.getOrders().get(0));
        this.order =null;
    }
}
