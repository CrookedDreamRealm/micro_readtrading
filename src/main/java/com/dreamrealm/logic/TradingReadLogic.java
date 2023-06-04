package com.dreamrealm.logic;

import com.dreamrealm.dto.TradingReadDTO;
import com.dreamrealm.model.TradingReadModel;
import com.dreamrealm.repository.TradingWriteRepo;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class TradingReadLogic {
    @Autowired
    RabbitTemplate rabbitTemplate;
    @Autowired
    TradingWriteRepo tradingRep;
    public boolean createTrade(TradingReadModel trading){
        try{
            ModelMapper modelMapper = new ModelMapper();
            TradingReadDTO tradingDTO = modelMapper.map(trading, TradingReadDTO.class);
            tradingRep.save(tradingDTO);
            return true;
        }
        catch(Exception e) {
            return false;
        }
    }

    public List<TradingReadModel> getAllTrades(){
        List<TradingReadDTO> tradingDTO = (List<TradingReadDTO>) tradingRep.findAll();
        ModelMapper modelMapper = new ModelMapper();
        List<TradingReadModel> trades = tradingDTO.stream().map(trade -> modelMapper.map(trade, TradingReadModel.class)).collect(Collectors.toList());
        return trades;
    }

    public boolean editTrade(TradingReadModel trading){
        try{
            ModelMapper modelMapper = new ModelMapper();
            TradingReadDTO tradingDTO = modelMapper.map(trading, TradingReadDTO.class);
            TradingReadDTO oldItem = tradingRep.findByItemId(tradingDTO.getId());
            TradingReadDTO newItem = tradingDTO;
            newItem.setId(oldItem.getId());
            tradingRep.save(newItem);
            return true;
        }
        catch(Exception e){
            return false;
        }
    }

    public boolean removeUserFromTrade(String id){
        try{
            List<TradingReadDTO> oldItems = tradingRep.findByUserId(id);
            for(TradingReadDTO oldItem : oldItems)
            {
                oldItem.setUserId(null);
                oldItem.setStatus("Cancelled");
                tradingRep.save(oldItem);
            }
            return true;
        }
        catch(Exception e){
            return false;
        }
    }
}
