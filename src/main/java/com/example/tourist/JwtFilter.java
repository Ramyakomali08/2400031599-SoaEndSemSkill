
package com.example.tourist;

import java.io.IOException;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return !("PUT".equals(request.getMethod())
                && request.getServletPath().startsWith("/api/tourist"));
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain)
            throws ServletException, IOException {

        String token = request.getHeader("Authorization");

        if ("Bearer mytoken123".equals(token)) {
            chain.doFilter(request, response);
        } else {
            response.setStatus(401);
            response.getWriter().write("Invalid or missing token");
        }
    }
}
