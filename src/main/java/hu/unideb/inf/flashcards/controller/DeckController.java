package hu.unideb.inf.flashcards.controller;

import hu.unideb.inf.flashcards.service.DeckService;
import hu.unideb.inf.flashcards.service.dto.DeckDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/decks")
public class DeckController {

    private final DeckService deckService;

    public DeckController(DeckService deckService) {
        this.deckService = deckService;
    }

    @PostMapping
    public ResponseEntity<DeckDTO> createDeck(@RequestBody DeckDTO dto) {
        return ResponseEntity.ok(deckService.save(dto));
    }

    @GetMapping
    public ResponseEntity<List<DeckDTO>> getAllDecks() {
        return ResponseEntity.ok(deckService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeckDTO> getDeckById(@PathVariable Long id) {
        return ResponseEntity.ok(deckService.findById(id));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<DeckDTO>> getDecksByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(deckService.getDecksByUserId(userId));
    }

    @GetMapping("/search")
    public ResponseEntity<List<DeckDTO>> findDecksByName(@RequestParam String name) {
        return ResponseEntity.ok(deckService.findByName(name));
    }

    @PutMapping
    public ResponseEntity<DeckDTO> updateDeck(@RequestBody DeckDTO dto) {
        return ResponseEntity.ok(deckService.update(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDeck(@PathVariable Long id) {
        deckService.delete(id);
        return ResponseEntity.noContent().build();
    }
}