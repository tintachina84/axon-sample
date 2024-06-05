package com.tintachina.axonsample.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serial;
import java.io.Serializable;
import lombok.Data;

@Data
@Entity
@Table(name = "elephant")
public class Elephant implements Serializable {

  @Serial
  private static final long serialVersionUID = 3696822807997263312L;

  @Id
  @Column(name = "id", nullable = false, length = 3)
  private String id;

  @Column(name = "name", nullable = false, length = 30)
  private String name;

  @Column(name = "weight", nullable = false)
  private int weight;

  @Column(name = "status", nullable = false, length = 20)
  private String status;

}
