package sa.account.management.dto;

import sa.account.management.model.Account;
import sa.account.management.model.AccountStatement;

import java.util.*;
import java.util.stream.Collectors;

public class AccountTestData {

    public static Set<AccountStatement> getAccountStatements() {
        Set<AccountStatement> accountStatements = new HashSet<>();
        accountStatements.add(new AccountStatement(64, "08.10.2012", "396.809586959555"));
        accountStatements.add(new AccountStatement(65, "30.12.2012", "811.442741186215"));
        accountStatements.add(new AccountStatement(130, "14.11.2012", "376.917602681929"));
        accountStatements.add(new AccountStatement(69, "08.01.2012", "161.803506518358"));
        accountStatements.add(new AccountStatement(72, "12.08.2012", "427.37803489619"));
        accountStatements.add(new AccountStatement(136, "23.11.2019", "103.422615473659"));
        accountStatements.add(new AccountStatement(9, "14.10.2020", "196.801905945903"));
        accountStatements.add(new AccountStatement(138, "24.05.2020", "301.475914804549"));
        accountStatements.add(new AccountStatement(13, "01.07.2020", "869.966470373683"));
        accountStatements.add(new AccountStatement(14, "05.05.2020", "578.931756366243"));
        accountStatements.add(new AccountStatement(81, "20.06.2012", "258.669010375204"));
        accountStatements.add(new AccountStatement(82, "29.08.2012", "537.263739510225"));
        accountStatements.add(new AccountStatement(85, "04.05.2012", "289.279082576802"));
        accountStatements.add(new AccountStatement(88, "13.07.2012", "585.269254817154"));
        accountStatements.add(new AccountStatement(89, "19.11.2012", "10.4978134308631"));
        accountStatements.add(new AccountStatement(92, "05.09.2012", "968.120471616201"));
        accountStatements.add(new AccountStatement(29, "26.05.2020", "191.608098447429"));
        accountStatements.add(new AccountStatement(94, "04.07.2012", "113.405299633134"));
        accountStatements.add(new AccountStatement(39, "18.10.2016", "304.375774283414"));
        accountStatements.add(new AccountStatement(43, "21.10.2019", "113.619329901574"));
        accountStatements.add(new AccountStatement(45, "14.05.2019", "957.272849951391"));
        accountStatements.add(new AccountStatement(46, "31.05.2019", "376.672352972369"));
        accountStatements.add(new AccountStatement(47, "13.01.2012", "893.061976381444"));
        accountStatements.add(new AccountStatement(48, "03.03.2012", "373.950606558506"));
        accountStatements.add(new AccountStatement(112, "15.01.2012", "732.785475399635"));
        accountStatements.add(new AccountStatement(53, "20.02.2012", "387.671104146657"));
        accountStatements.add(new AccountStatement(117, "28.06.2012", "35.4587802498303"));
        accountStatements.add(new AccountStatement(57, "14.03.2012", "332.588024293528"));
        accountStatements.add(new AccountStatement(121, "08.04.2012", "452.220841246041"));
        accountStatements.add(new AccountStatement(58, "05.05.2012", "803.304917411143"));
        accountStatements.add(new AccountStatement(61, "31.07.2012", "828.245746980652"));
        return accountStatements;
    }

    public static AccountWithStatementDto getFirstThreeMonthsPeriod(boolean isAdmin) {
        Account account = new Account();
        account.setId(1);
        account.setAccountNumber("1234");
        account.setAccountType("saving");
        account.setStatements(AccountTestData.getAccountStatements());

        List<AccountStatementDto> accountStatements = new ArrayList<>();
        accountStatements.add(new AccountStatementDto(69, "08.01.2012", "161.803506518358"));
        accountStatements.add(new AccountStatementDto(47, "13.01.2012", "893.061976381444"));
        accountStatements.add(new AccountStatementDto(48, "03.03.2012", "373.950606558506"));
        accountStatements.add(new AccountStatementDto(53, "20.02.2012", "387.671104146657"));
        accountStatements.add(new AccountStatementDto(57, "14.03.2012", "332.588024293528"));
        accountStatements.add(new AccountStatementDto(112, "15.01.2012", "732.785475399635"));
        List<AccountStatementDto> sortedAccountStatements = accountStatements.stream().sorted(Comparator.comparing(AccountStatementDto::getDateField)).collect(Collectors.toList());

        AccountStatementInquiry accountStatementInquiryTestDto = new AccountStatementInquiryTestDto(null, null, null, null);
        AccountStatementInquiryDto accountStatementInquiryDto = new AccountStatementInquiryDto(accountStatementInquiryTestDto, isAdmin);

        return new AccountWithStatementDto(account, sortedAccountStatements, accountStatementInquiryDto);
    }

    public static AccountWithStatementDto getOneYearAndAmountRange100To300(boolean isAdmin) {
        Account account = new Account();
        account.setId(1);
        account.setAccountNumber("1234");
        account.setAccountType("saving");
        account.setStatements(AccountTestData.getAccountStatements());

        List<AccountStatementDto> accountStatements = new ArrayList<>();
        accountStatements.add(new AccountStatementDto(69, "08.01.2012", "161.803506518358"));
        accountStatements.add(new AccountStatementDto(94, "04.07.2012", "113.405299633134"));
        accountStatements.add(new AccountStatementDto(85, "04.05.2012", "289.279082576802"));
        accountStatements.add(new AccountStatementDto(81, "20.06.2012", "258.669010375204"));
        List<AccountStatementDto> sortedAccountStatements = accountStatements.stream().sorted(Comparator.comparing(AccountStatementDto::getDateField)).collect(Collectors.toList());

        AccountStatementInquiry accountStatementInquiryTestDto = new AccountStatementInquiryTestDto(null, null, null, null);
        AccountStatementInquiryDto accountStatementInquiryDto = new AccountStatementInquiryDto(accountStatementInquiryTestDto, isAdmin);

        return new AccountWithStatementDto(account, sortedAccountStatements, accountStatementInquiryDto);
    }
}
