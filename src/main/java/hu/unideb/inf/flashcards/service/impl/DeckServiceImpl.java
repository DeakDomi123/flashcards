package hu.unideb.inf.flashcards.service.impl;

import hu.unideb.inf.flashcards.data.entity.DeckEntity;
import hu.unideb.inf.flashcards.data.repository.DeckRepository;
import hu.unideb.inf.flashcards.data.repository.UserRepository;
import hu.unideb.inf.flashcards.service.DeckService;
import hu.unideb.inf.flashcards.service.dto.DeckDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeckServiceImpl implements DeckService {

    @Autowired
    DeckRepository repo;

    @Autowired
    ModelMapper mapper;

    @Autowired
    UserRepository userRepo;

    @Override
    public DeckDTO save(DeckDTO dto) {
        DeckEntity entity = mapper.map(dto, DeckEntity.class);
        entity.setUser(userRepo.findById(dto.getUserId()).orElseThrow(() -> new RuntimeException("User not found")));
        entity = repo.save(entity);
        return mapper.map(entity, DeckDTO.class);
    }

    @Override
    public List<DeckDTO> findAll() {
        List<DeckEntity> entities = repo.findAll();
        return mapper.map(entities, new TypeToken<List<DeckDTO>>(){}.getType());
    }

    @Override
    public List<DeckDTO> getDecksByUserId(Long userId) {
        List<DeckEntity> filtered;
        filtered = repo.findAll()
                .stream()
                .filter(x -> x.getUser().getId().equals(userId))
                .toList();

        return mapper.map(filtered, new TypeToken<List<DeckDTO>>(){}.getType());
    }

    @Override
    public List<DeckDTO> findByName(String name) {
        List<DeckEntity> filtered;
        filtered = repo.findAll()
                .stream()
                .filter(x -> x.getName().equals(name))
                .toList();

        return mapper.map(filtered, new TypeToken<List<DeckDTO>>(){}.getType());
    }

    @Override
    public DeckDTO findById(Long id) {
        DeckDTO dto = new DeckDTO();
        DeckEntity entity = repo.getReferenceById(id);

        dto.setId(entity.getId());
        dto.setUserId(entity.getUser().getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setDueDate(entity.getDueDate());
        return dto;
    }

    @Override
    public DeckDTO update(DeckDTO dto) {
        DeckEntity existingEntity = repo.findById(dto.getId()).orElseThrow(() -> new RuntimeException("Deck not found"));

        existingEntity.setName(dto.getName());
        existingEntity.setDescription(dto.getDescription());
        existingEntity.setDueDate(dto.getDueDate());

        DeckEntity updatedEntity = repo.save(existingEntity);
        return mapper.map(updatedEntity, DeckDTO.class);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }
}
