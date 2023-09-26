package com.orange.weather.repository;

import com.orange.weather.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Integer> {

    List<Note> findAllByCreationDateBetweenOrderByCreationDateDesc(Date startOfDay, Date endOfDay);
}
