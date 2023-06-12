package com.nchu.software.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 通用返回结果类，服务端响应的数据最终都会封装成此对象
 *
 * @param <T>
 */
@Data
@ApiModel("返回结果")
public class Result<T> implements Serializable { // implements是为了实现redis缓存，序列化

    @ApiModelProperty("编码")
    private Integer code; //编码：200成功，0和其它数字为失败

    @ApiModelProperty("错误信息")
    private String msg; //错误信息

    private T data; //数据

    /**
     * 成功
     *
     * @param object
     * @param <T>
     * @return
     */
    public static <T> Result<T> success(T object) {
        Result<T> r = new Result<T>();
        r.data = object;
        r.code = 200;
        return r;
    }

    /**
     * 成功带对应的响应结果
     *
     * @param object
     * @param <T>
     * @return
     */
    public static <T> Result<T> success(T object, String msg) {
        Result<T> r = new Result<T>();
        r.data = object;
        r.msg = msg;
        r.code = 200;
        return r;
    }

    /**
     * 失败
     *
     * @param msg
     * @param <T>
     * @return
     */
    public static <T> Result<T> error(String msg) {
        Result r = new Result();
        r.msg = msg;
        r.code = 0;
        return r;
    }

}
