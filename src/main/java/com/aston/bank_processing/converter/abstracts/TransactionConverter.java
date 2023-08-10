package com.aston.bank_processing.converter.abstracts;

import com.aston.bank_processing.models.Transaction;
import com.aston.bank_processing.models.dto.abstracts.TransactionDto;

public interface TransactionConverter<T extends TransactionDto> {
    Transaction transactionCreatingDtoToTransaction(T transactionDto);
}
