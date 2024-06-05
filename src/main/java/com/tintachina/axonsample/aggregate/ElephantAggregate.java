package com.tintachina.axonsample.aggregate;

import com.tintachina.axonsample.command.CreateElephantCommand;
import com.tintachina.axonsample.command.EnterElephantCommand;
import com.tintachina.axonsample.command.ExitElephantCommand;
import com.tintachina.axonsample.events.CreatedElephantEvent;
import com.tintachina.axonsample.events.EnteredElephantEvent;
import com.tintachina.axonsample.events.ExitedElephantEvent;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.modelling.command.AggregateMember;
import org.axonframework.spring.stereotype.Aggregate;

@Slf4j
@NoArgsConstructor
@Aggregate
public class ElephantAggregate {

  @AggregateIdentifier
  private String id;

  @AggregateMember
  private String name;

  @AggregateMember
  private int weight;

  @AggregateMember
  private String status;

  @CommandHandler
  private ElephantAggregate(CreateElephantCommand cmd) {
    log.info("[@CommandHandler] CreateElephantCommand for Id: {}", cmd.getId());
    CreatedElephantEvent event = new CreatedElephantEvent(
        cmd.getId(), cmd.getName(), cmd.getWeight(), cmd.getStatus());
    AggregateLifecycle.apply(event);
  }

  @EventSourcingHandler
  private void on(CreatedElephantEvent event) {
    log.info("[@EventSourcingHandler] CreatedElephantEvent for Id: {}", event.getId());
    this.id = event.getId();
    this.name = event.getName();
    this.weight = event.getWeight();
    this.status = event.getStatus();
  }

  @CommandHandler
  private void handle(EnterElephantCommand cmd) {
    log.info("[@CommandHandler] EnterElephantCommand for Id: {}", cmd.getId());

    AggregateLifecycle.apply(new EnteredElephantEvent(cmd.getId(), cmd.getStatus()));
  }

  @EventSourcingHandler
  private void on(EnteredElephantEvent event) {
    log.info("[@EventSourcingHandler] EnteredElephantEvent for Id: {}", event.getId());
    log.info("======== [Enter] Event Replay => Status: {}", this.status);

    this.status = event.getStatus();

    log.info("======== [Enter] Final Status: {}", this.status);
  }

  @CommandHandler
  private void handle(ExitElephantCommand cmd) {
    log.info("[@CommandHandler] ExitElephantCommand for Id: {}", cmd.getId());

    AggregateLifecycle.apply(new ExitedElephantEvent(cmd.getId(), cmd.getStatus()));
  }

  @EventSourcingHandler
  private void on(ExitedElephantEvent event) {
    log.info("[@EventSourcingHandler] ExitedElephantEvent for Id: {}", event.getId());
    log.info("======== [Exit] Event Replay => Status: {}", this.status);

    this.status = event.getStatus();

    log.info("======== [Exit] Final Status: {}", this.status);
  }
}
