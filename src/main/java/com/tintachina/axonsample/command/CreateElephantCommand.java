package com.tintachina.axonsample.command;

import lombok.Builder;
import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Value
@Builder
public class CreateElephantCommand {

  @TargetAggregateIdentifier
  String id;
  String name;
  int weight;
  String status;
}
