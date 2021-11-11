package com.example.CMPApplication.filters;

import com.example.CMPApplication.Constants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String authHeader = request.getHeader("Authorization");
        if(authHeader != null){
            String authHeaderArr[] = authHeader.split("Bearer ");
            if (authHeaderArr != null && authHeaderArr.length > 1){
                String token = authHeaderArr[1];
                try{
                    Claims claims = Jwts.parser().setSigningKey(Constants.API_SECRET_KEY).parseClaimsJws(token).getBody();
                    request.setAttribute("userId",Integer.parseInt(claims.get("userId").toString()));
                }catch(Exception e){
                    response.sendError(HttpServletResponse.SC_FORBIDDEN,"Invalid Token");
                    return;
                }
            }else{
                response.sendError(HttpServletResponse.SC_BAD_REQUEST,"Authorization token must be Bearer [Token]");
                return;
            }
        }else{
            response.sendError(HttpServletResponse.SC_FORBIDDEN,"Authorization header must be provided");
            return;
        }

        filterChain.doFilter(request,response);
    }
}
