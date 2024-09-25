package com.onetuks.threekingdomwikiserver.adapter.out.persistence;

import com.onetuks.threekingdomwikiserver.adapter.out.persistence.converter.LocationConverter;
import com.onetuks.threekingdomwikiserver.adapter.out.persistence.repository.LocationNeo4jRepository;
import com.onetuks.threekingdomwikiserver.application.port.out.LocationPort;
import com.onetuks.threekingdomwikiserver.domain.location.Location;
import java.util.NoSuchElementException;
import org.springframework.stereotype.Repository;

@Repository
public class LocationNeo4jAdapter implements LocationPort {

  private final LocationNeo4jRepository repository;
  private final LocationConverter converter;

  public LocationNeo4jAdapter(LocationNeo4jRepository repository, LocationConverter converter) {
    this.repository = repository;
    this.converter = converter;
  }

  @Override
  public String create(Location location) {
    return converter.toDomain(repository.save(converter.toNode(location))).locationId();
  }

  @Override
  public Location readById(String locationId) {
    return converter.toDomain(
        repository
            .findById(locationId)
            .orElseThrow(() -> new NoSuchElementException("Location not found")));
  }

  @Override
  public void update(Location location) {
    repository.save(converter.toNode(location));
  }

  @Override
  public void delete(String locationId) {
    repository.deleteById(locationId);
  }
}
