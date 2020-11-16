package com.pht.drools.entity.drl;

public class TestEnum1 {
    public static void main(String[] args) {
    }
}
enum tets{

    Test("1111"),SSS("asda"),AAA("asda");
    private String code;
    private String name;
    tets(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
