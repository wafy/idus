package com.homework.idus.web.v1.user.signup.command;

import com.homework.idus.axiom.target.ForUser;
import com.homework.idus.core.user.command.User;
import com.homework.idus.core.user.command.UserCreator;
import com.homework.idus.web.v1.axiom.ApiResponseModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Tag(name = "회원가입")
@RestController
@RequestMapping("/v1/user")
@RequiredArgsConstructor
public class SignupController implements ForUser {

    private final UserCreator userCreator;

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "회원가입", description = "회원가입 처리를 담당합니다." )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "회원가입 성공"),
            @ApiResponse(responseCode = "409", description = "CONFLICT")
    })
    public ApiResponseModel<?> signup(@Valid @RequestBody SignupRequest request) {
        User user = userCreator.create(User.of(request));
        return ApiResponseModel.builder()
                .status(true)
                .data(new SignupResponse(user))
                .build();
    }
}
