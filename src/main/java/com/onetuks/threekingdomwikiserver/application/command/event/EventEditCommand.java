package com.onetuks.threekingdomwikiserver.application.command.event;

public record EventEditCommand(String eventId, String eventName, Integer happenedYear) {}
