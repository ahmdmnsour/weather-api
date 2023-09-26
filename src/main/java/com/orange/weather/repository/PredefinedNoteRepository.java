package com.orange.weather.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.orange.weather.entity.PredefinedNote;

public interface PredefinedNoteRepository extends JpaRepository<PredefinedNote, Integer> {
}
