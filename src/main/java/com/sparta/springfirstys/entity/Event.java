package com.sparta.springfirstys.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Event {
    private Long id; // 고유 식별자(ID)
    private String task; // 할 일
    private String assignee; // 담당자명
    private String password; // 비밀번호 (암호화 저장)
    private LocalDateTime createdAt; // 작성일
    private LocalDateTime updatedAt; // 수정일
    private LocalDateTime startTime; // 시작 시간
    private LocalDateTime endTime; // 종료 시간

    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
