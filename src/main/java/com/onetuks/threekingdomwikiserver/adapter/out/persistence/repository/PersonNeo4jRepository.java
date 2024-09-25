package com.onetuks.threekingdomwikiserver.adapter.out.persistence.repository;

import com.onetuks.threekingdomwikiserver.adapter.out.persistence.entity.PersonNode;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface PersonNeo4jRepository extends Neo4jRepository<PersonNode, String> {}
