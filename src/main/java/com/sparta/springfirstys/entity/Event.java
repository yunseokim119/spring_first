package com.sparta.springfirstys.entity;

import com.sparta.springfirstys.dto.EventRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class Event {
    private Long id; // 고유 식별자(ID)
    private String task; // 할 일
    private String manager; // 담당자명
    private String password; // 비밀번호 (암호화 저장)
    private LocalDateTime createdAt; // 작성일
    private LocalDateTime updatedAt; // 수정일
    private LocalDateTime startTime; // 시작 시간
    private LocalDateTime endTime; // 종료 시간

    public Event(EventRequestDto requestDto) {
        this.task = requestDto.getTask();
        this.manager = requestDto.getManager();
        this.password = requestDto.getPassword();
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.startTime = LocalDateTime.now();
        this.endTime = LocalDateTime.now();
    }
}
