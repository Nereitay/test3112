package com.springboot.atm.service;

import com.springboot.atm.model.AccountModel;
import com.springboot.atm.model.CardModel;
import com.springboot.atm.model.TransferModel;
import com.springboot.atm.repository.AccountRepository;
import com.springboot.atm.repository.CardRepository;
import com.springboot.atm.repository.TransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.Optional;

@Service
@Transactional
public class CardService {
    @Autowired
    private CardRepository cardRepository;
    @Autowired
    private TransferRepository transferRepository;
    @Autowired
    private AccountRepository accountRepository;

    // sacar dinero
    public void drawCash(String cardId, int money, HttpSession session) {
        Optional<CardModel> cardModelOptional = cardRepository.findById(cardId);
        if (cardModelOptional.isPresent()) {
            CardModel cardModel = cardModelOptional.get();
            Optional<AccountModel> accountModelOptional = accountRepository.findById(cardModel.getCustomerId());
            if (accountModelOptional.isPresent()) {
                AccountModel accountModel = accountModelOptional.get();
                int balance = accountModel.getBalance();
                if (cardModel.getCarType()) {
                    if (money <= balance + cardModel.getCredit()) {
                        balance = balance - money;
                        accountModel.setBalance(balance);
                        accountRepository.save(accountModel);

                        TransferModel transferModel = new TransferModel();
                        transferModel.setCardId(cardModel.getCardId());
                        transferModel.setTransType(1);
                        transferModel.setTransMoney(money);
                        transferModel.setTransDate(new Date());
                        transferRepository.save(transferModel);
                    }
                } else {
                    if (money <= balance) {
                        balance = balance - money;
                        accountModel.setBalance(balance);

                        TransferModel transferModel = new TransferModel();
                        transferModel.setCardId(cardModel.getCardId());
                        transferModel.setTransType(1);
                        transferModel.setTransMoney(money);
                        transferModel.setTransDate(new Date());
                        transferRepository.save(transferModel);
                    }
                }
            }
        }


    }

    // transferir
    public void TransferMoney (String OriginCard, String DesAccount, int money) {
        Optional<CardModel> originOptional = cardRepository.findById(OriginCard);
        Optional<AccountModel> desOptional = accountRepository.findByIbanNumber(DesAccount);
        if (originOptional.isPresent() && desOptional.isPresent()) {
            CardModel cardModelOrigin = originOptional.get();
            Optional<AccountModel> accountModelOptional = accountRepository.findById(cardModelOrigin.getCustomerId());
            AccountModel accountModelDes = desOptional.get();
            if (accountModelOptional.isPresent()) {
                AccountModel accountModelOrigin = accountModelOptional.get();
                int oriBalance = accountModelOrigin.getBalance();
                int desBalance = accountModelDes.getBalance();

                if (money <= oriBalance) {
                    oriBalance = oriBalance - money;
                    desBalance = desBalance + money;
                    accountModelDes.setBalance(desBalance);
                    accountModelOrigin.setBalance(oriBalance);
                    accountRepository.save(accountModelDes);
                    accountRepository.save(accountModelOrigin);

                    TransferModel transferModel = new TransferModel();
                    transferModel.setCardId(cardModelOrigin.getCardId());
                    transferModel.setTransType(3);
                    transferModel.setTransMoney(money);
                    transferModel.setTransDate(new Date());
                    transferRepository.save(transferModel);
                }

            }
        }
    }
}
