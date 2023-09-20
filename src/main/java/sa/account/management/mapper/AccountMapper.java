package sa.account.management.mapper;

import sa.account.management.dto.AccountDto;
import sa.account.management.dto.AccountStatementDto;
import sa.account.management.dto.AccountStatementInquiryDto;
import sa.account.management.dto.AccountWithStatementDto;
import sa.account.management.model.Account;

import java.util.List;

public class AccountMapper {

    private AccountMapper() {}

    public static AccountDto mapToAccountDto(Account account) {
        return new AccountDto(account);
    }

    public static AccountWithStatementDto mapToAccountWithStatementDto(Account account, List<AccountStatementDto> accountStatements, AccountStatementInquiryDto inquiryDto) {
        return new AccountWithStatementDto(account, accountStatements, inquiryDto);
    }
}
