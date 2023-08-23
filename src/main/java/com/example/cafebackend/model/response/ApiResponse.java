package com.example.cafebackend.model.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Data
public class ApiResponse {

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp = LocalDateTime.now(ZoneId.of("Asia/Bangkok"));
    private String message;
    private String accessToken;
}
