package com.tintachina.axonsample.service;

import com.tintachina.axonsample.command.CreateElephantCommand;
import com.tintachina.axonsample.dto.ElephantDTO;
import com.tintachina.axonsample.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ElephantService {

  public ResultVO<CreateElephantCommand> create(ElephantDTO elephant) {
    log.info("[ElephantService] create elephant: {}", elephant.toString());

    ResultVO<CreateElephantCommand> result = new ResultVO<>();

    return result;
  }

  public ResultVO<String> enter(String id) {
    log.info("[ElephantService] enter elephant: {}", id);

    ResultVO<String> result = new ResultVO<>();

    return result;
  }

  public ResultVO<String> exit(String id) {
    log.info("[ElephantService] exit elephant: {}", id);

    ResultVO<String> result = new ResultVO<>();

    return result;
  }
}
