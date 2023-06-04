package com.dreamrealm.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name="trading")
public class TradingReadDTO {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private String userId;
    private String messageId;
    private String offer;
    private String status;
}
