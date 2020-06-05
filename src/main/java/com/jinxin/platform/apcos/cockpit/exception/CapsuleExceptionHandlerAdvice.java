package com.jinxin.platform.apcos.cockpit.exception;

import com.jinxin.platform.apcos.cockpit.pojo.vo.result.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;

@Slf4j
@RestControllerAdvice
public class CapsuleExceptionHandlerAdvice {

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseResult handException(Exception e) {
        log.error("出异常了-->{}", e);
        return new ResponseResult( HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
    }
    @ExceptionHandler(value = DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseResult handException(DataIntegrityViolationException e) {
        log.error("出异常了-->{}", e);
        return new ResponseResult( HttpStatus.INTERNAL_SERVER_ERROR.value(), "新的数据违反了完整性约束");
    }
    @ExceptionHandler(value = SQLException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseResult handException(SQLException e) {
        log.error("出异常了-->{}", e);
        return new ResponseResult( HttpStatus.INTERNAL_SERVER_ERROR.value(), "数据库错误");
    }
}
