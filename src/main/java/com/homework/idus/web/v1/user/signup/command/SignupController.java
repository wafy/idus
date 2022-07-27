package com.homework.idus.web.v1.user.signup.command;

import com.homework.idus.axiom.target.ForUser;
import com.homework.idus.core.user.command.User;
import com.homework.idus.core.user.command.UserCreator;
import com.homework.idus.web.v1.axiom.ApiResponseModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = {"1. 회원가입"})
@RestController
@RequestMapping("/v1/user")
@RequiredArgsConstructor
public class SignupController implements ForUser {

    private final UserCreator userCreator;

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "회원가입", notes = "회원가입 처리를 합니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "정상응답"),
            @ApiResponse(code = 500, message = "비정상응답")
    })
    public ApiResponseModel signup(@Valid @RequestBody SignupRequest request) {
        User user = userCreator.create(User.of(request));
        return ApiResponseModel.builder()
                .status(true)
                .data(new SignupResponse(user))
                .build();
    }
}
