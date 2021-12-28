package com.dwibagus.springsecurity.response;

import org.springframework.stereotype.Component;

@Component
public class CommonResponseGenerator<T> {

    public <T> CommonResponse<T> successResponse(T data, String message){
        CommonResponse commonResponse = new CommonResponse<>();
        commonResponse.setStatus("200");
        commonResponse.setMessage(message);
        commonResponse.setData(data);

        return commonResponse;
    }
}
