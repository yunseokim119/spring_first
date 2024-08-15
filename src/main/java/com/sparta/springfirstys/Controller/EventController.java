package com.sparta.springfirstys.Controller;

import com.sparta.springfirstys.dto.EventRequestDto;
import com.sparta.springfirstys.dto.EventResponseDto;
import com.sparta.springfirstys.entity.Event;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

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
    public List<EventResponseDto> getEvent(
            // @RequestParam으로 updatedAt(수정일)과 manager(담당자명)를 전달
            @RequestParam(required = false)String updateAt,
            @RequestParam(required = false)String manager) {
        // Map to list
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        // LocalDate 형식으로 변환된 수정일 필터를 사용하여 조건을 적용
        LocalDate filterDate = updateAt != null ? LocalDate.parse(updateAt, formatter) : null;

        List<EventResponseDto> responseList = eventList.values().stream()
                // stream()을 사용하여 eventList의 모든 Event 객체를 필터링
                .filter(event -> (filterDate == null || event.getUpdatedAt().toLocalDate().equals(filterDate)))
                .filter(event -> (manager == null || event.getManager().equalsIgnoreCase(manager)))
                .sorted(Comparator.comparing(Event::getUpdatedAt).reversed())
                .map(EventResponseDto::new)
                .collect(Collectors.toList());

        return responseList;
    }

    @PutMapping("/events/{id}")
    public EventResponseDto updateEvent(@PathVariable Long id, @RequestBody EventRequestDto requestDto){
        // 해당 일정이 DB에 존재하는지 확인
        if(eventList.containsKey(id)){
            // 해당 일정 가져오기
            Event event = eventList.get(id);

            // 비밀번호 확인
            if (!event.getPassword().equals(requestDto.getPassword())){
                throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
            }

            // 일정 수정 : 할일, 담당자명
            event.setTask(requestDto.getTask());
            event.setManager(requestDto.getManager());
            event.setUpdatedAt(LocalDateTime.now()); // 수정일 갱신

            // 수정된 일정의 정보 반환
            return new EventResponseDto(event);
        } else {
            throw new IllegalArgumentException("선택한 일정이 존재하지 않습니다.");
        }
    }

    @DeleteMapping("/events/{id}")
    public long deleteEvent(@PathVariable Long id, @RequestBody EventRequestDto requestDto){
        // 해당 일정이 DB에 존재하는지 확인
        if(eventList.containsKey(id)){
            // 해당 일정 가져오기
            Event event = eventList.get(id);

            // 비밀번호 확인
            if (!event.getPassword().equals(requestDto.getPassword())){
                throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
            }

            // 해당 일정 삭제하기
            eventList.remove(id);
            return id;
        } else {
            throw new IllegalArgumentException("선택한 일정이 존재하지 않습니다");
        }
    }
}