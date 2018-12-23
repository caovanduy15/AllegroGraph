package main;

import java.util.Scanner;

import com.franz.agraph.repository.AGRepositoryConnection;

import database.ConnectAG_Server;
import database.InsertTriples;
import database.Query;

public class Main {
//	SERVER_URL = "http://localhost:10035";
//	USERNAME = "test";
//  PASSWORD = "xyzzy";
	
    private static String RepositoryID = new String();
    private static int chooseAssertOption;
    private static int chooseQueryOption;
    private static String inputString;
    
    public static void main(String[] args)throws Exception {
    	
    	Scanner s = new Scanner(System.in);
    	    	
    	System.out.println("--------------START Program!!-------------");
    	
    	inputRepository(s);
    	
    	showAssertOption(s);
    
    	AssertToRepository(s);
    
    	
    	boolean cont = true;
    	do{	
    		showQueryOption(s);
    		cont = doQuery(s);
    	}while(cont);
    	System.out.println("--------------------END Program, Thanks-----------------------");
    	
    	
	}
	
    public static void inputRepository(Scanner s) throws Exception {
    	ConnectAG_Server connect = new ConnectAG_Server();
    	connect.showCatalog();
    	
		String notUse = s.nextLine();
		System.out.println("type the name of repository to choose: ");
		RepositoryID = s.next();
		System.out.println("OK, you chose Repository "+RepositoryID);
		connect.connectRepository("/", RepositoryID, true);
    }
    
    public static void showAssertOption(Scanner s) {
    	int choose = 0;
    	String notUse = null;
    	
    	do {
    		notUse = s.nextLine();
			System.out.println("Let's type number of option you want to do: ");
			System.out.println(""
					+ "1. Renew & insert 1000 entities and their relation \n"
					+ "2. Renew & insert 5000 entities and their relation \n"
					+ "3. Renew & insert 60000 entities and their relation \n"
					+ "4. Renew & insert until limit of repository - 5000000 statements\n"
					+ "5. Work with current Repository"
					+ "");
			choose = s.nextInt();
			System.out.println("You chose "+ choose);
		}while (choose<1 || choose >5);
    	chooseAssertOption = choose;
    }
    
    public static void AssertToRepository(Scanner s) throws Exception {
    	int endFor = 0;
		switch (chooseAssertOption) {
		case 1: endFor =1;break;
		case 2: endFor =5;break;
		case 3: endFor =60;break;
		case 4: endFor =700;break;
		default:
			break;
		}
		ConnectAG_Server connect = new ConnectAG_Server();
		if(chooseAssertOption >= 1 && chooseAssertOption <= 4) {
			connect.createRepository("/", RepositoryID, true);
			long start = System.currentTimeMillis();
			InsertTriples insert = new InsertTriples();
			for(int a =1; a<= endFor ; a++) {
				System.out.println("thao tac thu "+a);
				insert.assertTriples("/",RepositoryID);			
			}
			long end = System.currentTimeMillis();
			System.out.println("System assert Triples during " + (-start+end)/1000.0 + " second(s)");
		}
		connect.connectRepository("/", RepositoryID, true);
		s.nextLine();
    }
    
    public static void showQueryOption(Scanner s) {
    	String notUse = new String();
    	int choose = -1;
    	do {
    		notUse = s.nextLine();
    		System.out.println("Let's type number of option you want to do: ");
    		System.out.println(""
    				+ "0. exit Repository. \n"
    				+ "1. Normal Query 1: Search Name By Label \n"
    				+ "2. Normal Query 2: Search Name By Time Get Link \n"
    				+ "3. Normal Query 3: Search Name By Link \n"
    				+ "4. Normal Query 4: Search Person Name By Position \n"
    				+ "5. Normal Query 5: Search Base Infor By Name \n"
    				+ "6. Normal Query 6: Search Label By Name \n"
    				+ "7. Normal Query 7: Search Description By Name \n"
    				+ "8. Normal Query 8: Search Link By Name \n"
    				+ "9. Normal Query 9: Search Time Get Link By Name \n"
    				+ "10.Normal Query 10:Search Type By Name \n"
    				+ "11.High Query 1: Cao Van Duy meet who in 2018 ?? \n"
    				+ "12.High Query 2: Cao Van Duy go where (e,c,l) in 2018 ? \n"
    				+ "13.High Query 3: What event does Cao Van Duy organize in 2018 ? \n"
    				+ "14.High Query 4: What Event does Cao Van Duy state in 2018 \n"
    				+ "15.High Query 5: Cao Van Duy travel where (c,l) in 2018 \n"
    				+ "16.High Query 6: Cao Van Duy stay where (c,l) in 2018 \n"
    				+ "17.High Query 7: Vietnam join Event in 2018 \n"
    				+ "18.High Query 8: Cao Van Duy join Organization in 2018 ? \n"
    				+ "19.High Query 9: What event does happen Vietnam in 2018 ?  \n"
    				+ "20.High Query 10: DH Bach Khoa organize Event in 2018 \n"
    				+ "21. Count the Entity \n"
    				+ "22. Count the Relation"

    				+ "");
    		choose = s.nextInt();
    		System.out.println("You chose "+ choose);
    	}while (choose<0 ||choose >22);
		chooseQueryOption = choose;
    }
    
