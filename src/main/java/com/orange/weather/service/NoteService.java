package com.orange.weather.service;

import com.orange.weather.entity.Note;
import com.orange.weather.exception.ObjectNotFoundException;
import com.orange.weather.repository.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NoteService {
    private final NoteRepository noteRepository;

    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    public Note getNoteById(int id) throws ObjectNotFoundException {
        Optional<Note> note = noteRepository.findById(id);
        if (note.isEmpty())
            throw new ObjectNotFoundException("note not found with id:" + id);

        return note.get();
    }

    @Transactional
    public Note createNote(Note request) {
        return noteRepository.save(request);
    }

    @Transactional
    public Note updateNote(int id, Note request) throws ObjectNotFoundException {
        Optional<Note> note = noteRepository.findById(id);
        if (note.isEmpty())
            throw new ObjectNotFoundException("note not found with id:" + id);

        return noteRepository.save(request);
    }

    @Transactional
    public void deleteNote(int id) throws ObjectNotFoundException {
        Optional<Note> note = noteRepository.findById(id);
        if (note.isEmpty())
            throw new ObjectNotFoundException("note not found with id:" + id);

        noteRepository.delete(note.get());
    }
}
