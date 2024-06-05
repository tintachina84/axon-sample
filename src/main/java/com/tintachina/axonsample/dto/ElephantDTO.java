package com.tintachina.axonsample.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ElephantDTO {
  String name;
  int weight;
}
