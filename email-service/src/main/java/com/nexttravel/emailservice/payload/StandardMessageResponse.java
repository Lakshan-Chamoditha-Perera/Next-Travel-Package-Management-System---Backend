package com.nexttravel.emailservice.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StandardMessageResponse {
    private String message;
    private Object data;
}
