package com.rslaka.employeeservice.dao;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

/**
 * @author Rohtash Lakra
 * @created 12/2/22 4:37 PM
 */
public abstract class AbstractDao<T> extends JdbcDaoSupport {

    private T classType;

//    /**
//     * @param classType
//     */
//    public AbstractDao(T classType) {
//        this.classType = classType;
//    }

    /**
     * @return
     */
    public T getClassType() {
        return classType;
    }

    /**
     * @param classType
     */
    public void setClassType(T classType) {
        this.classType = classType;
    }

    /**
     * @param tableName
     * @return
     */
    protected String selectQuery(final String tableName) {
        return ("SELECT * FROM " + tableName);
    }

    /**
     * @param tableName
     * @param columns
     * @return
     */
    protected String insertQuery(final String tableName, final String[] columns) {
        final StringBuilder queryBuilder = new StringBuilder("INSERT INTO ");
        queryBuilder.append(tableName).append("(");
        queryBuilder.append(String.join(", ", columns));
        queryBuilder.append(") VALUES (");
        for (int i = 0; i < columns.length; i++) {
            queryBuilder.append("?");
            if (i < columns.length - 1) {
                queryBuilder.append(", ");
            }
        }
        queryBuilder.append(")");
        return queryBuilder.toString();
    }
}
