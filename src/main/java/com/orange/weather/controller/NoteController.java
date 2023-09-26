package com.orange.weather.controller;

import com.orange.weather.entity.Note;
import com.orange.weather.entity.PredefinedNote;
import com.orange.weather.exception.ObjectNotFoundException;
import com.orange.weather.exception.UnauthorizedAccessException;
import com.orange.weather.payload.request.NoteRequest;
import com.orange.weather.service.AdminService;
import com.orange.weather.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Not;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
@RequiredArgsConstructor
public class NoteController {
    private final NoteService noteService;

    @GetMapping
    public ResponseEntity<List<Note>> getAllNotes() {
        return ResponseEntity.ok(noteService.getAllNotes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Note> getNoteById(@PathVariable int id) throws ObjectNotFoundException {
        return ResponseEntity.ok(noteService.getNoteById(id));
    }

    @GetMapping("/me") ResponseEntity<List<Note>> getAuthAdminNotes() {
        return ResponseEntity.ok(noteService.getAuthAdminNotes());
    }

    @PostMapping
    public ResponseEntity<Note> createNote(@RequestBody NoteRequest request) {
        return ResponseEntity.ok(noteService.createNote(request));
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<Note> updateNote(@PathVariable int id, @RequestBody NoteRequest request) throws ObjectNotFoundException {
//        return ResponseEntity.ok(noteService.updateNote(id, request));
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteNote(@PathVariable int id) throws ObjectNotFoundException, UnauthorizedAccessException {
        noteService.deleteNote(id);
        return ResponseEntity.ok("Note deleted");
    }

}
