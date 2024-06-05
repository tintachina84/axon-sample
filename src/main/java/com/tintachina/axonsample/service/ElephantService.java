package com.tintachina.axonsample.service;

import com.tintachina.axonsample.command.CreateElephantCommand;
import com.tintachina.axonsample.command.EnterElephantCommand;
import com.tintachina.axonsample.command.ExitElephantCommand;
import com.tintachina.axonsample.dto.ElephantDTO;
import com.tintachina.axonsample.dto.StatusEnum;
import com.tintachina.axonsample.entity.Elephant;
import com.tintachina.axonsample.repository.ElephantRepository;
import com.tintachina.axonsample.vo.ResultVO;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class ElephantService {

  private final ElephantRepository elephantRepository;
  private transient final CommandGateway commandGateway;

  public ResultVO<CreateElephantCommand> create(ElephantDTO elephant) {
    log.info("[ElephantService] create elephant: {}", elephant.toString());

    ResultVO<CreateElephantCommand> result = new ResultVO<>();

    // check validation
    if (elephant.getWeight() < 30 || elephant.getWeight() > 200) {
      result.setReturnCode(false);
      result.setReturnMessage("Elephant weight must be between 30 and 200 kg.");
      return result;
    }

    // create command
    CreateElephantCommand command = CreateElephantCommand.builder()
        .id(RandomStringUtils.random(3, false, true))
        .name(elephant.getName())
        .weight(elephant.getWeight())
        .status(StatusEnum.READY.value())
        .build();

    try {
      this.commandGateway.sendAndWait(command, 30, TimeUnit.SECONDS);
      result.setReturnCode(true);
      result.setReturnMessage("Elephant created successfully.");
      result.setResult(command);
    } catch (Exception e) {
      result.setReturnCode(false);
      result.setReturnMessage(e.getMessage());
    }

    return result;
  }

  public ResultVO<String> enter(String id) {
    log.info("[ElephantService] enter elephant: {}", id);

    ResultVO<String> result = new ResultVO<>();

    try {
      Elephant elephant = this.getEntity(id);
      if (elephant.getStatus().equals(StatusEnum.ENTER.value())) {
        result.setReturnCode(false);
        result.setReturnMessage("Elephant is already in the zoo.");
        return result;
      }
    } catch (IllegalArgumentException e) {
      result.setReturnCode(false);
      result.setReturnMessage(e.getMessage());
      return result;
    }

    try {
      this.commandGateway.sendAndWait(EnterElephantCommand.builder()
          .id(id)
          .status(StatusEnum.ENTER.value())
          .build(), 30, TimeUnit.SECONDS);

      result.setReturnCode(true);
      result.setReturnMessage("Elephant entered successfully.");
    } catch(Exception e) {
      result.setReturnCode(false);
      result.setReturnMessage(e.getMessage());
    }

    return result;
  }

  public ResultVO<String> exit(String id) {
    log.info("[ElephantService] exit elephant: {}", id);

    ResultVO<String> result = new ResultVO<>();

    try {
      Elephant elephant = this.getEntity(id);
      if (!elephant.getStatus().equals(StatusEnum.ENTER.value())) {
        result.setReturnCode(false);
        result.setReturnMessage("Elephant is not in the zoo.");
        return result;
      }
    } catch (IllegalArgumentException e) {
      result.setReturnCode(false);
      result.setReturnMessage(e.getMessage());
      return result;
    }

    try {
      this.commandGateway.sendAndWait(ExitElephantCommand.builder()
          .id(id)
          .status(StatusEnum.EXIT.value())
          .build(), 30, TimeUnit.SECONDS);

      result.setReturnCode(true);
      result.setReturnMessage("Elephant exited successfully.");
    } catch(Exception e) {
      result.setReturnCode(false);
      result.setReturnMessage(e.getMessage());
    }

    return result;
  }

  private Elephant getEntity(String id) {
    return this.elephantRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Elephant not found: " + id));
  }
}
