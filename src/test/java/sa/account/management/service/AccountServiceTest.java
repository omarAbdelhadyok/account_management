package sa.account.management.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import sa.account.management.dto.*;
import sa.account.management.mapper.AccountMapper;
import sa.account.management.model.Account;
import sa.account.management.model.Role;
import sa.account.management.model.User;
import sa.account.management.model.enums.RoleName;
import sa.account.management.repository.AccountRepository;
import sa.account.management.security.UserPrincipal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

    @Mock
    AccountRepository accountRepository;

    @InjectMocks
    AccountService accountService;

    static Account account;
    static List<Account> accounts = new ArrayList<>();
    static UserPrincipal userPrincipalAdmin;
    static UserPrincipal userPrincipalUser;
    static AccountStatementInquiry defaultStatementInquiry;
    static AccountStatementInquiry yearStatementInquiryAndAmountLimit;

    @BeforeAll
    static void setUp() {
        account = new Account();
        account.setId(1);
        account.setAccountNumber("1234");
        account.setAccountType("saving");
        account.setStatements(AccountTestData.getAccountStatements());
        accounts.add(account);

        User admin = new User();
        admin.setId(1);
        admin.setUsername("admin");
        Role role_admin = new Role();
        role_admin.setId(1);
        role_admin.setRoleName(RoleName.ROLE_ADMIN);
        admin.setRoles(Collections.singleton(role_admin));
        userPrincipalAdmin = UserPrincipal.create(admin);

        User user = new User();
        user.setId(2);
        user.setUsername("admin");
        Role role = new Role();
        role.setId(2);
        role.setRoleName(RoleName.ROLE_USER);
        user.setRoles(Collections.singleton(role));
        userPrincipalUser = UserPrincipal.create(user);

        defaultStatementInquiry = new AccountStatementInquiryTestDto(null, null, null, null);
        yearStatementInquiryAndAmountLimit = new AccountStatementInquiryTestDto("2012-01-01", "2013-01-01", "100", "300");
    }

    @Test
    void getAllAccountsTest() {
        given(accountRepository.findAll()).willReturn(accounts);
        List<AccountDto> allAccounts = accountService.getAllAccounts();
        assertThat(allAccounts).isEqualTo(accounts.stream().map(AccountMapper::mapToAccountDto).collect(Collectors.toList()));
    }

    @Test
    void getAccountByIdTest() {
        given(accountRepository.findById(1)).willReturn(Optional.of(account));
        AccountWithStatementDto accountWithStatementDto = accountService.getAccountById(userPrincipalAdmin, 1, defaultStatementInquiry);
        assertThat(accountWithStatementDto).isEqualTo(AccountTestData.getFirstThreeMonthsPeriod(true));
    }

    @Test
    void getAccountByIdTestForUserRegardlessOfParams() {
        given(accountRepository.findById(1)).willReturn(Optional.of(account));
        AccountWithStatementDto accountWithStatementDto = accountService.getAccountById(userPrincipalUser, 1, yearStatementInquiryAndAmountLimit);
        assertThat(accountWithStatementDto).isEqualTo(AccountTestData.getFirstThreeMonthsPeriod(false));
    }

    @Test
    void getAccountByIdTestForAdminWithOneYearAndAmount100To300() {
        given(accountRepository.findById(1)).willReturn(Optional.of(account));
        AccountWithStatementDto accountWithStatementDto = accountService.getAccountById(userPrincipalUser, 1, yearStatementInquiryAndAmountLimit);
        assertThat(accountWithStatementDto).isEqualTo(AccountTestData.getFirstThreeMonthsPeriod(true));
    }
}
