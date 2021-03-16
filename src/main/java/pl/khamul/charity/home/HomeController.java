package pl.khamul.charity.home;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.khamul.charity.category.Category;
import pl.khamul.charity.category.CategoryService;
import pl.khamul.charity.donation.Donation;
import pl.khamul.charity.donation.DonationService;
import pl.khamul.charity.institution.Institution;
import pl.khamul.charity.institution.InstitutionService;

import java.util.List;


@Controller
public class HomeController {
    private final InstitutionService institutionService;
    private final CategoryService categoryService;
    private final DonationService donationService;

    public HomeController(InstitutionService institutionService, CategoryService categoryService, DonationService donationService) {
        this.institutionService = institutionService;
        this.categoryService = categoryService;
        this.donationService = donationService;
    }

    @RequestMapping("/")
    public String homeAction(Model model) {
        List<Institution> institutionList = institutionService.institutionList();
        model.addAttribute("institutions", institutionList);
        model.addAttribute("quantity", donationService.totalDonationsQuantity() );
        model.addAttribute("number", donationService.totalDonationsNumber());


        return "index";
    }

    @RequestMapping("/donation")
    public String form(Model model) {
        List<Institution> institutionList = institutionService.institutionList();
        model.addAttribute("institutions", institutionList);
        Donation donation = new Donation();
        List<Category> categories = categoryService.categoryList();
        model.addAttribute("donation", donation);
        model.addAttribute("categories", categories);
        return "donationForm";
    }
}