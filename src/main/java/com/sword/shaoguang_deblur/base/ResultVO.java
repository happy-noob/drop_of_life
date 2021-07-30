package com.sword.shaoguang_deblur.base;/*
 *    Create By Yelson Li on 2021/4/19.
 *
 */

import com.sword.shaoguang_deblur.def.ExceptionCodeEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class ResultVO<T> implements Serializable {

    private Integer code;
    private String msg;
    private T data;

    public ResultVO(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ResultVO(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
        this.data = null;
    }

    /**
     * 带数据成功返回
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ResultVO<T> success(T data) {
        return new ResultVO<T>(ExceptionCodeEnum.SUCCESS.getCode(), ExceptionCodeEnum.SUCCESS.getDesc(), data);
    }

    /**
     * 不带数据成功返回
     *
     * @return
     */
    public static <T> ResultVO<T> success() {
        return success(null);
    }

    /**
     * 通用错误返回
     *
     * @param exceptionCodeEnum
     * @return
     */
    public static <T> ResultVO<T> error(ExceptionCodeEnum exceptionCodeEnum) {
        return new ResultVO<T>(exceptionCodeEnum.getCode(), exceptionCodeEnum.getDesc());
    }

    /**
     * 通用错误返回
     *
     * @param exceptionCodeEnum
     * @param msg
     * @return
     */
    public static <T> ResultVO<T> error(ExceptionCodeEnum exceptionCodeEnum, String msg) {
        return new ResultVO<T>(exceptionCodeEnum.getCode(), msg);
    }

    /**
     * 通用错误返回
     *
     * @param exceptionCodeEnum
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ResultVO<T> error(ExceptionCodeEnum exceptionCodeEnum, T data) {
        return new ResultVO<T>(exceptionCodeEnum.getCode(), exceptionCodeEnum.getDesc(), data);
    }

}
