package tn.pidev.intershipplateform_backend.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.pidev.intershipplateform_backend.dto.InternShipCreateDto;
import tn.pidev.intershipplateform_backend.entities.Internship;
import tn.pidev.intershipplateform_backend.entities.InternshipValidationStatus;
import tn.pidev.intershipplateform_backend.repositories.InternshipRepository;

import java.util.List;

@Service
public class InternshipService {

    @Autowired
    private tn.pidev.intershipplateform_backend.repositories.InternshipRepository repository;

    public List<Internship> getAllInternships() {
        return repository.findAll();
    }

    public Internship getInternshipById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Internship createInternship(InternShipCreateDto internShipCreateDto) {
        Internship internship=new Internship(internShipCreateDto.getType(),internShipCreateDto.getSupervisorEmail(),internShipCreateDto.getCompanyName(),internShipCreateDto.getCompanyAddress());
        internship.setStatus(InternshipValidationStatus.INCOMPLETE);
        return repository.save(internship);
    }

    public Internship updateInternship(Long id, Internship internship) {
        if (repository.existsById(id)) {
            internship.setIdInternship(id);
            return repository.save(internship);
        }
        return null;
    }

    public void deleteInternship(Long id) {
        repository.deleteById(id);
    }
}


