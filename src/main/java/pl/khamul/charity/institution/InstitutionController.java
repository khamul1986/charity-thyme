package pl.khamul.charity.institution;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/institution")
public class InstitutionController {
    private final InstitutionService institutionService;

    public InstitutionController(InstitutionService institutionService) {
        this.institutionService = institutionService;
    }

    @GetMapping("/add")
    public String addInstitution(Model model){
        model.addAttribute("institution", new Institution());
        return"/admin/institutionAddEdit";
    }

    @PostMapping("add")
    public String saveAddedInstitution(@ModelAttribute Institution institution){
        institutionService.saveInstitution(institution);
        return "/confirm";
    }

}
