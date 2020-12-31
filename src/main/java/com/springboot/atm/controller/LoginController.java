package com.springboot.atm.controller;

import com.springboot.atm.model.Result;
import com.springboot.atm.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    //check if card exists
    @PostMapping("/checkCarIdInfo")
    public ResponseEntity<Result> getCardIdInfo(String cardId, HttpSession session) {
        Result result = loginService.getUserInfo(cardId, session);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/checkAccount")
    public ResponseEntity<Result> userLogin(String cardId, String password, HttpSession session) {
        Result result = loginService.getCardInfo(cardId, password);
        session.setAttribute("loginAccount", result.getObject());
        return ResponseEntity.ok(result);
    }


}
