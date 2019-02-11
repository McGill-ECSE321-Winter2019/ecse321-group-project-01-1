package org.hibernate.dialect.identity;

import org.hibernate.MappingException;

public class SQLiteIdentityColumnSupport extends IdentityColumnSupportImpl {
	 
    @Override
    public boolean supportsIdentityColumns() {
        return true;
    }
 
    @Override
    public String getIdentitySelectString(String table, String column, int type) 
      throws MappingException {
        return "select last_insert_rowid()";
    }
 
    @Override
    public String getIdentityColumnString(int type) throws MappingException {
        return "integer";
    }
}
