package hu.unideb.inf.flashcards.service.mapper;

import hu.unideb.inf.flashcards.data.entity.DeckEntity;
import hu.unideb.inf.flashcards.service.dto.DeckDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class DeckMapper {

    private final ModelMapper modelMapper;

    public DeckMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public DeckDTO toDTO(DeckEntity deckEntity) {
        return modelMapper.map(deckEntity, DeckDTO.class);
    }

    public DeckEntity toEntity(DeckDTO deckDTO) {
        return modelMapper.map(deckDTO, DeckEntity.class);
    }
}
