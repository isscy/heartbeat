package cn.ff.common.constant;

public interface UserConstant {

    /**
     * 用户状态
     */
    String STATUS_0_INACTIVATED = "0"; // 未激活
    String STATUS_1_NORMAL = "1"; // 正常
    String STATUS_2_LOCK = "2"; // 锁定
    String STATUS_9_DELETE = "9"; // 注销

    /**
     * 用户类别
     */
    String TYPE_0_UNKNOWN = "0";
    String TYPE_1_NORMAL = "1";  // 普通用户
    String TYPE_2_VIP = "2";  // vip用户
    String TYPE_9_ADMIN = "9";  // 后台管理员账户
}
