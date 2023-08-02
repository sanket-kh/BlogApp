package com.sanket.blogapp.Security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@AllArgsConstructor
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private UserDetailsService userDetailsService;
    private JwtTokenHelper jwtTokenHelper;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        //get token

        String requestToken=request.getHeader("Authorization");
        System.out.println(requestToken);
        //request token starts like "Bearer 899013-320"

        String userName=null;
        String token=null;

        if (requestToken!=null && requestToken.startsWith("Bearer")){
            token= requestToken.substring(7);
            try {
                userName=jwtTokenHelper.extractUsername(token);
            } catch (IllegalArgumentException e) {
                System.out.println("unable to get JWT token");
            }catch (ExpiredJwtException e){
                System.out.println("expired");
            }catch (MalformedJwtException e){
                System.out.println("invalid JWT token");
            }
        }else {
            System.out.println("JWT token doesnt begin with Bearer");
        }


        //Validate Token
        if (userName!=null && SecurityContextHolder.getContext().getAuthentication()==null ){

            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userName);
            if(this.jwtTokenHelper.validateToken(token,userDetails)){
                //set authentication
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }else {
                System.out.println("Invalid JWT token");
            }
        }else {
            System.out.println("Username is null or context is not null");
        }

        filterChain.doFilter(request,response);

    }
}
