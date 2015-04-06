package com.freelxl.baselibrary.bean;

public class BaseJson {

    public String status;

    public int errCode;

    public String errMsg;

    public BaseJson(int errCode, String errMsg) {
        super();
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    public BaseJson() {
        super();
    }

}
