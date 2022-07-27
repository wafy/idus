package com.homework.idus.web.v1.admin.search.query;

import com.homework.idus.axiom.target.ForAdmin;
import com.homework.idus.core.exception.UserNotFountException;
import com.homework.idus.core.user.command.User;
import com.homework.idus.core.user.query.UserPageSearcher;
import com.homework.idus.core.user.query.UserSearcher;
import com.homework.idus.web.v1.admin.UnauthorizedException;
import com.homework.idus.web.v1.axiom.ApiResponseModel;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"3. 회원정보 조회"})
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
    @Operation(summary = "단일회원 조회", description = "회원의 상세 정보를 조회 합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "회원 조회 성공",
                    content = @Content(schema = @Schema(implementation = UserSearchResponse.class))),
            @ApiResponse(responseCode = "401", description = "토큰 정보 없이 조회",
                    content = @Content(schema = @Schema(implementation = UnauthorizedException.class))),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 회원번호로 조회",
                    content = @Content(schema = @Schema(implementation = UserNotFountException.class)))})

    public ApiResponseModel<?> findByUserNo(@PathVariable Long userNo,
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
     * 여려명의 사용자 정보를 조회해 리턴합니다.
     *
     * @param request
     * @return 조회 결과 사용자 목록
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "회원목록", description = "등록된 회원을 목록 조회 합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "회원 조회 성공",
                    content = @Content(schema = @Schema(implementation = UserSearchResponse.class))),
            @ApiResponse(responseCode = "405", description = "토큰 정보 없이 조회",
                    content = @Content(schema = @Schema(implementation = UnauthorizedException.class)))})
    public Page<UserSearchResponse> listUser(@ModelAttribute UserSearchRequest request,
                                             @AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails == null) {
            throw new UnauthorizedException("토큰정보가 누락되었습니다.");
        }


        Page<User> users = userPageSearcher.findAll(request);
        return users.map(UserSearchResponse::new);
    }
}
