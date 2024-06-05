package com.tintachina.axonsample.controller;

import com.tintachina.axonsample.command.CreateElephantCommand;
import com.tintachina.axonsample.dto.ElephantDTO;
import com.tintachina.axonsample.service.ElephantService;
import com.tintachina.axonsample.vo.ResultVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Order Service API", description = "Order Service API")
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class APIController {

  private final ElephantService elephantService;

  @Operation(summary = "Create an elephant")
  @PostMapping("/create")
  ResultVO<CreateElephantCommand> create(ElephantDTO elephant) {
    log.info("[APIController] @PostMapping /create: {}", elephant.toString());
    return this.elephantService.create(elephant);
  }

  @Operation(summary = "Enter an elephant")
  @Parameter(name = "id", description = "Elephant ID", required = true)
  @PostMapping("/enter/{id}")
  ResultVO<String> enter(@PathVariable String id) {
    log.info("[APIController] @PostMapping /enter: {}", id);
    return this.elephantService.enter(id);
  }

  @Operation(summary = "Exit an elephant")
  @Parameter(name = "id", description = "Elephant ID", required = true)
  @PostMapping("/exit/{id}")
  ResultVO<String> exit(@PathVariable String id) {
    log.info("[APIController] @PostMapping /exit: {}", id);
    return this.elephantService.exit(id);
  }
}
