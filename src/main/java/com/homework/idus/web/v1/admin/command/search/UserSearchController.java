package com.homework.idus.web.v1.admin.command.search;

import com.homework.idus.axiom.target.ForAdmin;
import com.homework.idus.core.user.command.User;
import com.homework.idus.core.user.query.UserPageSearcher;
import com.homework.idus.core.user.query.UserSearcher;
import com.homework.idus.web.v1.axiom.ApiResponseModel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/admin/user/search")
@RequiredArgsConstructor
public class UserSearchController implements ForAdmin {

    private final UserSearcher userSearcher;

    private final UserPageSearcher userPageSearcher;

    /**
     * 사용자 정보를 한명 조회해 리턴합니다.
     * @param userNo
     * @return 조회 결과 사용자
     */
    @GetMapping("/{userNo}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponseModel<?> findByUserNo(@PathVariable Long userNo) {
        User user = userSearcher.findByUserNo(userNo);

        return ApiResponseModel.builder()
                .status(true)
                .data(new UserSearchResponse(user))
                .build();
    }

    /**
     * 여려명의 사용자 정보를 조회해 리턴합니다.
     * @param request
     * @return 조회 결과 사용자 목록
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<UserSearchResponse> listUser(@ModelAttribute UserSearchRequest request,
                                             Pageable pageable) {

        Page<User> users = userPageSearcher.findAll(request, pageable);
        return users.map(UserSearchResponse::new);
    }
}
