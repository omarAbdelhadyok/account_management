package sa.account.management.dto;

import sa.account.management.model.Account;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.format.DateTimeFormatter;
import java.util.List;

@ToString
@EqualsAndHashCode(callSuper = true)
@Getter
public class AccountWithStatementDto extends AccountDto {

    private final List<AccountStatementDto> accountStatements;
    private final String startDate;
    private final String endDate;
    private final String minAmount;
    private final String maxAmount;
    private final String validationMessage;


    public AccountWithStatementDto(Account account, List<AccountStatementDto> accountStatements, AccountStatementInquiryDto inquiryDto) {
        super(account);
        this.accountStatements = accountStatements;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.startDate = inquiryDto.getStartDate().format(formatter);
        this.endDate = inquiryDto.getEndDate().format(formatter);
        this.minAmount = String.valueOf(inquiryDto.getMinAmount());
        this.maxAmount = String.valueOf(inquiryDto.getMaxAmount());
        this.validationMessage = inquiryDto.getValidationMessage();
    }

}
