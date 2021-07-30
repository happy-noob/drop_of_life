package com.sword.shaoguang_deblur.def;/*
 *    Create By Yelson Li on 2021/4/25.
 *
 */

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ValidatorException extends RuntimeException{
    private Integer code;
    /**
     * 枚举异常对象,是返回给用户看到的，包括枚举异常对象的code，desc
     */
    private ExceptionCodeEnum e;

    /**
     * 系统源异常
     * @param e
     */
    private Exception originException;

    /**
     * 完整的构造函数：参数错误码+参数错误信息+源异常信息
     *
     * @param code            参数错误码
     * @param message         参数错误信息
     * @param originException 系统源异常
     */
    public ValidatorException(Integer code, String message, Exception originException) {
        super(message);
        this.code = code;
        this.originException = originException;
    }

    /**
     * 构造函数：错误枚举+源异常信息
     *
     * @param codeEnum
     */
    public ValidatorException(ExceptionCodeEnum codeEnum, Exception originException) {
        this(codeEnum.getCode(), codeEnum.getDesc(), originException);
    }

    /**
     * 构造函数：错误枚举
     *
     * @param codeEnum 错误枚举
     */
    public ValidatorException(ExceptionCodeEnum codeEnum) {
        this(codeEnum.getCode(), codeEnum.getDesc(), null);
    }

    /**
     * 构造函数：参数错误信息+源异常信息
     *
     * @param message         参数错误信息
     * @param originException 系统源错误
     */
    public ValidatorException(String message, Exception originException) {
        this(ExceptionCodeEnum.ERROR_PARAM.getCode(), message, originException);
    }

    /**
     * 构造函数：参数错误信息
     *
     * @param message 参数错误信息
     */
    public ValidatorException(String message) {
        this(ExceptionCodeEnum.ERROR_PARAM.getCode(), message, null);
    }

}
