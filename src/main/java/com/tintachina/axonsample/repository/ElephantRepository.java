package com.tintachina.axonsample.repository;

import com.tintachina.axonsample.entity.Elephant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ElephantRepository extends JpaRepository<Elephant, String> {

}
