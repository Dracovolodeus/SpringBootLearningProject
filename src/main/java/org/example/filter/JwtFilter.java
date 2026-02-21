package org.example.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.AllArgsConstructor;
import org.example.dto.model.user.UserDetails;
import org.example.service.JwtService;
import org.example.service.impl.UserDetailsServiceImpl;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@AllArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

  private JwtService jwtService;
  private UserDetailsServiceImpl userDetailsService;

  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    final String authHeader = request.getHeader("Authorization");
    String jwt;
    String name;
    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
      filterChain.doFilter(request, response);
      return;
    }
    final String token = getTokenFromRequest(authHeader);
    name = jwtService.extractNameFromToken(token);
    if (name != null && SecurityContextHolder.getContext().getAuthentication() == null) {
      UserDetails userDetails = (UserDetails) userDetailsService.loadUserByUsername(name);
      if (jwtService.isValidToken(token, userDetails)) {
        UsernamePasswordAuthenticationToken authToken =
            new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());
        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authToken);
      }
    }
    filterChain.doFilter(request, response);
  }

  private String getTokenFromRequest(String authHeader) {
    return authHeader.substring(7);
  }

  private String getTokenFromRequest(HttpServletRequest request) {
    return request.getHeader("Authorization").substring(7);
  }
}
