package sa.account.management.dto;

import sa.account.management.model.Account;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@EqualsAndHashCode
public class AccountDto {
    private final Integer accountId;
    private final String accountType;
    private final String accountNumber;

    public AccountDto(Account account) {
        this.accountId = account.getId();
        this.accountType = account.getAccountType();
        this.accountNumber = account.getAccountNumber();
    }
}
