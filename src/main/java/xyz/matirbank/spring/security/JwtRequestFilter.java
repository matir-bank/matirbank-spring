package xyz.matirbank.spring.security;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import io.jsonwebtoken.ExpiredJwtException;
import xyz.matirbank.spring.models.entities.StandardUser;
import xyz.matirbank.spring.repositories.StandardUserRepository;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    StandardUserRepository userRepository;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {

        final String requestTokenHeader = request.getHeader("Authorization");

        String userHash = null;
        String jwtToken = null;
        int errorCode = 0;
        String errorDetails = "";

        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            jwtToken = requestTokenHeader.substring(7);
            try {
                userHash = jwtTokenUtil.getUserHashFromToken(jwtToken);
            } catch (IllegalArgumentException e) {
                errorCode = 11001;
                errorDetails = "Invalid Authorization Token";
            } catch (ExpiredJwtException e) {
                errorCode = 11002;
                errorDetails = "Authorization Token Expired";
            } catch (Exception e) {
                errorCode = 11001;
                errorDetails = "Invalid Authorization Token";
            }
        } else {
            errorCode = 11003;
            errorDetails = "Authorization Token Does Not Begin With Bearer String";
        }

        if (userHash != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            StandardUser user = userRepository.findUserByHash(userHash);

            if (jwtTokenUtil.validateToken(jwtToken, user)) {
                UserDetails userDetails = user.toUserDetails();
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            } else {
                errorCode = 11004;
                errorDetails = "Authorization Token Tampered";
            }
        }

//        if(errorCode != 0) {
//            ErrorResponse errorResponse = new ErrorResponse();
//            errorResponse.setCode(errorCode);
//            errorResponse.setSummary(errorDetails);
//            String serializedResponse = new ObjectMapper().writeValueAsString(new BaseResponse<>(200, null, errorResponse));
//            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
//            response.getOutputStream().write(serializedResponse.getBytes());
//        }
        chain.doFilter(request, response);
    }

}
