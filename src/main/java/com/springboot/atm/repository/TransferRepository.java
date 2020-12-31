package com.springboot.atm.repository;

import com.springboot.atm.model.TransferModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferRepository extends JpaRepository<TransferModel, String> {
}
