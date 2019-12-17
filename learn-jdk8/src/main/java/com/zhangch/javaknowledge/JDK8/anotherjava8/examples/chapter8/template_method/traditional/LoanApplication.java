package com.zhangch.javaknowledge.JDK8.anotherjava8.examples.chapter8.template_method.traditional;

import com.zhangch.javaknowledge.JDK8.anotherjava8.examples.chapter8.template_method.ApplicationDenied;

// BEGIN LoanApplication
public abstract class LoanApplication {

    public void checkLoanApplication() throws ApplicationDenied {
        checkIdentity();
        checkCreditHistory();
        checkIncomeHistory();
        reportFindings();
    }

    protected abstract void checkIdentity() throws ApplicationDenied;

    protected abstract void checkIncomeHistory() throws ApplicationDenied;

    protected abstract void checkCreditHistory() throws ApplicationDenied;

    private void reportFindings() {
// END LoanApplication

    }



}
