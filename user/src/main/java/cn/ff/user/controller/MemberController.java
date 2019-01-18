package cn.ff.user.controller;

import cn.ff.common.entity.R;
import cn.ff.common.utils.Rs;
import cn.ff.user.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 会员管理
 */
@RestController
@RequestMapping("member")
public class MemberController {

    @Autowired
    private MemberService memberService;
    @GetMapping("/{id}")
    public R oneInfo(@PathVariable String id){

        return Rs.success(memberService.oneInfo(id));

    }
}
