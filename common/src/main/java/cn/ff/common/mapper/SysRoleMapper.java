package cn.ff.common.mapper;

import cn.ff.common.entity.SysRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface SysRoleMapper extends BaseMapper<SysRole> {
    /**
     * 获取一个用户的所有角色码
     */
    @Deprecated
    List<String> getOneUserRoleCodes(String userId);

    /**
     * 获取一个用户的所有角色
     */
    List<SysRole> getOneUserRoles(String userId);
}
