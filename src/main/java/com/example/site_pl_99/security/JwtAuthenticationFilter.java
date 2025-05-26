package com.example.site_pl_99.security;

import com.example.site_pl_99.excaption.AuthorizeException;
import com.example.site_pl_99.service.AuthService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

@Slf4j
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JWTHandler jwtHandler;
    private final AuthService  authService;

    public JwtAuthenticationFilter(
            JWTHandler jwtHandler,
            AuthService authService
    ) {
        this.jwtHandler = jwtHandler;
        this.authService = authService;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        String autheader = parseJwt(request);


        if(Objects.nonNull(autheader) && jwtHandler.validateToken(autheader)) {
            String username = jwtHandler.extractUsernameFromToken(autheader);
            UserDetails userDetails = authService.loadUserByUsername(username);
            UsernamePasswordAuthenticationToken authenticationToken;
            authenticationToken = UsernamePasswordAuthenticationToken.authenticated(
                    userDetails,null,userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        filterChain.doFilter(request, response);
    }

    private String parseJwt(HttpServletRequest request){
        final String authHeader = request.getHeader("Authorization");
        log.info("------->>>>> {}", authHeader);

        if(Objects.nonNull(authHeader) ){
            if(authHeader.contains("Basic ")) {
                log.info("Вернул  ------->>>>>  {}", authHeader);
                throw  new AuthorizeException("Ошибка авторизации ");
            }

            if(authHeader.startsWith("Bearer ") ) {
                return authHeader.substring(7);
            }
         }
        return null;
    }
}
