package com.sparta.springfirstys.dto;

import com.sparta.springfirstys.entity.Event;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter

public class EventResponseDto {

    private Long id; // 고유 식별자 (ID)
    private String task; // 할 일
    private String manager; // 담당자명
    private LocalDateTime startTime; // 시작 시간
    private LocalDateTime endTime; // 종료 시간
    private LocalDateTime createdAt; // 작성일
    private LocalDateTime updatedAt; // 수정일

    // 기본 생성자
    public EventResponseDto() {
    }

    // 모든 필드를 초기화하는 생성자
    public EventResponseDto(Long id, String title, String manager, LocalDateTime startTime, LocalDateTime endTime, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.task = title;
        this.manager = manager;
        this.startTime = startTime;
        this.endTime = endTime;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Entity 객체를 이용한 생성자
    public EventResponseDto(Event event) {
        this.id = event.getId();
        this.task = event.getTask();
        this.manager = event.getManager();
        this.startTime = event.getStartTime();
        this.endTime = event.getEndTime();
        this.createdAt = event.getCreatedAt();
        this.updatedAt = event.getUpdatedAt();
    }
}
