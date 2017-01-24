package com.jason.trade.common;

import com.google.common.base.Preconditions;
import com.jason.trade.enums.OpCodeEnum;

import java.io.Serializable;

/**
 * Created by jason on 17/1/23.
 */
public final class OpResult<T> implements Serializable {

    private final static int SUCCESS = 0;

    public static OpResult createSucRst() {
        return new OpResult(OpCodeEnum.SUCCESS);
    }

    public static <T> OpResult<T> createSucRst(T result) {
        return new OpResult<T>(OpCodeEnum.SUCCESS, result);
    }

    public static OpResult createFailRst(OpCodeEnum codeEnum) {
        return new OpResult(codeEnum);
    }

    public static <T> OpResult<T> createFailRst(OpCodeEnum codeEnum, T result) {
        return new OpResult<T>(codeEnum, result);
    }

    private OpResult(OpCodeEnum codeEnum) {
        Preconditions.checkNotNull(codeEnum);

        this.setCode(codeEnum.getCode());
        this.setMsg(codeEnum.getMsg());
    }

    private OpResult(OpCodeEnum codeEnum, T result) {
        Preconditions.checkNotNull(codeEnum);

        this.setCode(codeEnum.getCode());
        this.setMsg(codeEnum.getMsg());
        this.setResult(result);
    }

    public boolean isSucceed() {
        return this.getCode() == OpCodeEnum.SUCCESS.getCode();
    }

    private int code;

    private String msg;

    private T result;

    public int getCode() {
        return code;
    }

    public OpResult setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public OpResult setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public T getResult() {
        return result;
    }

    public OpResult setResult(T result) {
        this.result = result;
        return this;
    }

    @Override
    public String toString() {
        return "OpResult{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", result=" + result +
                '}';
    }
}
