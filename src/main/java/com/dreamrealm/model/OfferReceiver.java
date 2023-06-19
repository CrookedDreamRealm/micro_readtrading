package com.dreamrealm.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OfferReceiver {
    private String userId;
    private String messageId;
    private String offer;
    private String status;
}