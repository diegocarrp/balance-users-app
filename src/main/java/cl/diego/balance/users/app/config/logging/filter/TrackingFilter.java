package cl.diego.balance.users.app.config.logging.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;


@Component
@Slf4j
public class TrackingFilter implements Filter {

    @Override
    public void doFilter( ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain )
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        String trackingId = request.getHeader( "X-trackingId" );
        trackingId = trackingId != null ? trackingId : UUID.randomUUID().toString();
        MDC.put("trackingId", trackingId);
        log.info( "trackingId: " + trackingId );
        String trackingId1 = MDC.get( "trackingId" );
        log.info( "trackingId1: " + trackingId1 );
        filterChain.doFilter( servletRequest, servletResponse );
    }
}
