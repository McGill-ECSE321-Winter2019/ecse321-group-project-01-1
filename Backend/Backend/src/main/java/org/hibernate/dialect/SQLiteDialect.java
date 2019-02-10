package org.hibernate.dialect;

import java.sql.Types;

import org.hibernate.JDBCException;
import org.hibernate.ScrollMode;
import org.hibernate.dialect.function.AbstractAnsiTrimEmulationFunction;
import org.hibernate.dialect.function.NoArgSQLFunction;
import org.hibernate.dialect.function.SQLFunctionTemplate;
import org.hibernate.dialect.function.StandardSQLFunction;
import org.hibernate.dialect.function.VarArgsSQLFunction;
import org.hibernate.dialect.identity.IdentityColumnSupport;
import org.hibernate.dialect.identity.SQLiteIdentityColumnSupport;
import org.hibernate.dialect.pagination.LimitHandler;
import org.hibernate.dialect.pagination.LimitHelper;
import org.hibernate.dialect.unique.DefaultUniqueDelegate;
import org.hibernate.dialect.unique.SQLiteUniqueDelegate;
import org.hibernate.dialect.unique.UniqueDelegate;
import org.hibernate.engine.spi.RowSelection;
import org.hibernate.exception.DataException;
import org.hibernate.exception.JDBCConnectionException;
import org.hibernate.exception.LockAcquisitionException;
import org.hibernate.exception.spi.SQLExceptionConversionDelegate;
import org.hibernate.exception.spi.TemplatedViolatedConstraintNameExtracter;
import org.hibernate.exception.spi.ViolatedConstraintNameExtracter;
import org.hibernate.internal.util.JdbcExceptionHelper;
import org.hibernate.mapping.Column;
import org.hibernate.mapping.Constraint;
import org.hibernate.tool.schema.spi.Exporter;
import org.hibernate.type.StandardBasicTypes;


public class SQLiteDialect extends Dialect {
	
	//https://www.baeldung.com/spring-boot-sqlite
	
	public SQLiteDialect() {
		registerColumnType(Types.BIT, "integer");
		registerColumnType(Types.TINYINT, "tinyint");
		registerColumnType(Types.SMALLINT, "smallint");
		registerColumnType(Types.INTEGER, "integer");
		registerColumnType(Types.BIGINT, "bigint");
		registerColumnType(Types.FLOAT, "float");
		registerColumnType(Types.REAL, "real");
		registerColumnType(Types.DOUBLE, "double");
		registerColumnType(Types.NUMERIC, "numeric");
		registerColumnType(Types.DECIMAL, "decimal");
		registerColumnType(Types.CHAR, "char");
		registerColumnType(Types.VARCHAR, "varchar");
		registerColumnType(Types.LONGVARCHAR, "longvarchar");
		registerColumnType(Types.DATE, "date");
		registerColumnType(Types.TIME, "time");
		registerColumnType(Types.TIMESTAMP, "timestamp");
		registerColumnType(Types.BINARY, "blob");
		registerColumnType(Types.VARBINARY, "blob");
		registerColumnType(Types.LONGVARBINARY, "blob");
		// registerColumnType(Types.NULL, "null");
		registerColumnType(Types.BLOB, "blob");
		registerColumnType(Types.CLOB, "clob");
		registerColumnType(Types.BOOLEAN, "integer");

		registerFunction( "concat", new VarArgsSQLFunction( StandardBasicTypes.STRING, "", "||", "" ) );
		registerFunction( "mod", new SQLFunctionTemplate( StandardBasicTypes.INTEGER, "?1 % ?2" ) );
		registerFunction( "quote", new StandardSQLFunction( "quote", StandardBasicTypes.STRING ) );
		registerFunction( "random", new NoArgSQLFunction( "random", StandardBasicTypes.INTEGER ) );
		registerFunction( "round", new StandardSQLFunction( "round" ) );
		registerFunction( "substr", new StandardSQLFunction( "substr", StandardBasicTypes.STRING ) );
	}

	@Override
	public IdentityColumnSupport getIdentityColumnSupport() {
		return new SQLiteIdentityColumnSupport();
	}

	@Override
	public boolean hasAlterTable() {
		return false;
	}

	@Override
	public boolean dropConstraints() {
		return false;
	}

	@Override
	public String getDropForeignKeyString() {
		return "";
	}

	@Override
	public UniqueDelegate getUniqueDelegate() {
		return new SQLiteUniqueDelegate();
	}
	
	@Override
	public String getAddForeignKeyConstraintString(String cn, 
			String[] fk, String t, String[] pk, boolean rpk) {
		return "";
	}

	@Override
	public String getAddPrimaryKeyConstraintString(String constraintName) {
		return "";
	}

}