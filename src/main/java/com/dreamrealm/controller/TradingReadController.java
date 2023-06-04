package com.dreamrealm.controller;

import com.dreamrealm.logic.TradingReadLogic;
import com.dreamrealm.model.TradingReadModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/api/readtrading", produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@RestController
public class TradingReadController {
    @Autowired
    TradingReadLogic tradingLogic;
    @RequestMapping(value= "/getAllTrades", method = RequestMethod.GET)
    public ResponseEntity<?> getAllTrades() {
        List<TradingReadModel> trades = tradingLogic.getAllTrades();
        return ResponseEntity.ok()
                .body(trades);
    }
}
