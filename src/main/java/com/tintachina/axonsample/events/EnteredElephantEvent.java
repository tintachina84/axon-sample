package com.tintachina.axonsample.events;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EnteredElephantEvent {
  private String id;
  private String status;
}
