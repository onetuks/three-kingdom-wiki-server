package com.onetuks.threekingdomwikiserver.adapter.out.persistence.converter;

import com.onetuks.threekingdomwikiserver.adapter.out.persistence.entity.LocationNode;
import com.onetuks.threekingdomwikiserver.domain.location.Coordinate;
import com.onetuks.threekingdomwikiserver.domain.location.Location;
import org.springframework.stereotype.Component;

@Component
public class LocationConverter {

  public LocationNode toNode(Location domain) {
    return new LocationNode(domain.locationId(), domain.locationName(), domain.coordinate());
  }

  public Location toDomain(LocationNode node) {
    return new Location(
        node.getLocationId(),
        node.getLocationName(),
        new Coordinate(node.getXRate(), node.getYRate()));
  }
}
