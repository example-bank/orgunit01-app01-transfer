package com.demobank.transfer.port.adapter.service.account;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.DefaultUriBuilderFactory;
import org.springframework.http.MediaType;

import com.demobank.transfer.domain.model.account.AccountService;
import com.demobank.transfer.domain.model.account.Transaction;
import com.demobank.transfer.domain.model.account.TransactionStatus;

@Service
public class AccountServiceREST implements AccountService {

    private String baseUrl;

    private RestClient restClient;

    private RestClient.Builder restClientBuilder;

    public AccountServiceREST() {
        super();
        this.setBaseUrl("http://localhost:8080/api/v1/account");
        this.setRestClientBuilder(RestClient.builder());
        DefaultUriBuilderFactory factory = new DefaultUriBuilderFactory(this.getBaseUrl());
        this.setRestClient(this.getRestClientBuilder().uriBuilderFactory(factory).build());
    }

    private String getBaseUrl() {
        return baseUrl;
    }

    private void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    private RestClient getRestClient() {
        return restClient;
    }

    private void setRestClient(RestClient restClient) {
        this.restClient = restClient;
    }

    private RestClient.Builder getRestClientBuilder() {
        return restClientBuilder;
    }

    private void setRestClientBuilder(RestClient.Builder restClientBuilder) {
        this.restClientBuilder = restClientBuilder;
    }

    public Transaction withdraw(String accountId, double amount, String currency) {
        TransactionResponse transactionResponse = this.getRestClient().post()
            .uri("/{accountId}/withdraw", accountId)
            .contentType(MediaType.APPLICATION_JSON)
            .body(new TransactionRequest(amount, currency))
            .retrieve()
            .body(TransactionResponse.class);
        
        return new Transaction(accountId, amount, currency, TransactionStatus.valueOf(transactionResponse.getStatus()), transactionResponse.getTransactionId(), transactionResponse.getNewBalance(), transactionResponse.getNewBalanceCurrency());
    }

    public Transaction deposit(String accountId, double amount, String currency) {
        TransactionResponse transactionResponse = this.getRestClient().post()
            .uri("/{accountId}/deposit", accountId)
            .contentType(MediaType.APPLICATION_JSON)
            .body(new TransactionRequest(amount, currency))
            .retrieve()
            .body(TransactionResponse.class);
        
        return new Transaction(
            accountId, 
            amount, 
            currency, 
            TransactionStatus.valueOf(transactionResponse.getStatus()), 
            transactionResponse.getTransactionId(), 
            transactionResponse.getNewBalance(), 
            transactionResponse.getNewBalanceCurrency());
    }
}