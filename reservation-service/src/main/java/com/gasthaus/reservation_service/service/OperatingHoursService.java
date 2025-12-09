package com.gasthaus.reservation_service.service;

import java.util.List;

import org.springframework.stereotype.Service;

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

    public OperatingHoursResponseDTO getOperatingHoursById(String id) {
        OperatingHours operatingHours = operatingHoursRepository
            .findById(java.util.UUID.fromString(id))
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

    public OperatingHoursResponseDTO createOperatingHours(String day, String openTime, String closeTime) {
        if(operatingHoursRepository.existsByDay(day)) {
            throw new DayExistsException("Operating hours already exist for day: " + day);
        }

        OperatingHours newOperatingHours = operatingHoursRepository.save(
            OperatingHoursMapper.toModel(day, openTime, closeTime)
        );

        return OperatingHoursMapper.toDTO(newOperatingHours);
    }

    public OperatingHoursResponseDTO updateOperatingHours(String id, String day, String openTime, String closeTime) {
        OperatingHours operatingHours = operatingHoursRepository
        .findById(java.util.UUID.fromString(id))
        .orElseThrow(
            () -> new OperatingHoursNotFoundException("Operating hours not found with id: " + id)
        );

        if(operatingHoursRepository.existsByDay(day) && !operatingHours.getDay().equals(day)) {
            throw new DayExistsException("Operating hours already exist for day: " + day);
        }

        operatingHours.setDay(day);
        operatingHours.setOpenTime(java.time.LocalTime.parse(openTime));
        operatingHours.setCloseTime(java.time.LocalTime.parse(closeTime));

        OperatingHours updatedOperatingHours = operatingHoursRepository.save(operatingHours);

        return OperatingHoursMapper.toDTO(updatedOperatingHours);
    }

    public void deleteOperatingHours(String id) {
        operatingHoursRepository.deleteById(java.util.UUID.fromString(id));
    }
}
