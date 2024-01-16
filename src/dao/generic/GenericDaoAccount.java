package dao.generic;

import model.Account;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class GenericDaoAccount extends GenericCrudOperation<Account> {
    @Override
    String getTableName() {
        return "account";
    }

    @Override
    Account getObjectFromResultSet(ResultSet resultSet) throws SQLException {
        String accountId = resultSet.getString("accountId");
        String name = resultSet.getString("name");
        BigDecimal balance = resultSet.getBigDecimal("balance");
        LocalDateTime lastUpdate = resultSet.getTimestamp("lastUpdate").toLocalDateTime();
        String currencyId = resultSet.getString("currencyId");
        String type = resultSet.getString("type");
        return new Account(accountId, name, balance, lastUpdate,null, currencyId, type);

    }

}
