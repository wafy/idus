package com.homework.idus.web.v1.user.login.query;

import com.homework.idus.axiom.target.ForUser;
import com.homework.idus.core.exception.UserNotFountException;
import com.homework.idus.core.user.command.User;
import com.homework.idus.core.user.query.UserSearcher;
import com.homework.idus.filter.JwtTokenProvider;
import com.homework.idus.web.v1.axiom.ApiResponseModel;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@Api(tags = {"2. 로그인"})
@RestController
@RequestMapping("/v1/user")
@RequiredArgsConstructor
public class LoginController implements ForUser {

    private final UserSearcher userSearcher;

    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponseModel<?> login(@Valid @RequestBody LoginRequest request) {
        User user = userSearcher.findByName(request)
                .orElseThrow(() -> new UserNotFountException("로그인 정보를 다시 확인해주세요."));

        return ApiResponseModel.builder()
                .status(true)
                .data(new LoginResponse(jwtTokenProvider.createToken(user.getName(), user.getRoles())))
                .build();
    }
}
