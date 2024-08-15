package com.sparta.springfirstys.service;

import com.sparta.springfirstys.dto.EventRequestDto;
import com.sparta.springfirstys.dto.EventResponseDto;
import com.sparta.springfirstys.entity.Event;
import com.sparta.springfirstys.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventService {

    private final EventRepository eventRepository;

    @Autowired
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public EventResponseDto createEvent(EventRequestDto requestDto) {
        Event event = new Event(requestDto);
        eventRepository.save(event);
        return new EventResponseDto(event);
    }

    public EventResponseDto getEvent(Long id) {
        Event event = eventRepository.findById(id);
        if (event == null) {
            throw new IllegalArgumentException("선택한 일정이 존재하지 않습니다.");
        }
        return new EventResponseDto(event);
    }

    public List<EventResponseDto> getEvents() {
        return eventRepository.findAll().stream()
                .map(EventResponseDto::new)
                .collect(Collectors.toList());
    }

    public EventResponseDto updateEvent(Long id, EventRequestDto requestDto) {
        Event event = eventRepository.findById(id);
        if (event == null) {
            throw new IllegalArgumentException("선택한 일정이 존재하지 않습니다.");
        }
        if (!event.getPassword().equals(requestDto.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        event.setTask(requestDto.getTask());
        event.setManager(requestDto.getManager());
        event.setUpdatedAt(LocalDateTime.now());
        eventRepository.update(event);

        return new EventResponseDto(event);
    }

    public void deleteEvent(Long id, String password) {
        Event event = eventRepository.findById(id);
        if (event == null) {
            throw new IllegalArgumentException("선택한 일정이 존재하지 않습니다.");
        }
        if (!event.getPassword().equals(password)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        eventRepository.delete(id);
    }
}