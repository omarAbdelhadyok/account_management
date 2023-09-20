package sa.account.management.controller;

import sa.account.management.dto.AccountStatementInquiry;
import sa.account.management.dto.AccountWithStatementDto;
import sa.account.management.security.CurrentUser;
import sa.account.management.security.UserPrincipal;
import sa.account.management.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/")
    public String getHomePage(Model model) {
        model.addAttribute("accounts", accountService.getAllAccounts());
        return "index";
    }

    @GetMapping("/statement/{id}")
    public String getAccountStatement(@CurrentUser UserPrincipal userPrincipal,
                                      @PathVariable Integer id,
                                      AccountStatementInquiry accountStatementInquiry,
                                      Model model) {

        AccountWithStatementDto account = accountService.getAccountById(userPrincipal, id, accountStatementInquiry);
        model.addAttribute("account", account);
        model.addAttribute("error", account.getValidationMessage());
        model.addAttribute("isAdmin", userPrincipal.isAdmin());
        return "statement";
    }
}
