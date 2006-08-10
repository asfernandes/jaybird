package org.firebirdsql.jdbc.oo;

import java.sql.*;

import org.firebirdsql.gds.GDSException;
import org.firebirdsql.jca.FBManagedConnection;
import org.firebirdsql.jdbc.*;

public class OOConnection extends AbstractConnection {

    private OODatabaseMetaData metaData;

    public OOConnection(FBManagedConnection mc) {
        super(mc);
    }

    public synchronized DatabaseMetaData getMetaData() throws SQLException {
        try {
            if (metaData == null) metaData = new OODatabaseMetaData(this);

            return metaData;
        } catch (GDSException ex) {
            throw new FBSQLException(ex);
        }
    }

    public synchronized Statement createStatement(int resultSetType,
            int resultSetConcurrency, int resultSetHoldability)
            throws SQLException {
        try {
            Statement stmt = new OOStatement(getGDSHelper(), resultSetType,
                    resultSetConcurrency, resultSetHoldability, txCoordinator);

            activeStatements.add(stmt);
            return stmt;
        } catch (GDSException ex) {
            throw new FBSQLException(ex);
        }
    }

    public synchronized PreparedStatement prepareStatement(String sql,
            int resultSetType, int resultSetConcurrency,
            int resultSetHoldability, boolean metaData) throws SQLException {
        try {
            FBObjectListener.StatementListener coordinator = txCoordinator;
            if (metaData)
                coordinator = new InternalTransactionCoordinator.MetaDataTransactionCoordinator(
                        txCoordinator);

            FBObjectListener.BlobListener blobCoordinator;
            if (metaData)
                blobCoordinator = null;
            else
                blobCoordinator = txCoordinator;

            PreparedStatement stmt = new OOPreparedStatement(getGDSHelper(),
                    sql, resultSetType, resultSetConcurrency,
                    resultSetHoldability, coordinator, blobCoordinator,
                    metaData);

            activeStatements.add(stmt);
            return stmt;

        } catch (GDSException ex) {
            throw new FBSQLException(ex);
        }
    }

    public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys)
            throws SQLException {
        throw new FBDriverNotCapableException();
    }

    public PreparedStatement prepareStatement(String sql, int[] columnIndexes)
            throws SQLException {
        throw new FBDriverNotCapableException();
    }

    public PreparedStatement prepareStatement(String sql, String[] columnNames)
            throws SQLException {
        throw new FBDriverNotCapableException();
    }

    public void releaseSavepoint(Savepoint savepoint) throws SQLException {
        releaseSavepoint((FirebirdSavepoint) savepoint);
    }

    public void rollback(Savepoint savepoint) throws SQLException {
        rollback((FirebirdSavepoint) savepoint);
    }

    public Savepoint setSavepoint() throws SQLException {
        return (Savepoint) setFirebirdSavepoint();
    }

    public Savepoint setSavepoint(String name) throws SQLException {
        return (Savepoint) setFirebirdSavepoint(name);
    }

}
