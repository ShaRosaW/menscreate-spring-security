package nl.wijnberg.menscreate.service.security.jwt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * AuthEntryPointJwt class implements AuthenticationEntryPoint interface. We override the
 * commence() method. This method will be triggered anytime unauthenticated User requests a secured HTTP resource and an
 * AuthenticationException is thrown.
 */
@Component
public class AuthEntryPointJwt  implements AuthenticationEntryPoint {
    private static final Logger logger = LoggerFactory.getLogger(AuthEntryPointJwt.class);

    /**
     * HttpServletResponse.SC_UNAUTHORIZED is the 401 Status code. It indicates that the request requires HTTP
     * authentication.
     * @param request request
     * @param response response
     * @param authException exception
     * @throws IOException exception
     * * @throws ServletException
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        logger.error("Unauthorized error: {}", authException.getMessage());
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Error: Unauthorized");
    }

}
//        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Error: You are unauthorized to make this request.");