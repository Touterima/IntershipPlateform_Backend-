package tn.pidev.intershipplateform_backend.restControllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.pidev.intershipplateform_backend.dto.InternShipCreateDto;
import tn.pidev.intershipplateform_backend.entities.Internship;
import tn.pidev.intershipplateform_backend.services.InternshipService;

import java.util.List;

@RestController
@RequestMapping("/api/internships")
public class InternshipController {

    @Autowired
    private InternshipService service;

    @GetMapping
    public List<Internship> getAllInternships() {
        return service.getAllInternships();
    }

    @GetMapping("/{id}")
    public Internship getInternshipById(@PathVariable Long id) {
        return service.getInternshipById(id);
    }

    @PostMapping("/add_internships")
    public Internship createInternship(@Valid @RequestBody InternShipCreateDto internship) {
        return service.createInternship(internship);
    }

    @PutMapping("/update_internships/{id}")
    public Internship updateInternship(@PathVariable Long id, @Valid @RequestBody Internship internship) {
        return service.updateInternship(id, internship);
    }

    @DeleteMapping("/delete_internships/{id}")
    public void deleteInternship(@PathVariable Long id) {
        service.deleteInternship(id);
    }
}