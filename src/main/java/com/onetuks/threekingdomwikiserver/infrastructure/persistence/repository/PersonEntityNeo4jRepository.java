package com.onetuks.threekingdomwikiserver.infrastructure.persistence.repository;

import com.onetuks.threekingdomwikiserver.infrastructure.persistence.entity.PersonEntity;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface PersonEntityNeo4jRepository extends Neo4jRepository<PersonEntity, Long> {}
