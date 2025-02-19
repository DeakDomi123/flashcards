package hu.unideb.inf.flashcards.service.mapper;

import hu.unideb.inf.flashcards.data.entity.StudySessionsEntity;
import hu.unideb.inf.flashcards.service.dto.StudySessionDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class StudySessionMapper {

    private final ModelMapper modelMapper;

    public StudySessionMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public StudySessionDTO toDTO(StudySessionsEntity entity) {
        return modelMapper.map(entity, StudySessionDTO.class);
    }

    public StudySessionsEntity toEntity(StudySessionDTO dto) {
        return modelMapper.map(dto, StudySessionsEntity.class);
    }
}