package com.dreamrealm.controller;

import com.dreamrealm.logic.TradingLogic;
import com.dreamrealm.model.OfferReceiver;
import com.dreamrealm.model.Trading;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/api/readtrading")
@Slf4j
@RestController
public class TradingController {
    private final RabbitTemplate rabbitTemplate;

    public TradingController (RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Autowired
    TradingLogic tradingLogic;

    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public ResponseEntity<?> sendMessenger(@RequestBody OfferReceiver offer){
        return ResponseEntity.ok().body("test");
    }

    @RequestMapping(value= "/getAllTrades", method = RequestMethod.GET)
    public ResponseEntity<?> getAllTrades() {
        List<Trading> trades = tradingLogic.getAllTrades();
        return ResponseEntity.ok()
                .body(trades);
    }
}
