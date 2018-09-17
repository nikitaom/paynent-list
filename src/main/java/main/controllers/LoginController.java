package main.controllers;

import main.entity.Role;
import main.entity.User;
import main.repository.RoleRepository;
import main.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.soap.SOAPBinding;
import javax.transaction.Transactional;
import java.security.Principal;
import java.util.Arrays;
import java.util.HashSet;


@Controller
public class LoginController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping(value = "/")
    public ModelAndView welcome() {
        return new ModelAndView("main");
    }

    @GetMapping(value = "/welcome")
    public String welcsssome(Principal principal,ModelMap model,User user) {
        String info = "Log in success";
              // model.addAttribute("name", principal.getName());
        String name = userRepository.findByEmail(principal.getName()).getName();
        model.addAttribute("info", info);
        model.addAttribute("name", name);
        return "welcome";
    }

    @GetMapping(value = "/log_in")
    public String login(Model model) {
        model.addAttribute("user", new User());
        return "log_in";
    }

//    @PostMapping(value = "/login")
//    public String login(Model model, User user) {
//        model.addAttribute("user", user);
//        return "welcome";
//    }
    /*Test*/

    @GetMapping(value = "/signup")
    public String signup(ModelMap model) {
        model.addAttribute("user", new User());
        return "signup";
    }

    @Transactional
    @PostMapping(value = "/signup")
    public String signup(User user1, ModelMap model) {

        User user = userRepository.findByEmail(user1.getEmail());
        if (user == null) {
            user1.setPassword(bCryptPasswordEncoder.encode(user1.getPassword()));
            Role userRole = roleRepository.findByRole("USER_ROLE");
            System.out.println(userRole.toString());
            user1.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
            userRepository.save(user1);
            String info = "Regestrated success";
            model.addAttribute("info", info);
            model.addAttribute("name", user1.getName());
            return "welcome";
        }else
        return "signup";
    }
}
