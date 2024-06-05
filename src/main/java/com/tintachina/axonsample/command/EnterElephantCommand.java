package com.tintachina.axonsample.command;

import lombok.Builder;
import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Value
@Builder
public class EnterElephantCommand {

  @TargetAggregateIdentifier
  String id;
  String status;
}
