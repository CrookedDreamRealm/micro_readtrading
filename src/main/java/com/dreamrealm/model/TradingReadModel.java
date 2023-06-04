package com.dreamrealm.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TradingReadModel {
    private Integer id;
    private String userId;
    private String messageId;
    private String offer;
    private String status;
}
