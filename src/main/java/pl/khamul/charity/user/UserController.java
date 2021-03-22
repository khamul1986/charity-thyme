package pl.khamul.charity.user;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.khamul.charity.exception.UserAlreadyExistsException;
import pl.khamul.charity.loginregister.RegistrationService;

import javax.validation.Valid;


@Controller
public class UserController {


    private final RegistrationService registrationService;


    public UserController(RegistrationService registrationService) {

        this.registrationService = registrationService;
    }


    @GetMapping("login")
    public String loginForm(){
        return "login";
    }

    @GetMapping("/register")
    public String showRegistrationForm( Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("user", userDto);
        return "/register";
    }

    @PostMapping("/register")
    public String registerUserAccount(
            @ModelAttribute("user") @Valid UserDto userDto) {
        User user = new User();
        user.setUserName(userDto.getUserName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());

        try {
            registrationService.registerNewUserAccount(user);
        } catch (UserAlreadyExistsException uaeEx) {

            return "error";
        }

        return "confirm";
    }

    @RequestMapping("/user")
    public  String userPanel(){
        return "/user/user";

    }

    @RequestMapping("/admin")
    public String adminPanel(){
        return "/admin/admin";
    }

}
