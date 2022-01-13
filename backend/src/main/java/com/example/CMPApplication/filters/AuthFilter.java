package com.example.CMPApplication.filters;

import com.example.CMPApplication.Constants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class AuthFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        ((HttpServletResponse) servletResponse).addHeader("Access-Control-Allow-Origin", "http://localhost:3000");
        ((HttpServletResponse) servletResponse).addHeader("Access-Control-Allow-Methods","GET, OPTIONS, HEAD, PUT, POST");
        ((HttpServletResponse) servletResponse).addHeader("Access-Control-Allow-Credentials", "true");

        String authHeader = request.getHeader("Authorization");

        // From Cookie Reading
        Cookie[] cookies = request.getCookies();
        String name = cookieNameParsing(cookies);



        if(authHeader != null){
            String authHeaderArr[] = authHeader.split("Bearer ");
            if (authHeaderArr != null && authHeaderArr.length > 1){
                String token = authHeaderArr[1];
                try{
                    parsingToken(request,token);
                }catch(Exception e){
                    response.sendError(HttpServletResponse.SC_FORBIDDEN,"Invalid Token");
                    return;
                }
            }else{
                response.sendError(HttpServletResponse.SC_BAD_REQUEST,"Authorization token must be Bearer [Token]");
                return;
            }
        }else{
            // parsing jwt string with Cookie and set to parsingToken()
            if (name != null) {
                parsingToken(request,name);
            } else {
                response.sendError(HttpServletResponse.SC_FORBIDDEN,"Authorization header must be provided");
                return;
            }
        }

        filterChain.doFilter(request,response);
    }

    // Parsing token
    private void parsingToken(HttpServletRequest request, String token){
        Claims claims = Jwts.parser().setSigningKey(Constants.API_SECRET_KEY).parseClaimsJws(token).getBody();
        request.setAttribute("userId",Integer.parseInt(claims.get("userId").toString()));
    }

    // Parsing cookie name
    private String cookieNameParsing(Cookie cookies[]){
        String cookieName = new String();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    cookieName = cookie.getValue();
                }
            }
        }

        return cookieName;
    }
}
