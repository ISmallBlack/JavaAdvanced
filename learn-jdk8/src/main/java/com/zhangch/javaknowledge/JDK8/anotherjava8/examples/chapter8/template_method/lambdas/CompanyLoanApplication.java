package com.zhangch.javaknowledge.JDK8.anotherjava8.examples.chapter8.template_method.lambdas;

// BEGIN CompanyLoanApplication
public class CompanyLoanApplication extends LoanApplication {

    public CompanyLoanApplication(Company company) {
        super(company::checkIdentity,
              company::checkHistoricalDebt,
              company::checkProfitAndLoss);
    }

}
// END CompanyLoanApplication
