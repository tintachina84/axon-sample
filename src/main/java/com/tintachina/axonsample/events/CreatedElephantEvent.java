package com.tintachina.axonsample.events;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreatedElephantEvent {
  private String id;
  private String name;
  private int weight;
  private String status;
}
