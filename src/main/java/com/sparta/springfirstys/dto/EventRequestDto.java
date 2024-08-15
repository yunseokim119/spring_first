package com.sparta.springfirstys.dto;

import com.sparta.springfirstys.entity.Event;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class EventRequestDto {

    private String task; // 할 일
    private String manager; // 담당자명
    private LocalDateTime startTime; // 시작 시간
    private LocalDateTime endTime; // 종료 시간
    private String password; // 비밀번호

    // 기본 생성자
    public EventRequestDto() {
    }

    // 모든 필드를 초기화하는 생성자
    public EventRequestDto(String task, String manager, LocalDateTime startTime, LocalDateTime endTime, String password) {
        this.task = task;
        this.manager = manager;
        this.startTime = startTime;
        this.endTime = endTime;
        this.password = password;
    }

    public EventRequestDto(Event event) {
    }
}
