package com.onetuks.threekingdomwikiserver.adapter.out.persistence.repository;

import com.onetuks.threekingdomwikiserver.adapter.out.persistence.entity.EventNode;
import java.util.Optional;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

public interface EventNeo4jRepository extends Neo4jRepository<EventNode, String> {

  @Query("MATCH (e:Event) WHERE e.eventId = $eventId RETURN e;")
  Optional<EventNode> findByEventId(String eventId);

  @Query("MATCH (e:Event) WHERE e.eventId = $eventId DETACH DELETE e;")
  void deleteByEventId(String eventId);
}
