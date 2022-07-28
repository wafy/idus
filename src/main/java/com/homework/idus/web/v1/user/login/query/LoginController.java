package com.homework.idus.web.v1.user.login.query;

import com.homework.idus.axiom.target.ForUser;
import com.homework.idus.core.exception.UserNotFountException;
import com.homework.idus.core.user.command.User;
import com.homework.idus.core.user.query.UserSearcher;
import com.homework.idus.filter.JwtTokenProvider;
import com.homework.idus.web.v1.axiom.ApiResponseModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Tag(name =  "로그인")
@RestController
@RequestMapping("/v1/user")
@RequiredArgsConstructor
public class LoginController implements ForUser {

    private final UserSearcher userSearcher;

    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "로그인", description = "로그인 처리를 담당합니다." )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "로그인 성공"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND")
    })
    public ApiResponseModel<LoginResponse> login(@Valid @RequestBody LoginRequest request) {

        User user = userSearcher.findByName(request)
                .orElseThrow(() -> new UserNotFountException("로그인 정보를 다시 확인해주세요."));

        String accessToken = jwtTokenProvider.createToken(user.getName(), user.getRoles());
        return new ApiResponseModel<>(new LoginResponse(accessToken));

    }


}
