package xyz.ibudai.authority.core.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import xyz.ibudai.authority.model.base.ResultData;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 监听异常请求并处理返回
     */
    @ExceptionHandler(Exception.class)
    public ResultData handleNotFoundException(Exception ex) {
        log.error(ex.getMessage(), ex);
        return ResultData.failed(ex.getMessage());
    }
}
