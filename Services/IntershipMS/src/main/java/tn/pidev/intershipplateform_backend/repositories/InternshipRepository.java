package tn.pidev.intershipplateform_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.pidev.intershipplateform_backend.entities.Internship;

public interface InternshipRepository extends JpaRepository<Internship, Long> {
}

