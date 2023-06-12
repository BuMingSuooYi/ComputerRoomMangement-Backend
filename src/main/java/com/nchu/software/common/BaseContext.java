package com.nchu.software.common;

/**
 * @Author JayHrn
 * @Date 2023-06-12 17:20
 * 基于ThreadLocal封装工具类，用于保存和获取当前登录用户id
 */
public class BaseContext {
    private static ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    /**
     * 设置值
     *
     * @param id
     */
    public static void setCurrentId(Long id) {
        threadLocal.set(id);
    }

    /**
     * 获取值
     *
     * @return
     */
    public static Long getCurrentId() {
        return threadLocal.get();
    }
}
