package com.ibabylon.chessrage.controller.api;


import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.ibabylon.chessrage.controller.request.SandBoxUserAuthRq;
import com.ibabylon.chessrage.dto.response.BaseApiResponse;
import com.ibabylon.chessrage.helpers.AuthErrorCodes;
import com.ibabylon.chessrage.helpers.ApiErrorMessageBuilder;
import com.ibabylon.chessrage.service.FireBaseService;
import com.ibabylon.chessrage.service.SandBoxUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequestMapping("/auth")

public class AuthController {

    @Autowired
    private FireBaseService fireBaseService;

    @Autowired
    private SandBoxUserService sandBoxUserService;


    @PostMapping("/SignInSandBoxUser")
    public BaseApiResponse SignInSandBoxUser(@RequestBody @Valid SandBoxUserAuthRq rq) {
        BaseApiResponse res = new BaseApiResponse();

        try {
            FirebaseToken token = fireBaseService.decodeToken(rq.getToken());

            Boolean userExist = sandBoxUserService.userExist(token.getEmail());

            if (userExist) {
                res.setSuccess(true);
                res.setResult(token);
            } else {
                ApiErrorMessageBuilder.buildErrorMessage(res, AuthErrorCodes.UserNotFound);
            }

        } catch (FirebaseAuthException e) {
            ApiErrorMessageBuilder.buildErrorMessage(res, AuthErrorCodes.InvalidToken);
        } catch (Exception e) {
            ApiErrorMessageBuilder.buildErrorMessage(res, AuthErrorCodes.UnknownError);
        }
        return res;
    }
}
