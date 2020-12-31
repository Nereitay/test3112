package com.springboot.atm.repository;

import com.springboot.atm.model.AccountModel;
import com.springboot.atm.model.CardModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<AccountModel, String> {
    Optional<AccountModel> findByIbanNumber(String desAccount);
}
