package cn.ff.auth.constant;

import lombok.Data;

@Data
public class TokenProperty {
    private String secret;
    private long expired;
    private long refreshExpired;
}
