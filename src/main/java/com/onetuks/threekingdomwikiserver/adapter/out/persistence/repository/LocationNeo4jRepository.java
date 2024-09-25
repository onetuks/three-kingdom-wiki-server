package com.onetuks.threekingdomwikiserver.adapter.out.persistence.repository;

import com.onetuks.threekingdomwikiserver.adapter.out.persistence.entity.LocationNode;
import java.util.Optional;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

public interface LocationNeo4jRepository extends Neo4jRepository<LocationNode, String> {

  @Query("MATCH (l:Location) WHERE l.locationId = $locationId RETURN l;")
  Optional<LocationNode> findByLocationId(String locationId);

  @Query("MATCH (l:Location) WHERE l.locationId = $locationId DELETE l;")
  void deleteByLocationId(String locationId);
}
