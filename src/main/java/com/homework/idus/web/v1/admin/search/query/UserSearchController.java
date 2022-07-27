package com.homework.idus.web.v1.admin.search.query;

import com.homework.idus.axiom.target.ForAdmin;
import com.homework.idus.core.user.command.User;
import com.homework.idus.core.user.query.UserPageSearcher;
import com.homework.idus.core.user.query.UserSearcher;
import com.homework.idus.web.v1.axiom.ApiResponseModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"3. 관리자회원조회"})
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
    @ApiOperation(value = "회원단건조회", notes = "단일회원 상세정보를 조회 합니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "정상응답")
    })
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
    @ApiOperation(value = "회원목록", notes = "회원 목록 조회 합니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "정상응답")
    })
    public Page<UserSearchResponse> listUser(@ModelAttribute UserSearchRequest request,
                                             Pageable pageable) {

        Page<User> users = userPageSearcher.findAll(request, pageable);
        return users.map(UserSearchResponse::new);
    }
}
