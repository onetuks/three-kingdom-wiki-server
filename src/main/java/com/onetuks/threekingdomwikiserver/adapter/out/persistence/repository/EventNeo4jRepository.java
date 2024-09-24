package com.onetuks.threekingdomwikiserver.adapter.out.persistence.repository;

import com.onetuks.threekingdomwikiserver.adapter.out.persistence.entity.EventNode;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface EventNeo4jRepository extends Neo4jRepository<EventNode, String> {}
