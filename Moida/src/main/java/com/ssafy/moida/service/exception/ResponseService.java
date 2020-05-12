package com.ssafy.moida.service.exception;

import org.springframework.stereotype.Service;

import com.ssafy.moida.exception.BaseException;
import com.ssafy.moida.service.response.CommonResult;

@Service
public class ResponseService {
    public CommonResult getFailResult(BaseException e) {
        CommonResult result = new CommonResult();
        result.setSuccess(false);
        result.setCode(e.getCode());
        result.setMsg(e.getDesc());
        return result;
    }
}
