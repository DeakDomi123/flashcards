package hu.unideb.inf.flashcards.controller;

import hu.unideb.inf.flashcards.service.CommonService;
import hu.unideb.inf.flashcards.service.DeckService;
import hu.unideb.inf.flashcards.service.dto.DeckDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/decks")
public class DeckController {

    @Autowired
    DeckService deckService;

    @Autowired
    CommonService commonService;

    @PostMapping
    public ResponseEntity<DeckDTO> createDeck(@RequestBody DeckDTO dto,
                                              @AuthenticationPrincipal UserDetails userDetails) {
        var user = commonService.getCurrentUser(userDetails);
        return ResponseEntity.ok(deckService.save(dto, user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeckDTO> getDeckById(@PathVariable Long id) {
        return ResponseEntity.ok(deckService.findById(id));
    }

    @GetMapping()
    public ResponseEntity<List<DeckDTO>> getDecksByUser(@AuthenticationPrincipal UserDetails userDetails) {
        var user = commonService.getCurrentUser(userDetails);
        return ResponseEntity.ok(deckService.getDecksByUser(user));
    }

    @GetMapping("/search")
    public ResponseEntity<DeckDTO> findDecksByName(@RequestParam String name) {
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