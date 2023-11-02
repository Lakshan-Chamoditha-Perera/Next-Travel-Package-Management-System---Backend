package com.nexttravel.booking_service.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StandardMessageResponse implements Serializable {
    private String message;
    private Object data;
}
