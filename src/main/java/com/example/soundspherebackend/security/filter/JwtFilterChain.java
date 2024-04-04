package com.example.soundspherebackend.security.filter;

import com.example.soundspherebackend.model.Login;
import com.example.soundspherebackend.security.auth.TokenDataDTO;
import com.example.soundspherebackend.security.service.JwtService;
import com.example.soundspherebackend.service.LoginService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtFilterChain extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final LoginService loginService;


    @Override
    protected void doFilterInternal(
            @NotNull HttpServletRequest request,
            @NotNull HttpServletResponse response,
            @NotNull FilterChain filterChain
    ) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String username;

        //Si viene por una url "/auth" lo dejamos pasar
        if (request.getServletPath().contains("/auth")){
            filterChain.doFilter(request, response);
            return;
        }

        if (authHeader == null ||!authHeader.startsWith("Bearer")){
            filterChain.doFilter(request, response);
            return;
        }

        String token = authHeader.substring(7);
        TokenDataDTO tokenDataDTO = jwtService.extractTokenData(token);

        if (tokenDataDTO != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            Login login = loginService.buscarPorUsername(tokenDataDTO.getUsername());

            if (login != null && !jwtService.isTokenExpired(token)){
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    login.getUsername(),
                    login.getPassword(),
                    login.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
                }
        }
        filterChain.doFilter(request, response);
    }
}
