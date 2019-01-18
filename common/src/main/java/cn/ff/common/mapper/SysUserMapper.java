package cn.ff.common.mapper;

import cn.ff.common.entity.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

@Repository
public interface SysUserMapper extends BaseMapper<SysUser> {

    SysUser getByUserName(String userName);

    /**
     * 插入一条用户名不重复的 数据
     */
    int insertOneNoRepeatUserName(SysUser user);







}
