package com.sparta.springfirstys.Controller;

import com.sparta.springfirstys.dto.EventRequestDto;
import com.sparta.springfirstys.dto.EventResponseDto;
import com.sparta.springfirstys.entity.Event;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
public class EventController {
    private final Map<Long, Event> eventList = new HashMap<>();

    @PostMapping("/events")
    public EventResponseDto createEvent(@RequestBody EventRequestDto requestDto){
        // RequestDto -> Entity
        Event event = new Event(requestDto);

        // Event Max ID Check
        Long maxId = eventList.size() > 0 ? Collections.max(eventList.keySet())  + 1 : 1;
        event.setId(maxId);

        //DB 저장
        eventList.put(event.getId(), event);

        // Entity -> ResponseDto
        EventResponseDto eventResponseDto = new EventResponseDto(event);

        return eventResponseDto;
    }

    @GetMapping("/events")
    public List<EventResponseDto> getEvent(){
        // Map to list
        List<EventResponseDto> responseList = eventList.values().stream()
                .map(EventResponseDto::new).toList();

        return responseList;
    }

    @PutMapping("/events/{id}")
    public Long updateEvent(@PathVariable Long id, @RequestBody EventRequestDto requestDto){
        // 해당 일정이 DB에 존재하는지 확인
        if(eventList.containsKey(id)){
            // 해당 일정 가져오기
            Event event = eventList.get(id);

            // 일정 수정
            event.update(requestDto);
            return event.getId();
        } else {
            throw new IllegalArgumentException("선택한 일정이 존재하지 않습니다.");
        }
    }

    @DeleteMapping("/events/{id}")
    public long deleteEvent(@PathVariable Long id){
        // 해당 일정이 DB에 존재하는지 확인
        if(eventList.containsKey(id)){
            // 해당 일정 삭제하기
            eventList.remove(id);
            return id;
        } else {
            throw new IllegalArgumentException("선택한 일정이 존재하지 않습니다");
        }
    }
}



/*
@RestController
@RequestMapping("/api/events") //사용자 일정 조회
public class EventController {
    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    // 1. 새로운 일정 등록 (POST)
     @PostMapping
    public ResponseEntity<Void> createEvent(@RequestBody EventRequestDto eventRequestDto) {
        eventService.createEvent(eventRequestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // 2. 선택한 일정 조회 (GET)
    @GetMapping("/{id}")
    public ResponseEntity<EventResponseDto> getEvent(@PathVariable long id) {
        EventResponseDto event = eventService.getEvent(id);
        if (event != null) {
            return new ResponseEntity<>(event, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    @GetMapping
    public ResponseEntity<List<EventResponseDto>> getEvents(
            @RequestParam(required = false) String updatedAt,
            @RequestParam(required = false) String manager) {
        LocalDate updatedDate = updatedAt != null ? LocalDate.parse(updatedAt) : null;
        List<EventResponseDto> events = eventService.getEvents(updatedDate, manager);
        return new ResponseEntity<>(events, HttpStatus.OK);
    }
}*/