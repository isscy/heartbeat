package cn.ff.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("sys_user_role")
public class SysUserRole implements Serializable {
    private String id;
    private String userId;
    private int roleId;
    private Date relTime;
}
