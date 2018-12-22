package database;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.rdf4j.model.Statement;
import org.eclipse.rdf4j.query.TupleQueryResult;
import org.eclipse.rdf4j.repository.RepositoryConnection;
import org.eclipse.rdf4j.repository.RepositoryResult;

public class Utility {
	public  void println(Object x) {
        System.out.println(x);
    }
    protected void printRows(RepositoryResult<Statement> rows) throws Exception {
        while (rows.hasNext()) {
            println(rows.next());
        }
        rows.close();
    }

    protected void printRows(String headerMsg, int limit, RepositoryResult<Statement> rows) throws Exception {
        println(headerMsg);
        int count = 0;
        while (count < limit && rows.hasNext()) {
            println(rows.next());
            count++;
        }
        println("Number of results: " + count);
        rows.close();
    }

    protected void printRows(String headerMsg, TupleQueryResult rows) throws Exception {
        println(headerMsg);
        try {
            while (rows.hasNext()) {
            	println(rows.next());
            }
        } finally {
            rows.close();
        }
    }

    protected void close(RepositoryConnection conn) {
        try {
            conn.close();
        } catch (Exception e) {
            System.err.println("Error closing repository connection: " + e);
            e.printStackTrace();
        }
    }
    
    protected  List<RepositoryConnection> toClose = new ArrayList<RepositoryConnection>();
    
    /**
     * This is just a quick mechanism to make sure all connections get closed.
     */
    protected  void closeBeforeExit(RepositoryConnection conn) {
        toClose.add(conn);
    }
    
    protected  void closeAll() {
        while (!toClose.isEmpty()) {
            RepositoryConnection conn = toClose.get(0);
            close(conn);
            while (toClose.remove(conn)) {
                // ...
            }
        }
    }
    
}
