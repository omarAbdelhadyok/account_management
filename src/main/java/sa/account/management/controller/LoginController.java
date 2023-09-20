package sa.account.management.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class LoginController {


    @PreAuthorize("isAnonymous()")
    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
