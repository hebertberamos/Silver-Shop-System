package com.web.hevepratas.servicies;

import com.web.hevepratas.dtos.ClientDTO;
import com.web.hevepratas.entities.Client;
import com.web.hevepratas.mappers.GlobalMapper;
import com.web.hevepratas.repositories.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository repository;
    private final PasswordEncoder encoder;

    public Client save(ClientDTO clientDto) {
//        TODO: add some rules to verify if the client doesn't already exist in database.

        String encodedPassword = encoder.encode(clientDto.getClientSecret());

        Client clientEntity = GlobalMapper.mapToClient(clientDto);
        clientEntity.setClientSecret(encodedPassword);

        return repository.save(clientEntity);
    }

    public Client findByClientId(String clientId) {
        return repository.findByClientId(clientId);
    }

}
