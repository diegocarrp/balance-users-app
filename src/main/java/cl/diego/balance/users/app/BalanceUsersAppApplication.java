package cl.diego.balance.users.app;

import cl.diego.balance.users.app.config.db.MongoDBConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class BalanceUsersAppApplication {

    public static void main( String[] args ) {
        SpringApplication.run( BalanceUsersAppApplication.class, args );
    }

}
