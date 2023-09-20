package sa.account.management.mapper;

import sa.account.management.dto.AccountStatementDto;
import sa.account.management.model.AccountStatement;

public class AccountStatementMapper {

    private AccountStatementMapper() {}

    public static AccountStatementDto toAccountStatementDto(AccountStatement accountStatement) {
        return new AccountStatementDto(accountStatement.getId(), accountStatement.getDateField(), accountStatement.getAmount());
    }
}
