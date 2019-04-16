package CleanStreams.Controllers;

import CleanStreams.DTO.Account;
import CleanStreams.Forms.LoginForm;
import CleanStreams.Services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
public class AccountController {

    private AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

//    @RequestMapping(value = "/signup", method = RequestMethod.GET)
//    public String getSignupPage(SignupForm signupForm, Model model, @ModelAttribute("error") String error){
//        model.addAttribute("title", "Sign-up");
//        model.addAttribute("error", error);
//        return "webpage/signup";
//    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getloginPage(Model model, @ModelAttribute("error") String error){
        model.addAttribute("title", "Login");
        model.addAttribute("error", error);
        return "webpage/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String postloginPage(LoginForm loginForm, HttpSession httpSession, RedirectAttributes redirectAttributes) {

        String email = loginForm.getEmail();
        String password = loginForm.getPassword();
        Account account = accountService.findByEmailAndPassword(email, password);

        if (account == null) {
            redirectAttributes.addFlashAttribute("error", "Please enter a valid username and password");
            return "redirect:/login";
        } else {
            httpSession.setAttribute("account", account);

            if (account.getPermissions() == 1) {
//                httpSession.setAttribute("login", Admin);
                return "redirect:/admin";

            } else if (account.getPermissions() == 0) {
//                httpSession.setAttribute("login", people);
                return "redirect:/";

            }

            else {
                return "redirect:/";
            }

        }
    }


//    @RequestMapping(value = "/signup", method = RequestMethod.POST)
//    public String postSignupPage(@ModelAttribute @Valid SignupForm signupForm, BindingResult bindingResult, RedirectAttributes redirectAttributes){

//        if (bindingResult.hasErrors()) {
//            return "webpage/signup";
//        }

//        boolean result = companyService.createCompanyAccount(signupForm.getName(), signupForm.getEmail(), signupForm.getPassword());

//        if (result){
//            return "redirect:/login";
//        }
//
//        else {
//            redirectAttributes.addFlashAttribute("error", "E-mail already exists");
//            return "redirect:/signup";
//        }
//    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/login";
    }
}