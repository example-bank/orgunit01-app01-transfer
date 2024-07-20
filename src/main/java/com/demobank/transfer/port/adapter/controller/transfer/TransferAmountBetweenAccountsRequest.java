package com.demobank.transfer.port.adapter.controller.transfer;

import java.math.BigDecimal;
import java.math.BigInteger;

public class TransferAmountBetweenAccountsRequest {
    private BigInteger fromAccountId;
    private BigInteger toAccountId;
    private BigDecimal amount;
    private String currencyCode;

    public TransferAmountBetweenAccountsRequest(BigInteger fromAccountId, BigInteger toAccountId, BigDecimal amount, String currencyCode) {
        super();

        this.setFromAccountId(fromAccountId);
        this.setToAccountId(toAccountId);
        this.setAmount(amount);
        this.setCurrencyCode(currencyCode);
    }

    public TransferAmountBetweenAccountsRequest() {
        super();
    }

    public BigInteger getFromAccountId() {
        return fromAccountId;
    }

    private void setFromAccountId(BigInteger fromAccountId) {
        this.fromAccountId = fromAccountId;
    }

    public BigInteger getToAccountId() {
        return toAccountId;
    }

    private void setToAccountId(BigInteger toAccountId) {
        this.toAccountId = toAccountId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    private void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    private void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }
}