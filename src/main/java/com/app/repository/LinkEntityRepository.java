package com.app.repository;

import com.app.entity.LinkEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LinkEntityRepository extends JpaRepository<LinkEntity, Integer> {
    Optional<LinkEntity> findByUserLink(String userLink);
    Optional<LinkEntity> findByShortenedLink(String shortenedLink);
}
