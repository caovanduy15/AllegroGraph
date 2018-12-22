package database;

import java.util.List;

import com.franz.agraph.repository.AGCatalog;
import com.franz.agraph.repository.AGRepository;
import com.franz.agraph.repository.AGRepositoryConnection;
import com.franz.agraph.repository.AGServer;

public class ConnectAG_Server extends Utility{
	private static final String SERVER_URL = "http://localhost:10035";
    static final String CATALOG_ID = "/";
    static final String REPOSITORY_ID = "OOP";
    private static final String USERNAME = "test";
    private static final String PASSWORD = "xyzzy";
	
    /**
     * Creating a Repository test
     */
    public AGRepositoryConnection createRepository(String CatalogID,String RepositoryID, boolean close)  {
        println("\nStarting Create Repository .");
        AGServer server = new AGServer(SERVER_URL, USERNAME, PASSWORD);
        try {
            println("Server version: " + server.getVersion());
            println("Server build date: " + server.getBuildDate());
            println("Server revision: " + server.getRevision());
            println("Available catalogs: " + server.listCatalogs());
        } catch (Exception e) {
            try {
				throw new Exception("Got error when attempting to connect to server at "
				                    + SERVER_URL + ": " + e);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
        }
        
        AGCatalog catalog = server.getCatalog(CatalogID); // open catalog

        if (catalog == null) {
            try {
				throw new Exception("Catalog " + CatalogID + " does not exist. Either "
				                + "define this catalog in your agraph.cfg or modify the CATALOG_ID "
				                + "in this tutorial to name an existing catalog.");
			} catch (Exception e) {
				e.printStackTrace();
			}
        }

        println("Available repositories in catalog " + 
                (catalog.getCatalogName()) + ": " + 
                catalog.listRepositories());
        closeAll();
        // clear all statements 
        catalog.deleteRepository(RepositoryID); 
        
        AGRepository myRepository = catalog.createRepository(RepositoryID);
        println("Got a repository.");
        myRepository.initialize();
        println("Initialized repository.");
        println("Repository is writable? " + myRepository.isWritable());
        AGRepositoryConnection conn = myRepository.getConnection();
        closeBeforeExit(conn);
        println("Got a connection.");
        println("Repository " + (myRepository.getRepositoryID()) +
                " is up! It contains " + (conn.size()) +
                " statements."              
                );
        List<String> indices = conn.listValidIndices();
        println("All valid triple indices: " + indices);
        indices = conn.listIndices();
        println("Current triple indices: " + indices);
        println("Removing graph indices...");
        conn.dropIndex("gospi");
        conn.dropIndex("gposi");
        conn.dropIndex("gspoi");
        indices = conn.listIndices();
        println("Current triple indices: " + indices);
        println("Adding one graph index back in...");
        conn.addIndex("gspoi");
        indices = conn.listIndices();
        println("Current triple indices: " + indices);
        if (close) {
            // tidy up
            conn.close();
            myRepository.shutDown();
            server.close();
            return null;
        }
        return conn;
    }
	
    public AGRepositoryConnection createRepository(boolean close)  {
    	return this.createRepository(CATALOG_ID, REPOSITORY_ID, close);
    }
    
    /**
	 * connenct to existed Repository 
	 * @return AGRepositoryConnection if false 
	 * @throws Exception
	 */                  
    public AGRepositoryConnection connectRepository(String CatalogID, String RepositoryID, boolean close){
    	AGServer server = new AGServer(SERVER_URL, USERNAME, PASSWORD);
    	AGCatalog catalog = server.getCatalog(CatalogID);
    	AGRepository myRepository = catalog.openRepository(RepositoryID);
    	AGRepositoryConnection conn = myRepository.getConnection();
    	println("Connected to Repository "+RepositoryID);
    	println("Repository " +RepositoryID+
                " is up! It contains " + (conn.size()) +
                " statements."              
                );
    	if (close) {
            // tidy up
            conn.close();
            myRepository.shutDown();
            server.close();
            return null;
        }
    	return conn;
    }
    
    public AGRepositoryConnection connectRepository(boolean close) throws Exception {
    	return connectRepository(CATALOG_ID,REPOSITORY_ID,close);
    }
    
    
    
    public void deleteRepository(String CatalogID, String RepositoryID) throws Exception {
    	println("\nStarting Delete Repository .");
        AGServer server = new AGServer(SERVER_URL, USERNAME, PASSWORD);
        AGCatalog catalog = server.getCatalog(CatalogID); // open catalog
        println("Available repositories in catalog " + 
                (catalog.getCatalogName()) + ": " + 
                catalog.listRepositories());
        closeAll();
        // clear Repository
        catalog.deleteRepository(RepositoryID); 
        
        println("Delete repository successfully!");
        println("Available repositories in catalog " + 
                (catalog.getCatalogName()) + ": " + 
                catalog.listRepositories());
        server.close();
    }
    
    public void showCatalog() throws Exception {
    	println("Starting Show Repository in Catalog "+ "/" +" ");
    	AGServer server = new AGServer(SERVER_URL, USERNAME, PASSWORD);
        AGCatalog catalog = server.getCatalog("/"); // open catalog
        println("Available repositories in catalog " + 
                (catalog.getCatalogName()) + ": " + 
                catalog.listRepositories());
    	server.close();
    }
	
	
}
