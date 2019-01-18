package cn.ff.auth.helper;

import cn.ff.auth.constant.SecurityConstant;
import cn.ff.auth.token.PhoneAuthenticationToken;
import cn.ff.auth.token.QrAuthenticationToken;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义登陆filter
 *
 * @author ff at 20190108
 */
public class DefaultLoginAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    public static final String TYPE_PHONE = "phone";
    public static final String TYPE_QR = "qr";
    public static final String TYPE_DEFAULT = "user";

    /**
     * 登陆类型： user:用户密码登陆；phone:手机验证码登陆；qr:二维码扫码登陆
     */
    private static final String KEY_LOGIN_TYPE = "type";



    // 登陆终端：1：移动端登陆，包括微信公众号、小程序等；0：PC后台登陆
    private static final String KEY_MOBILE = "mobile";

    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_PHONE = "phone";
    private static final String KEY_VERIFY_CODE = "verifyCode";
    private static final String KEY_QR_CODE = "qrCode";
    private boolean postOnly = true;

    public DefaultLoginAuthenticationFilter() {
        super(new AntPathRequestMatcher(SecurityConstant.URL_LOGIN, "POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (postOnly && !request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException( "Authentication method not supported: " + request.getMethod());
        }
        String type = obtainParameter(request, KEY_LOGIN_TYPE);
        String mobile = obtainParameter(request, KEY_MOBILE);
        AbstractAuthenticationToken authRequest;
        String principal;
        String credentials = null;
        // 手机验证码登陆
        if(TYPE_PHONE.equals(type)){
            principal = obtainParameter(request, KEY_PHONE);
            credentials = obtainParameter(request, KEY_VERIFY_CODE);
            principal = principal.trim();
            authRequest = new PhoneAuthenticationToken(principal, credentials);
        }
        // 二维码扫码登陆
        else if(TYPE_QR.equals(type)){
            principal = obtainParameter(request, KEY_QR_CODE);
            authRequest = new QrAuthenticationToken(principal, credentials);
        }
        // 账号密码登陆
        else {
            principal = obtainParameter(request, KEY_USERNAME);
            credentials = obtainParameter(request, KEY_PASSWORD);
            principal = principal.trim();
            authRequest = new UsernamePasswordAuthenticationToken(principal, credentials);
        }
        // Allow subclasses to set the "details" property
        setDetails(request, authRequest);
        return this.getAuthenticationManager().authenticate(authRequest);
    }

    private void setDetails(HttpServletRequest request,AbstractAuthenticationToken authRequest) {
        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
    }

    private String obtainParameter(HttpServletRequest request, String parameter) {
        String result =  request.getParameter(parameter);
        return result == null ? "" : result;
    }

}
