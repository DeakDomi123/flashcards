package hu.unideb.inf.flashcards.service.mapper;

import hu.unideb.inf.flashcards.data.entity.UserStatisticsEntity;
import hu.unideb.inf.flashcards.service.dto.UserStatisticsDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserStatisticsMapper {

    private final ModelMapper modelMapper;

    public UserStatisticsMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public UserStatisticsDTO toDTO(UserStatisticsEntity entity) {
        return modelMapper.map(entity, UserStatisticsDTO.class);
    }

    public UserStatisticsEntity toEntity(UserStatisticsDTO dto) {
        return modelMapper.map(dto, UserStatisticsEntity.class);
    }
}