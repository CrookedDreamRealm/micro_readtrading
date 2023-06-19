package com.dreamrealm.repository;

import com.dreamrealm.dto.TradingDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TradingWriteRepo extends JpaRepository<TradingDTO, Integer> {
    @Query("SELECT a FROM trading a WHERE a.id = ?1")
    TradingDTO findByItemId(Integer id);

    @Query("SELECT a FROM trading a WHERE a.userId = ?1")
    List<TradingDTO> findByUserId(String id);
}
