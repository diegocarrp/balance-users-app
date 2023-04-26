package cl.diego.balance.users.app.config.validation;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ValidatorConfig {

    @Bean
    public Validator validator() {
        return Validation.byDefaultProvider( )
                .configure( )
                .messageInterpolator( new ParameterMessageInterpolator( ) )
                .buildValidatorFactory( )
                .getValidator( );
    }
}
