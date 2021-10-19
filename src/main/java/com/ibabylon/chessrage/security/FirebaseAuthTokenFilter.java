package com.ibabylon.chessrage.security;

import com.google.common.base.Strings;
import com.ibabylon.chessrage.security.model.FirebaseAuthToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FirebaseAuthTokenFilter extends AbstractAuthenticationProcessingFilter {

    private final static String TOKEN_HEADER = "X-Firebase-Auth";

    public FirebaseAuthTokenFilter(){
        super("/api/**");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws AuthenticationException, IOException, ServletException {
        final String token  = httpServletRequest.getHeader(TOKEN_HEADER);

        if(Strings.isNullOrEmpty(token)){
            throw new RuntimeException("Unauthorized");
        }
        return getAuthenticationManager().authenticate(new FirebaseAuthToken(token));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult)
            throws IOException, ServletException {
        super.successfulAuthentication(request, response, chain, authResult);
        chain.doFilter(request, response);
    }
}
