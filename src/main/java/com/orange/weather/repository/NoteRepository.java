package com.orange.weather.repository;

import com.orange.weather.entity.Admin;
import com.orange.weather.entity.Note;
import com.orange.weather.payload.response.NoteResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Integer> {

    List<Note> findAllByCreationDateBetweenOrderByCreationDateDesc(Date startOfDay, Date endOfDay);
    void deleteAllByAdmin(Admin admin);
}
