package cn.ff.common.entity;

import com.google.gson.Gson;
import lombok.Data;

@Data
public class R {

    private int code;
    private String message;
    private Object data;

    public R() {
    }


    public R(int code, String message, Object data) {
        this(code, data);
        this.message = message;
    }

    public R(int code, Object data) {
        this();
        this.code = code;
        this.data = data;
    }

    public R success(Object data) {
        this.code = 1;
        this.data = data;
        return this;
    }

    public R fail() {
        this.code = -1;
        return this;
    }

    public R fail(int code, String msg) {
        this.code = code;
        this.message = msg;
        return this;
    }

    public R fail(String msg) {
        this.message = msg;
        return this;
    }

    public String asJson(){
        return new Gson().toJson(this);
    }

}
