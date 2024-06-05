package com.tintachina.axonsample.events;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FailedEnterElephantEvent {
  private String id;
}
