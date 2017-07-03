package com.pig.learn.mybatis.enums;

import com.pig.learn.mybatis.base.BaseEnum;

public enum MyEnum implements BaseEnum {

    A(1,"好好",""),
    B(2,"坏坏",""),
    C(3,"不对","");

    private int code;

    private String desc;

    private String extra;

    MyEnum(int code, String desc, String extra) {
        this.code = code;
        this.desc = desc;
        this.extra = extra;
    }

    public static MyEnum findByCode(Integer code) {
        for (MyEnum couponType : MyEnum.values()) {
            if (couponType.getCode() == code) {
                return couponType;
            }
        }
        return null;
    }
    @Override public int getCode() {
        return code;
    }

    @Override public String getDesc() {
        return desc;
    }

    @Override public String getExtra() {
        return extra;
    }

    @Override public String getName() {
        return name();
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("MyEnum{");
        sb.append("code=").append(code);
        sb.append(", desc='").append(desc).append('\'');
        sb.append(", extra='").append(extra).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public static void main(String[] args) {
        //返回的是枚举的字面名字
        System.out.println(MyEnum.A.getName());
    }
}
