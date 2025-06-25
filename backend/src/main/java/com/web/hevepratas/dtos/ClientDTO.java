package com.web.hevepratas.dtos;

import com.web.hevepratas.entities.Client;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ClientDTO {

    private Long id;
    private String clientId;
    private String clientSecret;
    private String clientURI;
    private String scope;

    public ClientDTO() {}

    public ClientDTO(Client clientEntity) {
        this.id = clientEntity.getId();
        this.clientId = clientEntity.getClientId();
        this.clientSecret = clientEntity.getClientSecret();
        this.scope = clientEntity.getScope();
    }
}
