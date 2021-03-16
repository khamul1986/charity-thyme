package pl.khamul.charity.donation;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.khamul.charity.category.CategoryService;
import pl.khamul.charity.institution.InstitutionService;


@Controller
@RequestMapping("/donation")
public class DonationController {

    private final DonationService donationService;
    private final CategoryService categoryService;
    private final InstitutionService institutionService;


    public DonationController(DonationService donationService, CategoryService categoryService, InstitutionService institutionService) {
        this.donationService = donationService;
        this.categoryService = categoryService;
        this.institutionService = institutionService;
    }


    @RequestMapping("/save")
    public String saveDonation( Donation donation){
        donationService.saveDonation(donation);

        return "redirect:/";
    }

}
