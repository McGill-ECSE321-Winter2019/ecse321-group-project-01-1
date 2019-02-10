package org.hibernate.dialect.unique;

import org.hibernate.boot.Metadata;
import org.hibernate.mapping.UniqueKey;

public class SQLiteUniqueDelegate implements UniqueDelegate{
	public String getColumnDefinitionUniquenessFragment(org.hibernate.mapping.Column column) { 
		return "";
	}

	public String getTableCreationUniqueConstraintsFragment(org.hibernate.mapping.Table table) {
		return "";
	}

	public String getAlterTableToAddUniqueKeyCommand(UniqueKey uniqueKey, Metadata metadata) {
		return "";
	}

	public String getAlterTableToDropUniqueKeyCommand(UniqueKey uniqueKey, Metadata metadata) { 
		return "";
	}

}
