package hu.unideb.inf.flashcards.service.mapper;

import hu.unideb.inf.flashcards.data.entity.UserEntity;
import hu.unideb.inf.flashcards.service.dto.UserDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    private final ModelMapper modelMapper;

    public UserMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public UserDTO toDTO(UserEntity userEntity) {
        return modelMapper.map(userEntity, UserDTO.class);
    }

    public UserEntity toEntity(UserDTO userDTO) {
        return modelMapper.map(userDTO, UserEntity.class);
    }
}