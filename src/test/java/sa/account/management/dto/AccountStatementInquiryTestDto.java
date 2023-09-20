package sa.account.management.dto;

public class AccountStatementInquiryTestDto implements AccountStatementInquiry {
    private final String startDate;
    private final String endDate;
    private final String minAmount;
    private final String maxAmount;

    public AccountStatementInquiryTestDto(String startDate, String endDate, String minAmount, String maxAmount) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.minAmount = minAmount;
        this.maxAmount = maxAmount;
    }

    @Override
    public String getStartDate() {
        return startDate;
    }

    @Override
    public String getEndDate() {
        return endDate;
    }

    @Override
    public String getMinAmount() {
        return minAmount;
    }

    @Override
    public String getMaxAmount() {
        return maxAmount;
    }
}
