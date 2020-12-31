package com.springboot.atm.service;

import com.springboot.atm.model.CardModel;
import com.springboot.atm.model.Result;
import com.springboot.atm.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Service
public class ConfigurationService {
    @Autowired
    private LoginRepository loginRepository;

    public Result activateAccount(String cardId) {
        Result result = new Result();
        Optional<CardModel> optionalCardModel = loginRepository.findById(cardId);
        if (optionalCardModel.isPresent()) {
            CardModel cardModel = optionalCardModel.get();
            cardModel.setActive(true);
            loginRepository.save(cardModel);
            result.setRes(true);
            result.setMeg("success");
        } else {
            result.setRes(false);
            result.setMeg("error");
        }
        return result;
    }



    public Result updatePassword(String cardId, String password, String newPassword) {
        Result result = new Result();
        Optional<CardModel> optionalCardModel = loginRepository.findById(cardId);
        if (optionalCardModel.isPresent()) {
            CardModel cardModel = optionalCardModel.get();
            if (password.equals(cardModel.getPassword())) {
                cardModel.setPassword(newPassword);
                cardModel.setChanged(true);
                loginRepository.save(cardModel);
                result.setRes(true);
                result.setMeg("success");
            }

        } else {
            result.setRes(false);
            result.setMeg("error");
        }
        return result;
    }
}
