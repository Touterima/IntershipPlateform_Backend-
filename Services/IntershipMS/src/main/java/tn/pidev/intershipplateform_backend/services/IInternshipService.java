package tn.pidev.intershipplateform_backend.services;

import tn.pidev.intershipplateform_backend.dto.InternShipCreateDto;
import tn.pidev.intershipplateform_backend.entities.Internship;

import java.util.List;

public interface IInternshipService {

    List<Internship> getAllInternships();

    Internship getInternshipById(Long id);

    Internship createInternship(InternShipCreateDto internship);

    Internship updateInternship(Long id, Internship internship);

    void deleteInternship(Long id);
}


