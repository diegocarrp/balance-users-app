package cl.diego.balance.users.app.config.logging.layout;

import ch.qos.logback.classic.pattern.ExtendedThrowableProxyConverter;
import ch.qos.logback.classic.pattern.ThrowableHandlingConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.LayoutBase;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class JSONLoggingLayout extends LayoutBase<ILoggingEvent> {

    private final ObjectMapper mapper = new ObjectMapper();
    private final ThrowableHandlingConverter converter = new ExtendedThrowableProxyConverter();
    private final DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern( "yyyy-MM-dd HH:mm:ss.SSS" );

    @Override
    public String doLayout( ILoggingEvent event ) {
        String formattedMessage = getMessage( event );
        return doInternalLayout( formattedMessage, event );
    }

    private String getMessage( ILoggingEvent event ) {
        StringBuilder builder = new StringBuilder();
        builder.append( event.getFormattedMessage() );
        if (event.getThrowableProxy() != null) {
            builder.append( System.lineSeparator() )
                    .append( "Details: " )
                    .append( converter.convert( event ) );
        }
        return builder.toString();
    }

    private String doInternalLayout( String formattedMessage, ILoggingEvent event ) {

        String timestamp = dtFormatter.format(
                ZonedDateTime.ofInstant(
                        Instant.ofEpochMilli(event.getTimeStamp()),
                        ZoneId.systemDefault()) );

        JSONLoggingEvent loggingEvent = JSONLoggingEvent.builder()
                .message( formattedMessage )
                .thread( event.getThreadName() )
                .severity( event.getLevel().toString() )
                .timestamp( timestamp )
                .build();

        try {
            return mapper.writeValueAsString( loggingEvent ) + "\n";
        } catch (JsonProcessingException e) {
            return "";
        }

    }

    @Data
    @Builder
    public static class JSONLoggingEvent {
        private String message;
        private String timestamp;
        private String thread;
        private String severity;
        private String trackingId;
    }

}
