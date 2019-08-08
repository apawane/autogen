package com.autogen.autogen.todo.model;

public class Item {
    String location;
    String param;
    String msg;
    String value;

    public Item(String param, String msg, String value) {
        this.param = param;
        this.msg = msg;
        this.value = value;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getValue() {
        return value;
    }


    public void setValue(String value) {
        this.value = value;
    }
}
