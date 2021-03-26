package com.irek.conference.repository;

import com.irek.conference.entity.Presentation;
import org.springframework.data.repository.CrudRepository;

public interface PresentationRepository extends CrudRepository<Presentation, Integer> {
}
