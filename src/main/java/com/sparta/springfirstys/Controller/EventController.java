package com.sparta.springfirstys.Controller;

import jdk.jfr.Event;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/events") //사용자 일정 조회
public class EventController {
    // 1. 새로운 일정 등록 (POST)
    @PostMapping
    public ResponseEntity<Event> createEvent(@RequestBody Event event) {
        // 새 일정을 생성하고, 작성일과 수정일을 설정하며 저장

        return null;
    }
}
