package test;

import dao.TransactionCrudOperation;
import model.Transaction;
import org.junit.jupiter.api.Test;
import utils.GetAccountOfData;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TransactionTest implements  TestOperation{
    TransactionCrudOperation transactionCrudOperation = new TransactionCrudOperation();
    GetAccountOfData data = new GetAccountOfData();

    //----------------------------------------variable----------------------------:
    LocalDateTime date = LocalDateTime.of(2023,12,8,12,0,0);
    BigDecimal amount = new BigDecimal("400.45");
    BigDecimal creditAmount = new BigDecimal("300.45");
    BigDecimal debitAmount = new BigDecimal("1");

    @Test
    @Override
    public void TestFindById() {
        Transaction transaction = new Transaction("TXN006",debitAmount, "Dinner", "DEBIT",date,"ACC001","CAT1");
        Transaction result = transactionCrudOperation.findById(transaction);
        assertEquals(result,transaction , "should equals result and transaction have in \"TXN006\"");
    }

    @Test
    @Override
    public void TestSave() {
        Transaction transaction = new Transaction("TXN006",debitAmount, "Dinner", "DEBIT",date,"ACC001","CAT1");
        Transaction result = transactionCrudOperation.save(transaction);
        assertNotNull(transactionCrudOperation.findById(result),"Should data not null");
        assertEquals(transaction,result,"Should equals data in the base and the data saved");

    }

    @Test
    @Override
    public void TestSaveList() {
        List<Transaction> ListOfTransaction = new ArrayList<>();
        Transaction transaction4 = new Transaction("TXN004", creditAmount, "Groceries", "CREDIT",date,"ACC003","CAT10");
        Transaction transaction5 = new Transaction("TXN008", debitAmount, "Dinner", "DEBIT",date,"ACC003","CAT2");
        ListOfTransaction.add(transaction4);
        ListOfTransaction.add(transaction5);

        List<Transaction> result = transactionCrudOperation.saveAll(ListOfTransaction);
        assertNotNull(result,"should data saved not null");
    }

    @Test
    @Override
    public void TestUpdate() {
        Transaction setTransaction5 = new Transaction("TXN004", amount, "Bonus", "CREDIT",date,"ACC003","CAT3");
        Transaction result = transactionCrudOperation.update(setTransaction5);
        assertEquals(result,transactionCrudOperation.findById(setTransaction5),"Should equals data updated and data to update");
    }
@Test
    @Override
    public void TestFindAll() {
        List<Transaction> ListOfTransaction = transactionCrudOperation.findAll();
        assertEquals(data.getAccountOfData("transaction"),ListOfTransaction.size(),"Should equals size result and data size  in the base");

    }
}
