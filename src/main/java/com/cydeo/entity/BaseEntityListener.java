package com.cydeo.entity;

import com.cydeo.service.KeycloakService;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@NoArgsConstructor
public class BaseEntityListener {


    private KeycloakService keycloakService;

    public BaseEntityListener(KeycloakService keycloakService) {
        this.keycloakService = keycloakService;
    }


    @PrePersist
    private void onPrePersist(BaseEntity baseEntity){

        String userName = keycloakService.getLoggedInUserName();

        if(userName != null && !userName.equals("anonymous")){

            baseEntity.setInsertDateTime(LocalDateTime.now());
            baseEntity.setLastUpdateDateTime(LocalDateTime.now());
            baseEntity.setInsertUserUsername(userName);
            baseEntity.setLastUpdateUserUsername(userName);
        }
    }

    @PreUpdate
    private void onPreUpdate(BaseEntity baseEntity){

        String userName = keycloakService.getLoggedInUserName();

        if(userName != null && !userName.equals("anonymous")){

            baseEntity.setLastUpdateDateTime(LocalDateTime.now());
            baseEntity.setLastUpdateUserUsername(userName);
        }
    }










}
