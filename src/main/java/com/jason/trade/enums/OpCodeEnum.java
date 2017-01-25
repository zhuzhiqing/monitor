package com.jason.trade.enums;

/**
 * Created by jason on 17/1/24.
 */
public enum OpCodeEnum {

    SUCCESS("成功",0),
    FAILED_PARAM_ERROR("参数错误",-100),
    FAILED_INTERNAL("内部调用错误", -101),
    FAILED_NO_GOODS("链路正常无房态",-102),
    FAILED_NO_GOODSID("查询不到产品ID",-905),
    FAILED_OTA_NO_DATA("访问直连异常",-904),
    FAILED_TDC_NO_DATA("访问TDC异常",-907),
    FAILED_SQL_NO_DATA("访问数据库异常",-908);

    // 成员变量
    private String msg;
    private int code;

    private OpCodeEnum(String msg, int code){
        this.msg = msg;
        this.code = code;
    }

    // 普通方法
    public static String getMsg(int code) {
        for (OpCodeEnum c : OpCodeEnum.values()) {
            if (c.getCode() == code) {
                return c.msg;
            }
        }
        return null;
    }
    // get set 方法
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }
}
