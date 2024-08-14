package com.sparta.springfirstys.service;

import com.sparta.springfirstys.dto.EventRequestDto;
import com.sparta.springfirstys.dto.EventResponseDto;
import com.sparta.springfirstys.entity.Event;
import com.sparta.springfirstys.repository.EventRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EventService {

    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }
    public void createEvent(EventRequestDto eventRequestDTO) {
        Event event = convertToEntity(eventRequestDTO);
        LocalDateTime now = LocalDateTime.now();
        event.setCreatedAt(now);
        event.setUpdatedAt(now);
        eventRepository.save(event);
    }

    public EventResponseDto getEvent(Long id) {
        Event event = eventRepository.findById(id);
        return event != null ? convertToResponseDto(event) : null;
    }

    private Event convertToEntity(EventRequestDto eventRequestDTO) {
        Event event = new Event();
        event.setTask(eventRequestDTO.getTask());
        event.setManager(eventRequestDTO.getManager());
        event.setStartTime(eventRequestDTO.getStartTime());
        event.setEndTime(eventRequestDTO.getEndTime());
        event.setPassword(eventRequestDTO.getPassword());
        return event;
    }

    private EventResponseDto convertToResponseDTO(Event event) {
        EventResponseDto eventResponseDTO = new EventResponseDto();
        eventResponseDTO.setId(event.getId());
        eventResponseDTO.setTask(event.getTask());
        eventResponseDTO.setManager(event.getManager());
        eventResponseDTO.setStartTime(event.getStartTime());
        eventResponseDTO.setEndTime(event.getEndTime());
        eventResponseDTO.setCreatedAt(event.getCreatedAt());
        eventResponseDTO.setUpdatedAt(event.getUpdatedAt());
        return eventResponseDTO;
    }
}