package com.web.hevepratas.repositories;

import com.web.hevepratas.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    Client findByClientId(String clientId);

}
