package application.config;

import application.domain.account.User;
import application.infrastructure.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final Logger log = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    private final JwtService jwtService;
    private final UserRepository userRepository;

    public JwtAuthenticationFilter(JwtService jwtService, UserRepository userRepository) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return request.getServletPath().startsWith("/auth/");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String header = request.getHeader("Authorization");
        log.debug("Authorization header: {}", header);

        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7).trim();
            log.debug("Token extraído: {}...", token.substring(0, Math.min(20, token.length())));

            if (jwtService.isTokenValid(token)) {
                String subject = jwtService.extractSubject(token);
                log.debug("Token válido, subject: {}", subject);

                User user = userRepository.findFirstByCpfOrAccountNumber(subject, subject)
                        .orElse(null);

                if (user != null) {
                    var authentication = new UsernamePasswordAuthenticationToken(
                            user, null, Collections.emptyList());
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    log.debug("Usuário autenticado: {}", user.getCpf());
                } else {
                    log.warn("Usuário não encontrado para subject: {}", subject);
                }
            } else {
                log.warn("Token inválido");
            }
        } else {
            log.debug("Sem header Authorization Bearer na requisição");
        }

        filterChain.doFilter(request, response);
    }
}

