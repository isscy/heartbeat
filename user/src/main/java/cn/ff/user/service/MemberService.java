package cn.ff.user.service;

import cn.ff.common.entity.SysUser;
import cn.ff.common.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
public class MemberService {
    @Autowired
    private SysUserMapper sysUserMapper;

    public Map<String, Object> oneInfo(String userId) {
        Map<String, Object> map = new HashMap<>();
        SysUser user = sysUserMapper.selectById(userId);
        if (user == null) {
            map.put("isMember", false);
        } else {
            map.put("isMember", true);
        }
        return map;
    }
}
