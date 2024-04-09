package com.osu.venglar.EventHarbor.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

//Filter will be used at every request
@Component
@RequiredArgsConstructor
//Extended class grants filtering on every request
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService; //Service for manipulating with token
    //Already an interface
    private final UserDetailsService userDetailsService;

    //Override required by OncePerRequestFilter
    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userEmail;
        if (authHeader == null || !authHeader.startsWith("Bearer ")){
            filterChain.doFilter(request, response);
            return;
        }
        //Extraction of the header
        jwt = authHeader.substring(7); //Count of chars at Bearer (space)
        userEmail = jwtService.extractUsername(jwt); //Extraction of the username from the token
        //User is not null and user is not authenticated yet
        if(userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null){
            //Getting user from the database (I can name it User, because User extends UserDetails)
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
            //Validation if the token is still valid or not
            if(jwtService.isTokenValid(jwt, userDetails)){
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
            //Passing req and resp to the next filter
            filterChain.doFilter(request, response);
        }

    }
}