    public static boolean doQuery(Scanner s) throws Exception {
    	ConnectAG_Server connect = new ConnectAG_Server();
		AGRepositoryConnection conn = connect.connectRepository("/", RepositoryID, false);
    	boolean cont = true;
    	Query query = new Query(conn);
    	long start = 0;
    	long end = 0;
    	switch (chooseQueryOption) {
		case 0: 
			break;
		case 1: // Search Name By Label
			System.out.println("Get Label:");
			setInputString(s);
			query.setInputLabel(getInputString());
			start = System.currentTimeMillis();
			query.searchNameByLabel(conn);
			end = System.currentTimeMillis();
			System.out.println("Normal Query 1 executed during " + (-start+end)/1000.0 + " second(s)");
			break;
		case 2: // Search Name By Time
			System.out.println("Get TimeGetLink:");
			setInputString(s);
			query.setInputTimeGetLink(getInputString());
			start = System.currentTimeMillis();
			query.searchNameByTimeGetLink(conn);	
			end = System.currentTimeMillis();
			System.out.println("Normal Query 2 executed during " + (-start+end)/1000.0 + " second(s)");
			break;	
		case 3: // Search Name By Link
			System.out.println("Get Link:");
			setInputString(s);
			query.setInputLink(getInputString());
			start = System.currentTimeMillis();
			query.searchNameByLink(conn);
			end = System.currentTimeMillis();
			System.out.println("Normal Query 3 executed during " + (-start+end)/1000.0 + " second(s)");
			break;	
		case 4: // Search Person Name By Position
			System.out.println("Get Person Name:");
			setInputString(s);
			start = System.currentTimeMillis();
			query.setposition(getInputString());
			query.searchPersonNameByPosition(conn);
			end = System.currentTimeMillis();
			System.out.println("Normal Query 4 executed during " + (-start+end)/1000.0 + " second(s)");
			break;
		case 5: // Search BaseInfor By Name
			System.out.println("Get Name:");
			setInputString(s);
			query.setInputName(getInputString());
			start = System.currentTimeMillis();
			query.searchBaseInforByName(conn);
			end = System.currentTimeMillis();
			System.out.println("Normal Query 5 executed during " + (-start+end)/1000.0 + " second(s)");
			break;
		case 6: // Search Label By Name
			System.out.println("Get Name:");
			setInputString(s);
			query.setInputName(getInputString());
			start = System.currentTimeMillis();
			query.searchLabelByName(conn);
			end = System.currentTimeMillis();
			System.out.println("Normal Query 6 executed during " + (-start+end)/1000.0 + " second(s)");
			break;
		case 7: // Search Description By Name 
			System.out.println("Get Name:");
			setInputString(s);
			query.setInputName(getInputString());
			start = System.currentTimeMillis();
			query.searchDescriptionByName(conn);
			end = System.currentTimeMillis();
			System.out.println("Normal Query 7 executed during " + (-start+end)/1000.0 + " second(s)");
			break;
		case 8: // Search Link By Name
			System.out.println("Get Name:");
			setInputString(s);
			query.setInputName(getInputString());
			start = System.currentTimeMillis();
			query.searchLinkByName(conn);
			end = System.currentTimeMillis();
			System.out.println("Normal Query 8 executed during " + (-start+end)/1000.0 + " second(s)");
			break;
		case 9: // Search Time Get Link By Name
			System.out.println("Get Name:");
			setInputString(s);
			query.setInputName(getInputString());
			start = System.currentTimeMillis();
			query.searchTimeGetLinkByName(conn);
			end = System.currentTimeMillis();
			System.out.println("Normal Query 9 executed during " + (-start+end)/1000.0 + " second(s)");
			break;
		case 10: // Search Type By Name
			System.out.println("Get Name:");
			setInputString(s);
			query.setInputName(getInputString());
			start = System.currentTimeMillis();
			query.searchTypeByName(conn);
			end = System.currentTimeMillis();
			System.out.println("Normal Query 10 executed during " + (-start+end)/1000.0 + " second(s)");
			break;
		case 11:
			start = System.currentTimeMillis();
			query.HighQuery1(conn);
			end = System.currentTimeMillis();
			System.out.println("High Query 1 executed during " + (-start+end)/1000.0 + " second(s)");
			break;
		case 12:
			start = System.currentTimeMillis();
			query.HighQuery2(conn);
			end = System.currentTimeMillis();
			System.out.println("High Query 2 executed during " + (-start+end)/1000.0 + " second(s)");
			break;
		case 13:
			start = System.currentTimeMillis();
			query.HighQuery3(conn);
			end = System.currentTimeMillis();
			System.out.println("High Query 3 executed during " + (-start+end)/1000.0 + " second(s)");
			break;
		case 14:
			start = System.currentTimeMillis();
			query.HighQuery4(conn);
			end = System.currentTimeMillis();
			System.out.println("High Query 4 executed during " + (-start+end)/1000.0 + " second(s)");
			break;
		case 15:
			start = System.currentTimeMillis();
			query.HighQuery5(conn);
			end = System.currentTimeMillis();
			System.out.println("High Query 5 executed during " + (-start+end)/1000.0 + " second(s)");
			break;
		case 16:
			start = System.currentTimeMillis();
			query.HighQuery6(conn);
			end = System.currentTimeMillis();
			System.out.println("High Query 6 executed during " + (-start+end)/1000.0 + " second(s)");
			break;
		case 17:
			start = System.currentTimeMillis();
			query.HighQuery7(conn);
			end = System.currentTimeMillis();
			System.out.println("High Query 7 executed during " + (-start+end)/1000.0 + " second(s)");
			break;
		case 18:
			start = System.currentTimeMillis();
			query.HighQuery8(conn);
			end = System.currentTimeMillis();
			System.out.println("High Query 8 executed during " + (-start+end)/1000.0 + " second(s)");
			break;
		case 19:
			start = System.currentTimeMillis();
			query.HighQuery9(conn);
			end = System.currentTimeMillis();
			System.out.println("High Query 9 executed during " + (-start+end)/1000.0 + " second(s)");
			break;
		case 20:
			start = System.currentTimeMillis();
			query.HighQuery10(conn);
			end = System.currentTimeMillis();
			System.out.println("High Query 10 executed during " + (-start+end)/1000.0 + " second(s)");
			break;
		case 21: // Count the Entity
			start = System.currentTimeMillis();
			query.countEntity(conn);
			end = System.currentTimeMillis();
			System.out.println("Counting Entity executed during " + (-start+end)/1000.0 + " second(s)");
			break;
		case 22: // Count the Relation
			start = System.currentTimeMillis();
			query.countRelation(conn);
			end = System.currentTimeMillis();
			System.out.println("Counting Relation executed during " + (-start+end)/1000.0 + " second(s)");
			break;
		default:
			break;
		}
    	String notUse = s.nextLine();
    	System.out.println("Do You Want to Continue with this Repository ? \n YES(y) or NO(any another key)");
    	char key = ((s.nextLine()+" ").toLowerCase()).charAt(0);
    	if (key == 'y') {
    		cont = true;
    		System.out.println("You chose YES");
    	}
    	else {
    		cont = false;
    		System.out.println("You chose NO");
    	}
    	return cont;
    }

	public static String getInputString() {
		return inputString;
	}

	public static void setInputString(Scanner s) {
		String notUse = null;
		notUse = s.nextLine();
		System.out.println("Type your input: ");
		Main.inputString = s.nextLine();
		System.out.println("Your Input: \""+Main.inputString+"\"");
	}
    

}
