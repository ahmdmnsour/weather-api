package com.orange.weather.controller;

import com.orange.weather.entity.Note;
import com.orange.weather.entity.PredefinedNote;
import com.orange.weather.exception.ObjectNotFoundException;
import com.orange.weather.exception.UnauthorizedAccessException;
import com.orange.weather.payload.request.NoteRequest;
import com.orange.weather.payload.request.PredefinedNoteRequest;
import com.orange.weather.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping("/api/predefined")
@RequiredArgsConstructor
public class PredefinedNoteController {
    private final NoteService noteService;

    @GetMapping
    public ResponseEntity<List<PredefinedNote>> getAllPredefinedNotes() {
        return ResponseEntity.ok(noteService.getAllPredefinedNotes());
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<PredefinedNote> getNoteById(@PathVariable int id) throws ObjectNotFoundException {
//        return ResponseEntity.ok(noteService.get(id));
//    }

    @PostMapping
    public ResponseEntity<PredefinedNote> createPredefinedNote(@RequestBody PredefinedNoteRequest request) {
        return ResponseEntity.ok(noteService.createPredefinedNote(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PredefinedNote> updatePredefinedNote(@PathVariable int id, @RequestBody PredefinedNoteRequest request) throws ObjectNotFoundException {
        return ResponseEntity.ok(noteService.updatePredefinedNote(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePredefinedNote(@PathVariable int id) throws ObjectNotFoundException, UnauthorizedAccessException {
        noteService.deletePredefinedNote(id);
        return ResponseEntity.ok("Note deleted");
    }
}
