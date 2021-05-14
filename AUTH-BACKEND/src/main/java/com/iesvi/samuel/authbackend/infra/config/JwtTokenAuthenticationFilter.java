package com.iesvi.samuel.authbackend.infra.config;

import com.iesvi.samuel.authbackend.domain.model.InstaUserDetails;
import com.iesvi.samuel.authbackend.application.service.JwtTokenProvider;
import com.iesvi.samuel.authbackend.application.service.UserService;
import io.jsonwebtoken.Claims;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtTokenAuthenticationFilter extends OncePerRequestFilter {

    private final JwtConfig jwtConfig;
    private JwtTokenProvider tokenProvider;
    private UserService userService;

    public JwtTokenAuthenticationFilter(
            JwtConfig jwtConfig,
            JwtTokenProvider tokenProvider,
            UserService userService) {

        this.jwtConfig = jwtConfig;
        this.tokenProvider = tokenProvider;
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        // 1.Obtenemos el header del token de autenticación
        String header = request.getHeader(jwtConfig.getHeader());

        // 2.Validamos el header
        if (header == null || !header.startsWith(jwtConfig.getPrefix())) {
            chain.doFilter(request, response); // Si no es válido, se slata al siguiente filtro
            return;
        }

        // 3.Obtenemos el token
        String token = header.replace(jwtConfig.getPrefix(), "");

        if (tokenProvider.validateToken(token)) {
            Claims claims = tokenProvider.getClaimsFromJWT(token);
            String username = claims.getSubject();

            UsernamePasswordAuthenticationToken auth =
                    userService.findByUsername(username)
                            .map(InstaUserDetails::new)
                            .map(userDetails -> {
                                UsernamePasswordAuthenticationToken authentication =
                                        new UsernamePasswordAuthenticationToken(
                                                userDetails, null, userDetails.getAuthorities());
                                authentication
                                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                                return authentication;
                            })
                            .orElse(null);

            SecurityContextHolder.getContext().setAuthentication(auth);
        } else {
            SecurityContextHolder.clearContext();
        }

        chain.doFilter(request, response);
    }

}
