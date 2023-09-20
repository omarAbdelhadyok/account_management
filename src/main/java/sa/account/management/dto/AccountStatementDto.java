package sa.account.management.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@ToString
@EqualsAndHashCode
@Getter
public class AccountStatementDto {
    private final Integer id;
    private final LocalDate dateField;
    private final Double amount;

    public AccountStatementDto(Integer id, String dateField, String amount) {
        this.id = id;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy"); //19.11.2012
        this.dateField = LocalDate.parse(dateField, formatter);
        this.amount = Double.parseDouble(amount);
    }
}
