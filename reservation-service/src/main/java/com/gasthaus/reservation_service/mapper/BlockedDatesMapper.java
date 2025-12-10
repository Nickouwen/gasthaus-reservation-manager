package com.gasthaus.reservation_service.mapper;

import com.gasthaus.reservation_service.dto.BlockedDatesRequestDTO;
import com.gasthaus.reservation_service.dto.BlockedDatesResponseDTO;
import com.gasthaus.reservation_service.models.BlockType;
import com.gasthaus.reservation_service.models.BlockedDates;

public class BlockedDatesMapper {
    public static BlockedDatesResponseDTO toDTO(BlockedDates blockedDates) {
        BlockedDatesResponseDTO dto = new BlockedDatesResponseDTO();
        dto.setId(blockedDates.getId().toString());
        dto.setStartDate(blockedDates.getStartDateTime().toString());
        dto.setEndDate(blockedDates.getEndDateTime().toString());
        dto.setReason(blockedDates.getReason());
        dto.setBlockType(blockedDates.getBlockType().toString());
        return dto;
    }

    public static BlockedDates toModel(BlockedDatesRequestDTO dto) {
        BlockedDates blockedDates = new BlockedDates();
        blockedDates.setStartDateTime(java.time.LocalDateTime.parse(dto.getStartDate()));
        blockedDates.setEndDateTime(java.time.LocalDateTime.parse(dto.getEndDate()));
        blockedDates.setReason(dto.getReason());
        blockedDates.setBlockType(Enum.valueOf(BlockType.class, dto.getBlockType()));
        return blockedDates;
    }
}
