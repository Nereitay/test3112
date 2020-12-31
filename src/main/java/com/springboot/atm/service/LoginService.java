package com.springboot.atm.service;

import com.springboot.atm.model.AccountModel;
import com.springboot.atm.model.CardModel;
import com.springboot.atm.model.Result;
import com.springboot.atm.repository.AccountRepository;
import com.springboot.atm.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Service
public class LoginService {
    @Autowired
    private CardRepository cardRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private ConfigurationService configurationService;

    public Result getUserInfo(String cardId, HttpSession session) {
        Result result = new Result();
        Optional<CardModel> cardModelOptional = cardRepository.findById(cardId);
        if (cardModelOptional.isPresent()) {
            result.setRes(true);
            result.setMeg(cardId);
            session.setAttribute("cardId", cardId);
        } else {
            result.setRes(false);
            result.setMeg("carId not exists");
        }
        return result;
    }

    public Result getCardInfo(String cardId, String password) {
        Result result = new Result();
        Optional<CardModel> cardModelOptional = cardRepository.findById(cardId);
        if (cardModelOptional.isPresent()) {
            CardModel cardModel = cardModelOptional.get();
           if(password.equals(cardModel.getPassword())) {
              if (cardModel.getActive() && cardModel.getChanged()) {
                  AccountModel accountModel = accountRepository.findById(cardModel.getCustomerId()).orElse(null);
                  result.setRes(true);
                  result.setMeg("success");
                  result.setObject(accountModel);
              } else {
                  result.setRes(false);
                  result.setMeg("please activate your account first and reset your password");
              }
           }
        } else {
            result.setRes(false);
            result.setMeg("password is not correct");
        }
        return result;
    }
}
