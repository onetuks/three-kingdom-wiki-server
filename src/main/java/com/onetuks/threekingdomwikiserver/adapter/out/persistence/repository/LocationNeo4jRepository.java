package com.onetuks.threekingdomwikiserver.adapter.out.persistence.repository;

import com.onetuks.threekingdomwikiserver.adapter.out.persistence.entity.LocationNode;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface LocationNeo4jRepository extends Neo4jRepository<LocationNode, String> {}
