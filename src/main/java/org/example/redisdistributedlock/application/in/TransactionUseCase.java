package org.example.redisdistributedlock.application.in;

public interface TransactionUseCase {
    // -> 송금하라
    void wire(Long from, Long to, Long amount);
}
