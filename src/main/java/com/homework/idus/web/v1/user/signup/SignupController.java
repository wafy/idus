package com.homework.idus.web.v1.user.signup;

import com.homework.idus.core.user.command.User;
import com.homework.idus.core.user.command.UserCreator;
import com.homework.idus.web.v1.axiom.ApiResponseModel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/user")
@RequiredArgsConstructor
public class SignupController {

    private final UserCreator userCreator;

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponseModel signup(@Valid @RequestBody SignupRequest request) {
        User user = userCreator.create(User.of(request));
        return ApiResponseModel.builder()
                .status(true)
                .data(new SignupResponse(user))
                .build();
    }
}
