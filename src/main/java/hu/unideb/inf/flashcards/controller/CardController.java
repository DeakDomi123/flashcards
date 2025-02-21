package hu.unideb.inf.flashcards.controller;

import hu.unideb.inf.flashcards.service.CardService;
import hu.unideb.inf.flashcards.service.dto.CardDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cards")
public class CardController {

    @Autowired
    CardService cardService;

    @PostMapping
    public ResponseEntity<CardDTO> createCard(@RequestBody CardDTO dto) {
        return ResponseEntity.ok(cardService.save(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CardDTO> getCardById(@PathVariable Long id) {
        return ResponseEntity.ok(cardService.findById(id));
    }

    @GetMapping("/deck/{deckId}")
    public ResponseEntity<List<CardDTO>> getCardsByDeckId(@PathVariable Long deckId) {
        return ResponseEntity.ok(cardService.getCardsByDeckId(deckId));
    }

    @PutMapping
    public ResponseEntity<CardDTO> updateCard(@RequestBody CardDTO dto) {
        return ResponseEntity.ok(cardService.update(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCard(@PathVariable Long id) {
        cardService.delete(id);
        return ResponseEntity.noContent().build();
    }
}