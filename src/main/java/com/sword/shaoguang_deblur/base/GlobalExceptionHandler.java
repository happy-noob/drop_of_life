package com.sword.shaoguang_deblur.base;/*
 *    Create By Yelson Li on 2021/4/20.
 *
 */

import com.sword.shaoguang_deblur.def.ExceptionCodeEnum;
import com.sword.shaoguang_deblur.def.ShaoGuangDeblurException;
import com.sword.shaoguang_deblur.def.ValidatorException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//全局异常兜底，自动调用统一封装结果集，本质还是调用ResultVO
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    //参数校验异常
    @ExceptionHandler(ValidatorException.class)
    public ResultVO<ExceptionCodeEnum> handleValidatorException(ValidatorException e) {
        // 打印精确的参数错误日志，方便后端排查
        log.warn("参数校验异常: {}", e.getMessage(), e);
        return ResultVO.error(ExceptionCodeEnum.ERROR_PARAM);
    }

    //业务异常
    @ExceptionHandler(ShaoGuangDeblurException.class)
    @ResponseStatus(code = HttpStatus.OK)
    public ResultVO<ExceptionCodeEnum> joinOAuthExceptionHandler(ShaoGuangDeblurException shaoGuangDeblurException) {
        log.error("业务异常：{}", shaoGuangDeblurException.getCodeEnum().getDesc(), shaoGuangDeblurException.getCodeEnum());
        return ResultVO.error(shaoGuangDeblurException.getCodeEnum());
    }

    //处理全局兜底异常
    @ExceptionHandler(Exception.class)
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResultVO<ExceptionCodeEnum> defaultExceptionHandler(Exception runtimeException) {
        log.error("运行时异常：{}", runtimeException.getMessage(), runtimeException);
        return ResultVO.error(ExceptionCodeEnum.SYSTEM_BUSY);
    }
// todo
    /**
     * ConstraintViolationException异常（散装GET参数校验）
     *
     * @param e
     * @return
     *
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResultVO<ExceptionCodeEnum> handleConstraintViolationException(ConstraintViolationException e) {
        log.warn("参数错误: {}", e.getMessage(), e);
        return ResultVO.error(ExceptionCodeEnum.ERROR_PARAM, e.getMessage());
    }

    /**
     * BindException异常（GET DTO校验）
     *
     * @param e
     * @return
     */
    @ExceptionHandler(BindException.class)
    public ResultVO<Map<String, String>> validationBindException(BindException e) {
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        String message = fieldErrors.stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.joining(" && "));
        log.error("参数错误: {}", message, e);
        return ResultVO.error(ExceptionCodeEnum.ERROR_PARAM, message);
    }

    /**
     * MethodArgumentNotValidException异常（POST DTO校验）
     *
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultVO<Map<String, String>> validationMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        String message = fieldErrors.stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.joining(" && "));
        log.error("参数错误: {}", message, e);
        return ResultVO.error(ExceptionCodeEnum.ERROR_PARAM, message);
    }
}
