package com.web.hevepratas.repositories;

import com.web.hevepratas.entities.FavoriteList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoriteListRepository extends JpaRepository<FavoriteList, Long> {

}
