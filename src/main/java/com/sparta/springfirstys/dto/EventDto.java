package com.sparta.springfirstys.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EventDto {
    private Long id; // 고유 식별자 (ID)
    private String task; // 할 일
    private String manager; // 담당자명
    private LocalDateTime createdAt; // 작성일
    private LocalDateTime updatedAt; // 수정일
    private LocalDateTime startTime; // 시작 시간
    private LocalDateTime endTime; // 종료 시간
}
