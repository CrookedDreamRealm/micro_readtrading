package com.dreamrealm.logic;

import com.dreamrealm.model.TradingReadModel;
import com.dreamrealm.repository.TradingWriteRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RabbitReceiver {
    @Autowired
    TradingWriteRepo tradingRep;
    private final RabbitTemplate rabbitTemplate;

    public RabbitReceiver(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }
    @Autowired
    TradingReadLogic tradingLogic;

    @RabbitListener(queues = {"q.readOffer"})
    @RabbitHandler
    public void onOfferRegistration(TradingReadModel event)  {
        log.info("Offer Registration Event Received: {}", event);
        //executeRegistration(event);
        //rabbitTemplate.convertAndSend("x.post-registration","", event);
        System.out.println(event);
        TradingReadModel trade = new TradingReadModel();
        trade.setOffer(event.getOffer());
        trade.setMessageId(event.getMessageId());
        trade.setStatus(event.getStatus());
        tradingLogic.createTrade(trade);
        log.info("Offer: {}", trade);
    }

    @RabbitListener(queues = {"q.deleteReadInfoTrade"})
    @RabbitHandler
    public void onDeleteAccRegistration(String id)  {
        log.info("Offer Registration Event Received: {}", id);
        //executeRegistration(event);
        //rabbitTemplate.convertAndSend("x.post-registration","", event);
        System.out.println(id);
        tradingLogic.removeUserFromTrade(id);
        log.info("Offer: {}", id);
    }

    /*@RabbitListener(queues = {"q.fall-back-registration"})
    public void onRegistrationFailure(Offer failedRegistration){
        log.info("Executing fallback for failed registration {}", failedRegistration);
    }*/

    private void executeRegistration(TradingReadModel event) {
        log.info("Executing offer Registration Event: {}", event);

        throw new RuntimeException("Registration Failed");

    }
}
