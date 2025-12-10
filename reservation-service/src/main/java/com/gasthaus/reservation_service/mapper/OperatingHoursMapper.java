package com.gasthaus.reservation_service.mapper;

import com.gasthaus.reservation_service.dto.OperatingHoursRequestDTO;
import com.gasthaus.reservation_service.dto.OperatingHoursResponseDTO;
import com.gasthaus.reservation_service.models.OperatingHours;

public class OperatingHoursMapper {

    public static OperatingHoursResponseDTO toDTO(OperatingHours operatingHours) {
        OperatingHoursResponseDTO dto = new OperatingHoursResponseDTO();
        dto.setId(operatingHours.getId().toString());
        dto.setDay(operatingHours.getDay());
        dto.setOpenTime(operatingHours.getOpenTime().toString());
        dto.setCloseTime(operatingHours.getCloseTime().toString());
        return dto;
    }

    public static OperatingHours toModel(OperatingHoursRequestDTO requestDTO) {
        OperatingHours operatingHours = new OperatingHours();
        operatingHours.setDay(requestDTO.getDay());
        operatingHours.setOpenTime(java.time.LocalTime.parse(requestDTO.getOpenTime()));
        operatingHours.setCloseTime(java.time.LocalTime.parse(requestDTO.getCloseTime()));
        return operatingHours;
    }
}
