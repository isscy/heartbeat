package cn.ff.common.utils;

import cn.ff.common.entity.R;

public class Rs {


    public static R success(Object data){
        return new R().success(data);
    }

    public static R fail(String msg){
        return new R().fail(msg);
    }
}
