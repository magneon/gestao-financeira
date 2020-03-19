package br.com.softcube.expensemanagement.filter;

import br.com.softcube.expensemanagement.daos.UserDao;
import br.com.softcube.expensemanagement.models.User;
import br.com.softcube.expensemanagement.services.security.TokenService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationTokenFilter extends OncePerRequestFilter {

    private TokenService serviceToken;
    private UserDao daoUser;

    public AuthenticationTokenFilter(TokenService serviceToken, UserDao daoUser) {
        this.serviceToken = serviceToken;
        this.daoUser = daoUser;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String token = getTokenFromRequest(httpServletRequest);

        if(serviceToken.isValid(token)) {
            authenticateUserWith(token);
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private void authenticateUserWith(String token) {
        Long userId = serviceToken.getUserId(token);

        User user = daoUser.findById(userId).get();

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(usernamePasswordAuthenticationToken);
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader == null || authorizationHeader.isEmpty() || !(authorizationHeader.startsWith("Bearer"))) {
            return null;
        }

        return authorizationHeader.substring(7);
    }
}
