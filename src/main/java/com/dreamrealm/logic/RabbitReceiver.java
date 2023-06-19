package com.dreamrealm.logic;

import com.dreamrealm.model.OfferReceiver;
import com.dreamrealm.model.Trading;
import com.dreamrealm.repository.TradingWriteRepo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
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
    TradingLogic tradingLogic;

    /*@RabbitListener(queues = {"q.readOffer"})
    public void onOfferRegistration(Offer event)  {
        log.info("Offer Registration Event Received: {}", event);
        //executeRegistration(event);
        //rabbitTemplate.convertAndSend("x.post-registration","", event);
        System.out.println(event);
        Offer trade = new Offer();
        trade.setOffer(event.getOffer());
        trade.setMessageId(event.getMessageId());
        trade.setStatus(event.getStatus());
        //tradingLogic.createTrade(trade);
        log.info("Offer: {}", trade);
    }*/

    @RabbitHandler
    @RabbitListener(queues = {"q.readOffer"})
    public void onReadOfferRegistration(String offerReceive) {
        ObjectMapper objectMapper = new ObjectMapper();
        log.info("Offer Registration Event Received: {}", offerReceive);
        String offerReceiveErr = offerReceive.replaceAll("^\"|\"$", "");
        offerReceiveErr = offerReceiveErr.replaceAll("\\\\", "");
        try  {
            JsonNode node = objectMapper.readTree(offerReceiveErr);
            log.info("node1: {}", node);
            Trading trade = new Trading();
            trade.setUserId(node.get("userId").asText());
            trade.setMessageId(node.get("messageId").asText());
            trade.setOffer(node.get("offer").asText());
            trade.setStatus(node.get("status").asText());
            tradingLogic.createTrade(trade);
        }catch(Exception e){
            e.printStackTrace();
        }
    }


    @RabbitHandler
    @RabbitListener(queues = {"q.deleteReadInfoTrade"})
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

    private void executeRegistration(Trading event) {
        log.info("Executing offer Registration Event: {}", event);

        throw new RuntimeException("Registration Failed");

    }
}
