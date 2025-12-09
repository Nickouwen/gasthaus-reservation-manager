package com.gasthaus.reservation_service.mapper;

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

    public static OperatingHours toModel(String day, String openTime, String closeTime) {
        OperatingHours operatingHours = new OperatingHours();
        operatingHours.setDay(day);
        operatingHours.setOpenTime(java.time.LocalTime.parse(openTime));
        operatingHours.setCloseTime(java.time.LocalTime.parse(closeTime));
        return operatingHours;
    }
}
