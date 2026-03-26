package gabrielebelluco.EnergyRoad.security;

import gabrielebelluco.EnergyRoad.entities.User;
import gabrielebelluco.EnergyRoad.services.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

@Component
public class JwtFilter extends OncePerRequestFilter {


    private final JwtUtils jwtUtils;
    private final UserService userService;

    @Autowired
    public JwtFilter(JwtUtils jwtUtils, @Lazy UserService userService) {
        this.jwtUtils = jwtUtils;
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.getWriter().write("{\"message\": \"Token mancante o malformato\"}");
            return;
        }
        String accessToken = authHeader.replace("Bearer ", "");
        jwtUtils.verifyToken(accessToken);
        UUID userId = jwtUtils.extractIdFromToken(accessToken);
        User authenticatedUser = this.userService.findById(userId);
        Authentication authentication = new UsernamePasswordAuthenticationToken(authenticatedUser, null, authenticatedUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        AntPathMatcher matcher = new AntPathMatcher();
        String path = request.getServletPath();
        String method = request.getMethod();
        if ("OPTIONS".equalsIgnoreCase(method)) return true;
        if (matcher.match("/auth/**", path)) return true;
        if ("GET".equalsIgnoreCase(method)) {
            return matcher.match("/site", path)
                    || matcher.match("/site/**", path)
                    || matcher.match("/products", path)
                    || matcher.match("/categories", path);
        }

        return false;
    }
}
