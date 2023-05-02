package cl.diego.balance.users.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableAutoConfiguration(exclude={MongoAutoConfiguration.class})
public class BalanceUsersAppApplication {

    public static void main( String[] args ) {
        SpringApplication.run( BalanceUsersAppApplication.class, args );
    }

}
