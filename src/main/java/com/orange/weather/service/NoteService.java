package com.orange.weather.service;

import com.orange.weather.entity.Admin;
import com.orange.weather.entity.Note;
import com.orange.weather.entity.PredefinedNote;
import com.orange.weather.entity.User;
import com.orange.weather.exception.ObjectNotFoundException;
import com.orange.weather.exception.UnauthorizedAccessException;
import com.orange.weather.payload.request.NoteRequest;
import com.orange.weather.payload.request.PredefinedNoteRequest;
import com.orange.weather.payload.response.NoteResponse;
import com.orange.weather.repository.AdminRepository;
import com.orange.weather.repository.NoteRepository;
import com.orange.weather.repository.PredefinedNoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

@Service
@RequiredArgsConstructor
public class NoteService {
    private final NoteRepository noteRepository;
    private final PredefinedNoteRepository predefinedNoteRepository;
    private final AdminRepository adminRepository;

    public List<NoteResponse> getAllNotes() {
        List<Note> notes = noteRepository.findAll();

        List<NoteResponse> response = new ArrayList<>();
        for (Note note : notes) {
            response.add(new NoteResponse(note.getNote(), note.getCreationDate(), note.getAdmin().getName()));
        }
        return response;
    }

    public List<PredefinedNote> getAllPredefinedNotes() { return predefinedNoteRepository.findAll(); }

    public Note getNoteById(int id) throws ObjectNotFoundException {
        Optional<Note> note = noteRepository.findById(id);
        if (note.isEmpty())
            throw new ObjectNotFoundException("note not found with id:" + id);

        return note.get();
    }

    public List<NoteResponse> getTodayNotes() {
        LocalDateTime startOfDay = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0);
        LocalDateTime endOfDay = LocalDateTime.now().withHour(23).withMinute(59).withSecond(59);
        Instant instant = startOfDay.atZone(ZoneId.systemDefault()).toInstant();
        Date start = Date.from(instant);
        instant = endOfDay.atZone(ZoneId.systemDefault()).toInstant();
        Date end = Date.from(instant);
        List<Note> todayNotes = noteRepository.findAllByCreationDateBetweenOrderByCreationDateDesc(start, end);
        List<NoteResponse> response = new ArrayList<>();
        for (Note note : todayNotes) {
            response.add(new NoteResponse(note.getNote(), note.getCreationDate(), note.getAdmin().getName()));

        }
        System.out.println(todayNotes);
        return response;
    }

    @Transactional
    public Note createNote(NoteRequest request) {
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<Admin> admin = adminRepository.findByEmail(user.getUsername());

        Note note = new Note(request.getNote(), new Date(), admin.get());

        return noteRepository.save(note);
    }

    @Transactional
    public void deleteNote(int id) throws ObjectNotFoundException, UnauthorizedAccessException {
        Optional<Note> note = noteRepository.findById(id);
        if (note.isEmpty())
            throw new ObjectNotFoundException("note not found with id:" + id);

        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<Admin> admin = adminRepository.findByEmail(user.getUsername());

        if (!note.get().getAdmin().equals(admin.get()))
            throw new UnauthorizedAccessException();

        noteRepository.delete(note.get());
    }

    @Transactional
    public PredefinedNote createPredefinedNote(PredefinedNoteRequest request) {
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<Admin> admin = adminRepository.findByEmail(user.getUsername());

        PredefinedNote note = new PredefinedNote(
                request.getNote(),
                request.getMinTemp(),
                request.getMaxTemp(),
                new Date(),
                admin.get());

        return predefinedNoteRepository.save(note);
    }

    @Transactional
    public PredefinedNote updatePredefinedNote(int id, PredefinedNoteRequest request) throws ObjectNotFoundException {
        Optional<PredefinedNote> note = predefinedNoteRepository.findById(id);
        if (note.isEmpty())
            throw new ObjectNotFoundException("note not found with id:" + id);

        note.get().setNote(request.getNote());
        note.get().setMinTemp(request.getMinTemp());
        note.get().setMaxTemp(request.getMaxTemp());
        note.get().setLastEditDate(new Date());

        return predefinedNoteRepository.save(note.get());
    }

    @Transactional
    public void deletePredefinedNote(int id) throws ObjectNotFoundException, UnauthorizedAccessException {
        Optional<PredefinedNote> note = predefinedNoteRepository.findById(id);
        if (note.isEmpty())
            throw new ObjectNotFoundException("note not found with id:" + id);

        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<Admin> admin = adminRepository.findByEmail(user.getUsername());

        if (!note.get().getAdmin().equals(admin.get()))
            throw new UnauthorizedAccessException();

        predefinedNoteRepository.delete(note.get());
    }

    public List<Note> getAuthAdminNotes() {
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<Admin> admin = adminRepository.findByEmail(user.getUsername());

        return admin.get().getNotes();
    }

    public PredefinedNote getAuthAdminPredefinedNotes() {
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<Admin> admin = adminRepository.findByEmail(user.getUsername());

        return admin.get().getPredefinedNote();
    }
}
