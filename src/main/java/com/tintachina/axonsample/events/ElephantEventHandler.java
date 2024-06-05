package com.tintachina.axonsample.events;

import com.tintachina.axonsample.entity.Elephant;
import com.tintachina.axonsample.repository.ElephantRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.gateway.EventGateway;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class ElephantEventHandler {

  private final ElephantRepository elephantRepository;
  private transient final EventGateway eventGateway;
  private transient final CommandGateway commandGateway;

  @EventHandler
  private void on(CreatedElephantEvent event) {
    log.info("[@EventHandler] CreatedElephantEvent for Id: {}", event.getId());
    Elephant elephant = new Elephant();
    elephant.setId(event.getId());
    elephant.setName(event.getName());
    elephant.setWeight(event.getWeight());
    elephant.setStatus(event.getStatus());

    this.elephantRepository.save(elephant);
  }

  @EventHandler
  private void on(EnteredElephantEvent event) {
    log.info("[@EventHandler] EnteredElephantEvent for Id: {}", event.getId());

    Elephant elephant = getEntity(event.getId());
    if(elephant != null) {
      elephant.setStatus(event.getStatus());
      elephantRepository.save(elephant);
    }
  }

  @EventHandler
  private void on(ExitedElephantEvent event) {
    log.info("[@EventHandler] ExitedElephantEvent for Id: {}", event.getId());

    Elephant elephant = getEntity(event.getId());
    if(elephant != null) {
      elephant.setStatus(event.getStatus());
      elephantRepository.save(elephant);
    }
  }

  private Elephant getEntity(String id) {
    return this.elephantRepository.findById(id).orElse(null);
  }
}
