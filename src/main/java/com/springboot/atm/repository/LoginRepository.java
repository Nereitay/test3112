package com.springboot.atm.repository;

import com.springboot.atm.model.CardModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginRepository extends JpaRepository<CardModel, String> {
}
