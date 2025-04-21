package com.web.hevepratas.repositories;

import com.web.hevepratas.entities.Address;
import com.web.hevepratas.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    Address findByUser(User user);

}
