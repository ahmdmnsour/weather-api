package com.orange.weather.security.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class AuthenticationFilter extends OncePerRequestFilter {
  private final JWTService jwtService;
  private final UserDetailsService userDetailsService;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    final String authHeader = request.getHeader("Authorization");
    final String token;
    final String email;

    if (authHeader == null  || !authHeader.startsWith("Bearer ")) {
      filterChain.doFilter(request, response);
      return;
    }

    token = authHeader.substring(7);
    System.out.println(token);
    email = jwtService.extractEmail(token);
    if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
      UserDetails userDetails = this.userDetailsService.loadUserByUsername(email);

      if (jwtService.isTokenValid(token, userDetails)) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
      }
    }

    filterChain.doFilter(request, response);
  }
//  @Autowired
//  private JwtUtils jwtUtils;
//  @Autowired
//  private UserService userService;
//  private static final Logger logger = LoggerFactory.getLogger(AuthTokenFilter.class);
//
//  @Override
//  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//    try {
//      String jwt = parseJwt(request);
//      if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
//        String email = jwtUtils.getEmailFromJwtToken(jwt);
//
//        UserDetails userDetails = AppUserDetails.build(userService.getUserByEmail(email));
//
//        UsernamePasswordAuthenticationToken authentication =
//            new UsernamePasswordAuthenticationToken(userDetails,
//                                                    null,
//                                                    userDetails.getAuthorities());
//
//        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//      }
//    } catch (Exception e) {
//      logger.error("Cannot set user authentication: {}", e);
//    }
//
//    filterChain.doFilter(request, response);
//  }
//
//  private String parseJwt(HttpServletRequest request) {
//      return jwtUtils.getJwtFromCookies(request);
//  }
}
