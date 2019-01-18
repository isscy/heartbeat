package cn.ff.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("sys_role")
public class SysRole implements Serializable {

    private int id;
    private String code;
    private String name;
    private Integer level;
    private String dataScope;
    private String createBy;
    private Date createTime;
    private String delFlag;


}
