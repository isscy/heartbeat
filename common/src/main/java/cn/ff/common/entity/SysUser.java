package cn.ff.common.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@TableName("sys_user")
public class SysUser implements Serializable {

    private String id;
    private String userName;
    private String password;
    private String nickName;
    private String phone;
    private String type;
    private String status;
    private String delFlag;
    private Date createTime;

    @TableField(exist = false)
    private List<SysRole> roles;


}
