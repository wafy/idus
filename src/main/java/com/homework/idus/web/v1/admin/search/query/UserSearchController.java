package com.homework.idus.web.v1.admin.search.query;

import com.homework.idus.axiom.target.ForAdmin;
import com.homework.idus.core.user.command.User;
import com.homework.idus.core.user.query.UserPageSearcher;
import com.homework.idus.core.user.query.UserSearcher;
import com.homework.idus.web.v1.admin.UnauthorizedException;
import com.homework.idus.web.v1.axiom.ApiResponseModel;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@Tag(name = "회원조회")
@RestController
@RequestMapping("/v1/admin/user/search")
@RequiredArgsConstructor
public class UserSearchController implements ForAdmin {

    private final UserSearcher userSearcher;

    private final UserPageSearcher userPageSearcher;

    /**
     * 사용자 정보를 한명 조회해 리턴합니다.
     *
     * @param userNo
     * @return 조회 결과 사용자
     */
    @GetMapping("/{userNo}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "단일회원조회", description = "단일회원 조회를 담당합니다." )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "회원정보 조회"),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    public ApiResponseModel<?> findByUserNo(@PathVariable Long userNo,
                                            @Parameter(hidden = true)
                                            @AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails == null) {
            throw new UnauthorizedException("토큰정보가 누락되었습니다.");
        }

        User user = userSearcher.findByUserNo(userNo);

        return ApiResponseModel.builder()
                .status(true)
                .data(new UserSearchResponse(user))
                .build();
    }

    /**
     * 여러명의 사용자 정보를 조회해 리턴합니다.
     *
     * @param request
     * @return 조회 결과 사용자 목록
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "회원목록조히", description = "회원목록 조회를 담당합니다." )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "회원정보 조회"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "404", description = "Not Found")
    })
    public Page<UserSearchResponse> listUser(@ModelAttribute UserSearchRequest request,
                                             @Parameter(hidden = true)
                                             @AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails == null) {
            throw new UnauthorizedException("토큰정보가 누락되었습니다.");
        }

        Page<User> users = userPageSearcher.findAll(request);
        return users.map(UserSearchResponse::new);
    }
}
