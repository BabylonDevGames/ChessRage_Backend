package com.ibabylon.chessrage.helpers;

import com.ibabylon.chessrage.dto.response.ApiError;
import com.ibabylon.chessrage.dto.response.BaseApiResponse;

public class ApiErrorMessageBuilder {
    public static void buildErrorMessage(BaseApiResponse res,String errorCode){

        ApiError error = new ApiError();
        error.setErrorCode(errorCode);
        res.setError(error);

        switch (res.getError().getErrorCode()){
            case AuthErrorCodes.UserNotFound:
                res.getError().setMessage("User Not Found");
                break;
            case AuthErrorCodes.InvalidToken:
                res.getError().setMessage("Invalid Token");
                break;
            default:
                res.getError().setMessage("Unknown Error");

        }
    }
}
