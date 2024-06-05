package com.tintachina.axonsample.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
public class ResultVO<T> {

  private boolean returnCode;
  private String returnMessage;
  private T result;
}
