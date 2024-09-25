package com.onetuks.threekingdomwikiserver.adapter.out.persistence.repository;

import com.onetuks.threekingdomwikiserver.adapter.out.persistence.entity.PersonNode;
import java.util.Optional;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

public interface PersonNeo4jRepository extends Neo4jRepository<PersonNode, String> {

  @Query("MATCH (p:Person) WHERE p.personId = $personId RETURN p;")
  Optional<PersonNode> findByPersonId(String personId);

  @Query("MATCH (p:Person) WHERE p.personId = $personId DETACH DELETE p;")
  void deleteByPersonId(String personId);
}
