package cl.diego.balance.users.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class BalanceUsersAppApplication {

    public static void main( String[] args ) {
        SpringApplication.run( BalanceUsersAppApplication.class, args );
    }

}
