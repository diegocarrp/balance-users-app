package cl.diego.balance.users.app.config.db;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
@Slf4j
public class MongoDBConfig {

    @Value("${config.mongodb.uri}")
    private String connectionUri;
    @Value("${config.mongodb.database}")
    private String databaseName;

    @Bean
    public MongoDatabase mongoDatabase() {
        MongoClientSettings settings = getMongoClientSettings( );
        MongoClient mongoClient = MongoClients.create(settings);
        return mongoClient.getDatabase(databaseName);
    }

    @Bean
    public MongoTemplate mongoTemplate() {
        MongoClientSettings settings = getMongoClientSettings( );
        MongoClient mongoClient = MongoClients.create(settings);

        return new MongoTemplate(mongoClient, databaseName);
    }

    private MongoClientSettings getMongoClientSettings( ) {
        ConnectionString connectionString = new ConnectionString(connectionUri);
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString( connectionString )
                .serverApi( ServerApi.builder()
                        .version( ServerApiVersion.V1)
                        .build())
                .build();
        return settings;
    }
}
