package sa.account.management.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import sa.account.management.dto.*;
import sa.account.management.exception.ApiException;
import sa.account.management.mapper.AccountMapper;
import sa.account.management.mapper.AccountStatementMapper;
import sa.account.management.model.Account;
import sa.account.management.repository.AccountRepository;
import sa.account.management.security.UserPrincipal;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AccountService {

    private final AccountRepository accountRepository;


    public List<AccountDto> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        return accounts.stream().map(AccountMapper::mapToAccountDto).collect(Collectors.toList());
    }

    public AccountWithStatementDto getAccountById(UserPrincipal userPrincipal, Integer accountId, AccountStatementInquiry accountStatementInquiry) {
        AccountStatementInquiryDto inquiryDto = new AccountStatementInquiryDto(accountStatementInquiry, userPrincipal.isAdmin());
        Account account = accountRepository.findById(accountId).orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Account was not found"));
        List<AccountStatementDto> accountStatements = getAccountStatements(account, inquiryDto);
        return AccountMapper.mapToAccountWithStatementDto(account, accountStatements, inquiryDto);
    }

    private static List<AccountStatementDto> getAccountStatements(Account account, AccountStatementInquiryDto inquiryDto) {
        return account.getStatements().stream()
                .map(AccountStatementMapper::toAccountStatementDto)
                .filter(statement -> isWithinDateAndAmountRange(statement, inquiryDto))
                .sorted(Comparator.comparing(AccountStatementDto::getDateField))
                .collect(Collectors.toList());
    }

    private static boolean isWithinDateAndAmountRange(AccountStatementDto accountStatementDto, AccountStatementInquiryDto inquiryDto) {
        boolean isWithinAmountRange = (accountStatementDto.getAmount() >= inquiryDto.getMinAmount()) && (inquiryDto.getMaxAmount() == 0 || accountStatementDto.getAmount() <= inquiryDto.getMaxAmount());
        boolean isWithinDateRange = accountStatementDto.getDateField().isAfter(inquiryDto.getStartDate()) && accountStatementDto.getDateField().isBefore(inquiryDto.getEndDate());
        return isWithinDateRange && isWithinAmountRange;
    }

}
