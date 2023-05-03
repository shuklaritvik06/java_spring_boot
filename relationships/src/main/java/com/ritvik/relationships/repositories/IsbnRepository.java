package com.ritvik.relationships.repositories;

import com.ritvik.relationships.entities.IsbnEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IsbnRepository extends JpaRepository<IsbnEntity, Integer> {}
