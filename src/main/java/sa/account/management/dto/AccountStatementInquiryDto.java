package sa.account.management.dto;

import sa.account.management.constant.Exceptions;
import sa.account.management.util.DateValidator;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
public class AccountStatementInquiryDto {

    public static final LocalDate DEFAULT_START_DATE = LocalDate.of(2012, 1, 1); //due to data stored in the provided database
    public static final int DEFAULT_INQUIRY_RANGE = 3;

    private LocalDate startDate;
    private LocalDate endDate;
    private Double minAmount;
    private Double maxAmount;
    private String validationMessage;

    public AccountStatementInquiryDto(AccountStatementInquiry accountStatementInquiry, boolean isAdmin) {
        this.setStartDate(accountStatementInquiry.getStartDate());
        this.setEndDate(isAdmin, accountStatementInquiry.getEndDate());
        this.setMinAmount(isAdmin, accountStatementInquiry.getMinAmount());
        this.setMaxAmount(isAdmin, accountStatementInquiry.getMaxAmount());
    }

    private void setMinAmount(boolean isAdmin, String minAmount) {
        if (isAdmin) {
            if (!StringUtils.isBlank(minAmount)) {
                if (NumberUtils.isParsable(minAmount)) {
                    this.minAmount = Double.parseDouble(minAmount);
                } else {
                    this.validationMessage = Exceptions.INVALID_MIN_AMOUNT.get();
                    this.minAmount = 0d;
                }
            } else {
                this.minAmount = 0d;
            }
        } else {
            this.minAmount = 0d;
        }
    }

    private void setMaxAmount(boolean isAdmin, String maxAmount) {
        if (isAdmin) {
            if (!StringUtils.isBlank(maxAmount)) {
                if (NumberUtils.isParsable(maxAmount)) {
                    this.maxAmount = Double.parseDouble(maxAmount);
                } else {
                    this.maxAmount = 0d;
                    this.validationMessage = Exceptions.INVALID_MAX_AMOUNT.get();
                }
            } else {
                this.maxAmount = 0d;
            }
        } else {
            this.maxAmount = 0d;
        }
    }

    private void setStartDate(String startDate) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        if (!StringUtils.isBlank(startDate) && DateValidator.isValidDate(startDate, formatter)) {
            this.startDate = LocalDate.parse(startDate, formatter);
        } else {
            this.startDate = DEFAULT_START_DATE;
        }
    }

    private void setEndDate(boolean isAdmin, String endDate) {
        if (isAdmin) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            if (!StringUtils.isBlank(endDate) && DateValidator.isValidDate(endDate, formatter)) {
                this.endDate = LocalDate.parse(endDate, formatter);
            } else {
                this.endDate = this.startDate.plusMonths(DEFAULT_INQUIRY_RANGE);
            }
        } else {
            this.endDate = this.startDate.plusMonths(DEFAULT_INQUIRY_RANGE);
        }
    }

}
