package com.jeeva.quiz_service.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ResponseAns {
    private Integer id;
    private String response;

}
