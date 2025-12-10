package com.gasthaus.reservation_service.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.gasthaus.reservation_service.dto.OperatingHoursRequestDTO;
import com.gasthaus.reservation_service.dto.OperatingHoursResponseDTO;
import com.gasthaus.reservation_service.exception.DayExistsException;
import com.gasthaus.reservation_service.exception.OperatingHoursNotFoundException;
import com.gasthaus.reservation_service.mapper.OperatingHoursMapper;
import com.gasthaus.reservation_service.models.OperatingHours;
import com.gasthaus.reservation_service.repository.OperatingHoursRepository;

@Service
public class OperatingHoursService {
    private OperatingHoursRepository operatingHoursRepository;

    public OperatingHoursService(OperatingHoursRepository operatingHoursRepository) {
        this.operatingHoursRepository = operatingHoursRepository;
    }

    public List<OperatingHoursResponseDTO> getAllOperatingHours() {
        List<OperatingHours> operatingHours = operatingHoursRepository.findAll();
        return operatingHours.stream().map(OperatingHoursMapper::toDTO).toList();
    }

    public OperatingHoursResponseDTO getOperatingHoursById(UUID id) {
        OperatingHours operatingHours = operatingHoursRepository
            .findById(id)
            .orElseThrow(
                () -> new OperatingHoursNotFoundException("Operating hours not found with id: " + id)
            );

        return OperatingHoursMapper.toDTO(operatingHours);
    }

    public OperatingHoursResponseDTO getOperatingHoursByDay(String day) {
        OperatingHours operatingHours = operatingHoursRepository
            .findByDay(day)
            .orElseThrow(
                () -> new OperatingHoursNotFoundException("Operating hours not found for day: " + day)
            );

        return OperatingHoursMapper.toDTO(operatingHours);
    }

    public OperatingHoursResponseDTO createOperatingHours(OperatingHoursRequestDTO requestDTO) {
        if(operatingHoursRepository.existsByDay(requestDTO.getDay())) {
            throw new DayExistsException("Operating hours already exist for day: " + requestDTO.getDay());
        }

        OperatingHours newOperatingHours = operatingHoursRepository.save(
            OperatingHoursMapper.toModel(requestDTO)
        );

        return OperatingHoursMapper.toDTO(newOperatingHours);
    }

    public OperatingHoursResponseDTO updateOperatingHours(UUID id, OperatingHoursRequestDTO requestDTO) {
        OperatingHours operatingHours = operatingHoursRepository
        .findById(id)
        .orElseThrow(
            () -> new OperatingHoursNotFoundException("Operating hours not found with id: " + id)
        );

        if(operatingHoursRepository.existsByDay(requestDTO.getDay()) && !operatingHours.getDay().equals(requestDTO.getDay())) {
            throw new DayExistsException("Operating hours already exist for day: " + requestDTO.getDay());
        }

        operatingHours.setDay(requestDTO.getDay());
        operatingHours.setOpenTime(java.time.LocalTime.parse(requestDTO.getOpenTime()));
        operatingHours.setCloseTime(java.time.LocalTime.parse(requestDTO.getCloseTime()));

        OperatingHours updatedOperatingHours = operatingHoursRepository.save(operatingHours);

        return OperatingHoursMapper.toDTO(updatedOperatingHours);
    }

    public void deleteOperatingHours(UUID id) {
        operatingHoursRepository.deleteById(id);
    }
}
