package com.tintachina.axonsample.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum StatusEnum {
  READY("Ready"),
  ENTER("Enter"),
  EXIT("Exit");

  private final String value;

  public String value() {
    return value;
  }
}
