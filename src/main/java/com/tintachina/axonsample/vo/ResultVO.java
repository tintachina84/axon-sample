package com.tintachina.axonsample.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class ResultVO<T> {

  private boolean returnCode;
  private String returnMessage;
  private T result;
}
