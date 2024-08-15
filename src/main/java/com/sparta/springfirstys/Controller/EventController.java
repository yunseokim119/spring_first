package com.sparta.springfirstys.Controller;

import com.sparta.springfirstys.dto.EventRequestDto;
import com.sparta.springfirstys.dto.EventResponseDto;
import com.sparta.springfirstys.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EventController {

    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }
    @PostMapping("/events")
    public EventResponseDto createEvent(@RequestBody EventRequestDto requestDto) {
        return eventService.createEvent(requestDto);
    }

    @GetMapping("/events/{id}")
    public EventResponseDto getEvent(@PathVariable Long id) {
        return eventService.getEvent(id);
    }

    @GetMapping("/events")
    public List<EventResponseDto> getEvents() {
        return eventService.getEvents();
    }

    @PutMapping("/events/{id}")
    public EventResponseDto updateEvent(@PathVariable Long id, @RequestBody EventRequestDto requestDto) {
        return eventService.updateEvent(id, requestDto);
    }

    @DeleteMapping("/events/{id}")
    public void deleteEvent(@PathVariable Long id, @RequestParam String password) {
        eventService.deleteEvent(id, password);
    }
}