package com.gasthaus.reservation_service.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.gasthaus.reservation_service.dto.BlockedDatesRequestDTO;
import com.gasthaus.reservation_service.dto.BlockedDatesResponseDTO;
import com.gasthaus.reservation_service.exception.BlockedDatesNotFoundException;
import com.gasthaus.reservation_service.mapper.BlockedDatesMapper;
import com.gasthaus.reservation_service.models.BlockedDates;
import com.gasthaus.reservation_service.repository.BlockedDatesRepository;

@Service
public class BlockedDatesService {
    private final BlockedDatesRepository blockedDatesRepository;

    public BlockedDatesService(BlockedDatesRepository blockedDatesRepository) {
        this.blockedDatesRepository = blockedDatesRepository;
    }

    public List<BlockedDatesResponseDTO> getAllBlockedDates() {
        List<BlockedDates> blockedDates = blockedDatesRepository.findAll();
        return blockedDates.stream().map(BlockedDatesMapper::toDTO).toList();
    }

    public BlockedDatesResponseDTO getBlockedDatesById(UUID id) {
        BlockedDates blockedDates = blockedDatesRepository
            .findById(id)
            .orElseThrow(
                () -> new BlockedDatesNotFoundException("Blocked dates not found with id: " + id)
            );

        return BlockedDatesMapper.toDTO(blockedDates);
    }

    public BlockedDatesResponseDTO createBlockedDates(BlockedDatesRequestDTO requestDTO) {
        BlockedDates newBlockedDates = blockedDatesRepository.save(
            BlockedDatesMapper.toModel(requestDTO)
        );

        return BlockedDatesMapper.toDTO(newBlockedDates);
    }

    public void deleteBlockedDates(UUID id) {
        blockedDatesRepository.deleteById(id);
    }
}
