package com.pht.drools.entity.drl;

public enum TestEnum {
    SPRING("code","a11","22");

    private String code;
    private String  name;
    private  String age;

    TestEnum(String code, String name, String age) {
        this.code = code;
        this.name = name;
        this.age = age;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
