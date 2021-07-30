package com.sword.shaoguang_deblur.def;/*
 *    Create By Yelson Li on 2021/4/19.
 *
 */

import lombok.Getter;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Getter
public enum ExceptionCodeEnum {
    /**
     * 服务器异常
     */

    SYSTEM_BUSY(-1,"系统繁忙"),

    /**
     * DB出现异常
     */
    DUPLICATE_DATA(15020,"插入了重复数据"),
    INVALID_DELETD(15021,"删除失败，无权限"),
    DATA_NOT_FOUND(15022,"数据不存在"),
    ACCOUNT_REPREAT(15023,"账户号已存在"),
    ERROR_INSERT(15024,"注册失败，请重新注册"),

    /**
     * 参数校验
     */
    EMPTY_PARAM(15031,"参数为空"),
    ERROR_PARAM(15030, "参数错误"),
    ERROR_PARAM_LENGTH(15032, "参数长度错误"),
    COLLECTION_EMPTY(15033,"集合不能为空"),

    /**
     * 验证码有问题
     */
    ERROR_VERCODE(15041,"验证码无效或有误"),


    /**
     * 通用结果
     */
    SUCCESS(0, "OK");



    private final Integer code;
    private final String desc;

    ExceptionCodeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    private static final Map<Integer, ExceptionCodeEnum> ENUM_CACHE = new HashMap<>();

    static {
        for (ExceptionCodeEnum exceptionCodeEnum : ExceptionCodeEnum.values()) {
            ENUM_CACHE.put(exceptionCodeEnum.code, exceptionCodeEnum);
        }
    }

    public static String getDesc(Integer code) {
        return Optional.ofNullable(ENUM_CACHE.get(code))
                .map(ExceptionCodeEnum::getDesc)
                .orElseThrow(() -> new IllegalArgumentException("invalid exception code!"));
    }


}
