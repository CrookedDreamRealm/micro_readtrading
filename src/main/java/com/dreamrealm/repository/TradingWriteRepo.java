package com.dreamrealm.repository;

import com.dreamrealm.dto.TradingReadDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TradingWriteRepo extends JpaRepository<TradingReadDTO, Integer> {
    @Query("SELECT a FROM trading a WHERE a.id = ?1")
    TradingReadDTO findByItemId(Integer id);

    @Query("SELECT a FROM trading a WHERE a.userId = ?1")
    List<TradingReadDTO> findByUserId(String id);
}
