package com.sparta.springfirstys.dto;

import com.sparta.springfirstys.entity.Event;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class EventResponseDto {
    private Long id; // 고유 식별자 (ID)
    private String task; // 할 일
    private String manager; // 담당자명
    private LocalDateTime createdAt; // 작성일
    private LocalDateTime updatedAt; // 수정일
    private LocalDateTime startTime; // 시작 시간
    private LocalDateTime endTime; // 종료 시간

    public EventResponseDto(Event event) {
        this.id = event.getId();
        this.task = event.getTask();
        this.manager = event.getManager();
        this.createdAt = event.getCreatedAt();
        this.updatedAt = event.getUpdatedAt();
        this.startTime = event.getStartTime();
        this.endTime = event.getEndTime();
    }
}
