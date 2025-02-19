package hu.unideb.inf.flashcards.service.mapper;

import hu.unideb.inf.flashcards.data.entity.CardEntity;
import hu.unideb.inf.flashcards.service.dto.CardDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CardMapper {

    private final ModelMapper modelMapper;

    public CardMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public CardDTO toDTO(CardEntity cardEntity) {
        return modelMapper.map(cardEntity, CardDTO.class);
    }

    public CardEntity toEntity(CardDTO cardDTO) {
        return modelMapper.map(cardDTO, CardEntity.class);
    }
}