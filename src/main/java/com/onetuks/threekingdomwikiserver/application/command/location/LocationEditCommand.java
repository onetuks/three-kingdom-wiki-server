package com.onetuks.threekingdomwikiserver.application.command.location;

public record LocationEditCommand(
    String locationId, String locationName, Double xRate, Double yRate) {}
