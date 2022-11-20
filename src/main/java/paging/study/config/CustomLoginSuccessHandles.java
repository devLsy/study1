package paging.study.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Set;

@Slf4j
public class CustomLoginSuccessHandles implements AuthenticationSuccessHandler {
    private RequestCache requestCache = new HttpSessionRequestCache();
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    private final String DEFAULT_LOGIN_SUCCESS_URL = "/";

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        
        clearAuthenticationAttributes(request);
        redirectStrategy(request, response, authentication);
    }

    // redirect
    private void redirectStrategy(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        log.info("여기 오기는 오나?");
        SavedRequest savedRequest = requestCache.getRequest(request, response);

//        if (savedRequest == null) {
//            redirectStrategy.sendRedirect(request, response, DEFAULT_LOGIN_SUCCESS_URL);
//        } else {
//            Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
//            log.info("redirectStrategy > roles ::::::::::::::: " + roles);
//            if ("SUPER_ADMIN".equals(roles)) {
//                redirectStrategy.sendRedirect(request, response, "/admin/main");
//            } else if ("MEMBER".equals(roles)) {
//                redirectStrategy.sendRedirect(request, response, "/user/main");
//            }
//        }
        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
            log.info("redirectStrategy > roles ::::::::::::::: " + roles);
            if ("SUPER_ADMIN".equals(roles)) {
                redirectStrategy.sendRedirect(request, response, "/admin/main");
            } else if ("MEMBER".equals(roles)) {
                redirectStrategy.sendRedirect(request, response, "/user/main");
            }
    }

    // 세션에 남아 있는 로그인 실패 이력 삭제
    private void clearAuthenticationAttributes(HttpServletRequest request) {
        log.info("clearAuthenticationAttributes::::::::::::::::::::::::");
        HttpSession session = request.getSession(false);
        if (session != null) {
                session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
        }
    }




}
