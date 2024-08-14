package com.sparta.springfirstys.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EventRequestDto {
    private String task; // 할 일
    private String manager; // 담당자명
    private String password; // 비밀번호
    private LocalDateTime startTime; // 시작 시간
    private LocalDateTime endTime; // 종료 시간
}
