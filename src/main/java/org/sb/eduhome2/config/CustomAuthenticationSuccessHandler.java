package org.sb.eduhome2.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collection;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {



    @Override
    public void onAuthenticationSuccess(jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response, Authentication authentication) throws IOException, jakarta.servlet.ServletException {
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        String redirectUrl = "/";

        for (GrantedAuthority authority : authorities) {
            String authorityName = authority.getAuthority();
            if (authorityName.equals("ROLE_ADMIN")) {
                redirectUrl = "/admin";
                break;
            } else if (authorityName.equals("ROLE_MODERATOR")) {
                redirectUrl = "/admin";
                break;
            } else if (authorityName.equals("ROLE_USER")) {
                redirectUrl = "/";
                break;
            }
        }

        response.sendRedirect(request.getContextPath() + redirectUrl);
    }
}
