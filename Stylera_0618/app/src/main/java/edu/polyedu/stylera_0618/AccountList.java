package edu.polyedu.stylera_0618;

public class AccountList {
    private String accountId;
    private String accountPw;

    public AccountList(String accountId, String accountPw){
        this.accountId = accountId;
        this.accountPw = accountPw;
    }

    public String getAccountId() {
        return accountId;
    }
    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }
    public String getAccountPw() {
        return accountPw;
    }
    public void setAccountPw(String accountPw) {
        this.accountPw = accountPw;
    }
}
