package com.ibabylon.chessrage.dto.response;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class BaseApiResponse {
    private Boolean success = false;
    private Object result;
    private ApiError error;
}


