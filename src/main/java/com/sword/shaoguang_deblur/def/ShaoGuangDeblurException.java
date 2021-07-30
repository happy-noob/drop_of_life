package com.sword.shaoguang_deblur.def;/*
 *    Create By Yelson Li on 2021/7/27.
 *
 */
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ShaoGuangDeblurException extends RuntimeException{
    private ExceptionCodeEnum codeEnum;

    /**
     * 构造器，有时我们需要将第三方异常转为自定义异常抛出，但又不想丢失原来的异常信息，此时可以传入cause
     *
     * @param codeEnum
     * @param cause
     */
    public ShaoGuangDeblurException(ExceptionCodeEnum codeEnum, Throwable cause) {
        super(cause);
        this.codeEnum = codeEnum;
    }

    /**
     * 构造器，只传入错误枚举
     *
     * @param codeEnum
     */
    public ShaoGuangDeblurException(ExceptionCodeEnum codeEnum) {
        this.codeEnum = codeEnum;
    }
}
