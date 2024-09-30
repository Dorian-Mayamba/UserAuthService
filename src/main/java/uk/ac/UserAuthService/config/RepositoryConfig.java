package uk.ac.UserAuthService.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertCallback;
import uk.ac.UserAuthService.models.User;

import java.util.UUID;

@Configuration
public class RepositoryConfig {
    @Bean
    public BeforeConvertCallback<User> beforeSaveCallback(){
        return ((entity, collection) -> {
            if(entity.getId() == null){
                entity.setId(UUID.randomUUID());
            }
            return entity;
        });
    }
}
