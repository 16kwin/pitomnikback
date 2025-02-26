package project.pitomnik.security;

import project.pitomnik.util.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import java.io.IOException;
import java.util.stream.Collectors;
import org.json.JSONObject;


public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtUtil jwtUtil, UserDetailsService userDetailsService) {
        super(authenticationManager);
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;

        setFilterProcessesUrl("/login");
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        logger.info("Attempting authentication");

        try {
            // Read the request body
            String requestBody = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
            logger.debug("Request Body: {}", requestBody);

            // Parse the JSON
            JSONObject jsonObject = new JSONObject(requestBody);
            String username = jsonObject.getString("username");
            String password = jsonObject.getString("password");

            logger.info("Extracted username: {}", username);
            logger.info("Extracted password: {}", password);

            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);

            return getAuthenticationManager().authenticate(authenticationToken);

        } catch (Exception e) {
            logger.error("Error reading request body", e);
            throw new AuthenticationServiceException("Error reading request body", e);
        }
    }



    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        logger.info("Authentication successful");
        UserDetails userDetails = (UserDetails) authResult.getPrincipal();


        String jwtToken = jwtUtil.generateToken(userDetails);
        String refreshToken = jwtUtil.generateRefreshToken(userDetails);


        String jsonResponse = String.format("{\"token\":\"%s\",\"refreshToken\":\"%s\"}", jwtToken, refreshToken);


        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonResponse);
        response.getWriter().flush();
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        logger.warn("Authentication failed: " + failed.getMessage());
        response.setStatus(HttpStatus.UNAUTHORIZED.value());


        String jsonResponse = "{\"error\":\"Invalid credentials\"}";

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonResponse);
        response.getWriter().flush();
    }
}