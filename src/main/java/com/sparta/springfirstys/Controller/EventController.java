package com.sparta.springfirstys.Controller;

import com.sparta.springfirstys.dto.EventRequestDto;
import com.sparta.springfirstys.service.EventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/events") //사용자 일정 조회
public class EventController {
    private EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    // 1. 새로운 일정 등록 (POST)
    @PostMapping
    public ResponseEntity<Void> createEvent(@RequestBody EventRequestDto eventRequestDto) {
        eventService.createEvent(eventRequestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
