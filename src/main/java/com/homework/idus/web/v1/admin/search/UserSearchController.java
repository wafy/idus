package com.homework.idus.web.v1.admin.search;

import com.homework.idus.axiom.target.ForAdmin;
import com.homework.idus.core.user.command.User;
import com.homework.idus.core.user.query.UserSearcher;
import com.homework.idus.web.v1.axiom.ApiResponseModel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/admin")
@RequiredArgsConstructor
public class UserSearchController implements ForAdmin {

    private final UserSearcher userSearcher;

    /**
     * 사용자 정보를 상세 조회합니다.
     * @param userNo
     * @return
     */
    @GetMapping("/search/{userNo}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponseModel<?> findByUserNo(@PathVariable Long userNo) {
        User user = userSearcher.findByUserNo(userNo);

        return ApiResponseModel.builder()
                .status(true)
                .data(new UserSearchResponse(user))
                .build();
    }
}
