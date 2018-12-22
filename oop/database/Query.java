package database;


import org.eclipse.rdf4j.query.TupleQueryResult;


import com.franz.agraph.repository.*;


/**
 * 10 Base Query:
 * searchNameByLabel
 * searchNameByTimeGetLink
 * searchNameByLink
 * searchPersonNameByPosition
 * searchInforByName
 * searchLabelByName
 * searchDescriptionByName
 * searchLinkByName
 * searchTimeGetLinkByName
 * listTypeName
 */



public class Query extends Utility{
	
	private final String ontology = InsertTriples.ontology;
	private final String relation = InsertTriples.relation;

	
	private String inputName;
	private String inputLabel;
	private String inputTimeGetLink;
	private String inputLink;
	private String queryString;
	private String type;
	
	public Query() {
		super();
	}
	

	public String getInputName() {
		return inputName;
	}

	public void setInputName(String inputName) {
		this.inputName = inputName;
	}

	public String getInputLabel() {
		return inputLabel;
	}

	public void setInputLabel(String inputLabel) {
		this.inputLabel = inputLabel;
	}

	

	public String getInputTimeGetLink() {
		return inputTimeGetLink;
	}


	public void setInputTimeGetLink(String inputTimeGetLink) {
		this.inputTimeGetLink = inputTimeGetLink;
	}


	public String getInputLink() {
		return inputLink;
	}

	public void setInputLink(String inputLink) {
		this.inputLink = inputLink;
	}

	public String getQueryString() {
		return queryString;
	}


	public void setQueryString(String queryString) {
		this.queryString = queryString;
	}
	

	public String getType() {
			return type;
		}


	public void setType(char type) {
		switch (type) {
		case 'p':
			this.type = "Person";
			break;
		case 'o':
			this.type = "Organization";
			break;
		case 'l':
			this.type = "Location";
			break;
		case 'c':
			this.type = "Country";
			break;
		case 'e':
			this.type = "Event";
			break;
		case 't':
			this.type = "Time";
			break;
		default:
			break;
		}
	}

	/**
	 * 10 Base Query:
	 * searchNameByLabel
	 * searchNameByTimeGetLink
	 * searchNameByLink
	 * searchPersonNameByPosition
	 * searchInforByName
	 * searchLabelByName
	 * searchDescriptionByName
	 * searchLinkByName
	 * searchTimeGetLinkByName
	 * listTypeName
	 */

	public void searchNameByLabel(String CatalogID, String RepositoryID) throws Exception { 
		ConnectAG_Server connect = new ConnectAG_Server();
		AGRepositoryConnection conn = connect.connectRepository(CatalogID, RepositoryID, false);
		conn.setNamespace("ontology", ontology);
		String query = 
				"SELECT  ?name \n" + 
				"WHERE {?IRI ontology:label \""+getInputLabel()+"\"."+
						"?IRI ontology:name ?name} " ;		
		setQueryString(query);
		executeQuery(conn);
	}
	
	public void searchNameByTimeGetLink(String CatalogID, String RepositoryID) throws Exception { 
		ConnectAG_Server connect = new ConnectAG_Server();
		AGRepositoryConnection conn = connect.connectRepository(CatalogID, RepositoryID, false);
		conn.setNamespace("ontology", ontology);
		String query = 
				"SELECT  ?name \n" + 
				"WHERE {?IRI ontology:timeGetLink \""+getInputTimeGetLink()+"\"."+
						"?IRI ontology:name ?name} " ;		
		setQueryString(query);
		executeQuery(conn);
	}
	
	public void searchNameByLink(String CatalogID, String RepositoryID) throws Exception { 
		ConnectAG_Server connect = new ConnectAG_Server();
		AGRepositoryConnection conn = connect.connectRepository(CatalogID, RepositoryID, false);
		conn.setNamespace("ontology", ontology);
		String query = 
				"SELECT  ?name \n" + 
				"WHERE {?IRI ontology:link \""+getInputLink()+"\"."+
						"?IRI ontology:name ?name} " ;		
		setQueryString(query);
		executeQuery(conn);
	}
	
	public void searchPersonNameByPosition(String CatalogID, String RepositoryID,String Position) throws Exception { 
		ConnectAG_Server connect = new ConnectAG_Server();
		AGRepositoryConnection conn = connect.connectRepository(CatalogID, RepositoryID, false);
		conn.setNamespace("ontology", ontology);
		String query = 
				"SELECT  ?name \n" + 
				"WHERE {?IRI ontology:position \""+Position+"\"."+
						"?IRI ontology:name ?name} " ;		
		setQueryString(query);
		executeQuery(conn);
	}
	
	public void searchBaseInforByName(String CatalogID, String RepositoryID) throws Exception { 
		ConnectAG_Server connect = new ConnectAG_Server();
		AGRepositoryConnection conn = connect.connectRepository(CatalogID, RepositoryID, false);
		conn.setNamespace("ontology", ontology);
		String query = new String();
			query=	"SELECT ?label ?type ?description ?link ?timeGetLink ?position \n" + 
					"WHERE {?IRI ontology:name \""+getInputName()+"\".\n"+
							"?IRI ontology:label ?label .\n"+
							"?IRI ontology:description ?description .\n"+
							"?IRI rdf:type ?type ."+
							"?IRI ontology:link ?link .\n"+
							"?IRI ontology:timeGetLink ?timeGetLink \n"+
							 " } " ;
			
		setQueryString(query);
		executeQuery(conn);
	}
	
	public void searchLabelByName(String CatalogID, String RepositoryID) throws Exception { 
		ConnectAG_Server connect = new ConnectAG_Server();
		AGRepositoryConnection conn = connect.connectRepository(CatalogID, RepositoryID, false);
		conn.setNamespace("ontology", ontology);
		String query = new String();
		
			query=	"SELECT  ?label \n" + 
					"WHERE {?IRI ontology:name \""+getInputName()+"\".\n"+
							"?IRI ontology:label ?label .\n"+
							 "} " ;

		setQueryString(query);
		executeQuery(conn);
	}
	
	public void searchDescriptionByName(String CatalogID, String RepositoryID) throws Exception { 
		ConnectAG_Server connect = new ConnectAG_Server();
		AGRepositoryConnection conn = connect.connectRepository(CatalogID, RepositoryID, false);
		conn.setNamespace("ontology", ontology);
		String query = new String();
		
			query=	"SELECT  ?description  \n" + 
					"WHERE {?IRI ontology:name \""+getInputName()+"\".\n"+
							"?IRI ontology:description ?description .\n"+
							 "} " ;

		setQueryString(query);
		executeQuery(conn);
	}
	
	public void searchLinkByName(String CatalogID, String RepositoryID) throws Exception { 
		ConnectAG_Server connect = new ConnectAG_Server();
		AGRepositoryConnection conn = connect.connectRepository(CatalogID, RepositoryID, false);
		conn.setNamespace("ontology", ontology);
		String query = new String();
		
			query=	"SELECT  ?link  \n" + 
					"WHERE {?IRI ontology:name \""+getInputName()+"\".\n"+
							"?IRI ontology:link ?link .\n"+
							 "} " ;

		setQueryString(query);
		executeQuery(conn);
	}
	
	public void searchTimeGetLinkByName(String CatalogID, String RepositoryID) throws Exception { 
		ConnectAG_Server connect = new ConnectAG_Server();
		AGRepositoryConnection conn = connect.connectRepository(CatalogID, RepositoryID, false);
		conn.setNamespace("ontology", ontology);
		String query = new String();
		
			query=	"SELECT  ?timeGetLink  \n" + 
					"WHERE {?IRI ontology:name \""+getInputName()+"\".\n"+
							"?IRI ontology:timeGetLink ?timeGetLink .\n"+
							 "} " ;

		setQueryString(query);
		executeQuery(conn);
	}
	
	public void searchTypeByName(String CatalogID, String RepositoryID) throws Exception { 
		ConnectAG_Server connect = new ConnectAG_Server();
		AGRepositoryConnection conn = connect.connectRepository(CatalogID, RepositoryID, false);
		conn.setNamespace("ontology", ontology);
		String query = new String();
		
			query=	"SELECT  ?type  \n" + 
					"WHERE {?IRI ontology:name \""+getInputName()+"\".\n"+
							"?IRI rdf:type ?type .\n"+
							 "} " ;

		setQueryString(query);
		executeQuery(conn);
	}
	
	public void listTypeName(String CatalogID, String RepositoryID) throws Exception {
		ConnectAG_Server connect = new ConnectAG_Server();
		AGRepositoryConnection conn = connect.connectRepository(CatalogID, RepositoryID, false);
		conn.setNamespace("ontology", ontology);
		String query = 
				"SELECT ?name \n"+
				"WHERE { ?IRI rdf:type ontology:"+type+". \n"+
				"?IRI ontology:name ?name }";
		setQueryString(query);
		executeQuery(conn);
		
	}
	
	/**
	 * High Query 1: Cao Van Duy meet who in 2018
	 * @param CatalogID
	 * @param RepositoryID
	 * @throws Exception
	 */
	public void HighQuery1(String CatalogID, String RepositoryID) throws Exception {
		println("High Query 1: Cao Van Duy meet who in 2018 ??");
		ConnectAG_Server connect = new ConnectAG_Server();
		AGRepositoryConnection conn = connect.connectRepository(CatalogID, RepositoryID, false);
		conn.setNamespace("ontology", ontology);
		conn.setNamespace("relation", relation);
		String query = 
				"SELECT ?name ?labelTime \n"+
				"WHERE { ?IRI ontology:label \"Cao Van Duy\". \n"+
				"?IRI relation:meet ?IRI1. \n"+
				"?IRI1 ontology:label ?name .\n"+
				"?IRI relation:when ?timeID .\n"+
				"?timeID ontology:label ?labelTime \n"+
				"filter contains(str(?labelTime),str(2018))"+
				"}";
		setQueryString(query);
		executeQuery(conn);
	}
	
	/**
	 * High Query 2: Cao Van Duy go where (e,c,l) in 2018
	 * @param CatalogID
	 * @param RepositoryID
	 * @throws Exception
	 */
	public void HighQuery2(String CatalogID, String RepositoryID) throws Exception {
		println("High Query 2: Cao Van Duy go where (e,c,l) in 2018 ??");
		ConnectAG_Server connect = new ConnectAG_Server();
		AGRepositoryConnection conn = connect.connectRepository(CatalogID, RepositoryID, false);
		conn.setNamespace("ontology", ontology);
		conn.setNamespace("relation", relation);
		String query = 
				"SELECT ?where ?labelTime \n"+
				"WHERE { ?IRI ontology:label \"Cao Van Duy\". \n"+
				"?IRI relation:go ?IRI1. \n"+
				"?IRI1 ontology:label ?where .\n"+
				"?IRI relation:when ?timeID .\n"+
				"?timeID ontology:label ?labelTime \n"+
				"filter contains(str(?labelTime),str(2018))"+
				"}";
		setQueryString(query);
		executeQuery(conn);
	}
	
	/**
	 * High Query 3: What event does Cao Van Duy organize in 2018
	 * @param CatalogID
	 * @param RepositoryID
	 * @throws Exception
	 */
	public void HighQuery3(String CatalogID, String RepositoryID) throws Exception {
		println("High Query 3: What event does Cao Van Duy organize in 2018 ??");
		ConnectAG_Server connect = new ConnectAG_Server();
		AGRepositoryConnection conn = connect.connectRepository(CatalogID, RepositoryID, false);
		conn.setNamespace("ontology", ontology);
		conn.setNamespace("relation", relation);
		String query = 
				"SELECT ?relation ?name ?labelTime \n"+
				"WHERE { "+
				"?IRI ontology:label \"Cao Van Duy\". \n"+
				"?IRI ontology:organize ?eIRI. \n"+
				"?eIRI ontology:label ?name . \n"+
				"?IRI relation:when ?timeID .\n"+
				"?timeID ontology:label ?labelTime \n"+
				"FILTER contains(str(?labelTime),str(2018))"+
				"}";
		setQueryString(query);
		executeQuery(conn);
	}
	
	/**
	 * High Query 4: What Event does Cao Van Duy state in 2018
	 * @param CatalogID
	 * @param RepositoryID
	 * @throws Exception
	 */
	public void HighQuery4(String CatalogID, String RepositoryID) throws Exception {
		println("High Query 4: What Event does Cao Van Duy state in 2018");
		ConnectAG_Server connect = new ConnectAG_Server();
		AGRepositoryConnection conn = connect.connectRepository(CatalogID, RepositoryID, false);
		conn.setNamespace("ontology", ontology);
		conn.setNamespace("relation", relation);
		String query = 
				"SELECT ?name ?labelTime \n"+
				"WHERE { ?IRI ontology:label \"Cao Van Duy\". \n"+
				"?IRI relation:state ?eIRI. \n"+
				"?eIRI ontology:label ?name .\n"+
				"?IRI relation:when ?timeID .\n"+
				"?timeID ontology:label ?labelTime \n"+
				"filter contains(str(?labelTime),str(2018))"+
				"}";
		setQueryString(query);
		executeQuery(conn);
	}
	
	/**
	 * High Query 5: Cao Van Duy travel where (c,l) in 2018
	 * @param CatalogID
	 * @param RepositoryID
	 * @throws Exception
	 */
	public void HighQuery5(String CatalogID, String RepositoryID) throws Exception {
		println("High Query 5: Cao Van Duy travel where (c,l) in 2018 ??");
		ConnectAG_Server connect = new ConnectAG_Server();
		AGRepositoryConnection conn = connect.connectRepository(CatalogID, RepositoryID, false);
		conn.setNamespace("ontology", ontology);
		conn.setNamespace("relation", relation);
		String query = 
				"SELECT ?where ?labelTime \n"+
				"WHERE { ?IRI ontology:label \"Cao Van Duy\". \n"+
				"?IRI relation:travel ?IRI1. \n"+
				"?IRI1 ontology:label ?where .\n"+
				"?IRI relation:when ?timeID .\n"+
				"?timeID ontology:label ?labelTime \n"+
				"filter contains(str(?labelTime),str(2018))"+
				"}";
		setQueryString(query);
		executeQuery(conn);
	}
	
	/**
	 * High Query 6: Cao Van Duy stay where (c,l) in 2018
	 * @param CatalogID
	 * @param RepositoryID
	 * @throws Exception
	 */
	public void HighQuery6(String CatalogID, String RepositoryID) throws Exception {
		println("High Query 6: Cao Van Duy stay where (c,l) in 2018 ??");
		ConnectAG_Server connect = new ConnectAG_Server();
		AGRepositoryConnection conn = connect.connectRepository(CatalogID, RepositoryID, false);
		conn.setNamespace("ontology", ontology);
		conn.setNamespace("relation", relation);
		String query = 
				"SELECT ?where ?labelTime \n"+
				"WHERE { ?IRI ontology:label \"Cao Van Duy\". \n"+
				"?IRI relation:stay ?IRI1. \n"+
				"?IRI1 ontology:label ?where .\n"+
				"?IRI relation:when ?timeID .\n"+
				"?timeID ontology:label ?labelTime \n"+
				"filter contains(str(?labelTime),str(2018))"+
				"}";
		setQueryString(query);
		executeQuery(conn);
	}
	
	/**
	 * High Query 7: Vietnam join Event in 2018
	 * @param CatalogID
	 * @param RepositoryID
	 * @throws Exception
	 */
	public void HighQuery7(String CatalogID, String RepositoryID) throws Exception {
		println("High Query 7: Vietnam join Event in 2018 ??");
		ConnectAG_Server connect = new ConnectAG_Server();
		AGRepositoryConnection conn = connect.connectRepository(CatalogID, RepositoryID, false);
		conn.setNamespace("ontology", ontology);
		conn.setNamespace("relation", relation);
		String query = 
				"SELECT ?name ?labelTime \n"+
						"WHERE { ?IRI ontology:label \"VietNam\". \n"+
						"?IRI relation:join ?eIRI. \n"+
						"?eIRI ontology:label ?name .\n"+
						"?IRI relation:when ?timeID .\n"+
						"?timeID ontology:label ?labelTime \n"+
						"filter contains(str(?labelTime),str(2018))"+
						"}";
		setQueryString(query);
		executeQuery(conn);
	}
	
	/**
	 * High Query 8: Cao Van Duy join Country in 2018
	 * @param CatalogID
	 * @param RepositoryID
	 * @throws Exception
	 */
	public void HighQuery8(String CatalogID, String RepositoryID) throws Exception {
		println("High Query 8: Cao Van Duy join Organization in 2018 ??");
		ConnectAG_Server connect = new ConnectAG_Server();
		AGRepositoryConnection conn = connect.connectRepository(CatalogID, RepositoryID, false);
		conn.setNamespace("ontology", ontology);
		conn.setNamespace("relation", relation);
		String query = 
				"SELECT ?name ?labelTime \n"+
						"WHERE { ?IRI ontology:label \"Cao Van Duy\". \n"+
						"?IRI relation:join ?oIRI. \n"+
						"?oIRI ontology:label ?name .\n"+
						"?IRI relation:when ?timeID .\n"+
						"?timeID ontology:label ?labelTime \n"+
						"filter contains(str(?labelTime),str(2018))"+
						"}";
		setQueryString(query);
		executeQuery(conn);
	}
	
	/**
	 * High Query 9: Vietnam negotiate Country in 2018
	 * @param CatalogID
	 * @param RepositoryID
	 * @throws Exception
	 */
	public void HighQuery9(String CatalogID, String RepositoryID) throws Exception {
		println("High Query 9: What event does happen Vietnam in 2018 ??");
		ConnectAG_Server connect = new ConnectAG_Server();
		AGRepositoryConnection conn = connect.connectRepository(CatalogID, RepositoryID, false);
		conn.setNamespace("ontology", ontology);
		conn.setNamespace("relation", relation);
		String query = 
				"SELECT ?event ?labelTime \n"+
				"WHERE { "+
				"?IRI relation:happen ?IRIc. \n"+
				"?IRIc ontology:label \"Vietnam\" ."+
				"?IRI ontology:label ?event .\n"+
				"?IRI relation:when ?timeID .\n"+
				"?timeID ontology:label ?labelTime \n"+
				"filter contains(str(?labelTime),str(2018))"+
				"}";
		setQueryString(query);
		executeQuery(conn);
	}
	
	/**
	 * High Query 10: Microsoft Corporation organize Event in 2018
	 * @param CatalogID
	 * @param RepositoryID
	 * @throws Exception
	 */
	public void HighQuery10(String CatalogID, String RepositoryID) throws Exception {
		println("High Query 10: Microsoft Corporation organize Event in 2018 ??");
		ConnectAG_Server connect = new ConnectAG_Server();
		AGRepositoryConnection conn = connect.connectRepository(CatalogID, RepositoryID, false);
		conn.setNamespace("ontology", ontology);
		conn.setNamespace("relation", relation);
		String query = 
				"SELECT ?event ?labelTime \n"+
				"WHERE { ?IRI ontology:label \"Microsoft Corporation\". \n"+
				"?IRI relation:organize ?IRI1. \n"+
				"?IRI1 rdf:type ontology:Event ."+
				"?IRI1 ontology:label ?event .\n"+
				"?IRI relation:when ?timeID .\n"+
				"?timeID ontology:label ?labelTime \n"+
				"filter contains(str(?labelTime),str(2018))"+
				"}";
		setQueryString(query);
		executeQuery(conn);
	}
	
	public static void main(String[] args) throws Exception {
		Query q = new Query();
//		q.countRelation("/", "OOP");
//		q.countEntity("/", "OOP");
		q.setInputLabel("Vietnam");
//		q.searchNameByLabel("/", "OOP");
		q.setInputName("Vietnam595073");
//		q.setType('p');
//		q.HighQuery1("/", "OOP");
//		q.searchTypeByName("/", "OOP");
//		q.searchPersonNameByPosition("/", "OOP", "builder");
		q.searchBaseInforByName("/", "OOP");
//		q.searchDescriptionByName("/", "OOP");
//		q.listTypeName("/", "OOP");
//		q.countEntity("/", "OOP");
//		char arr[] = {'p','o','c','l','t','e'};
//		for (char i:arr) {
//			q.setType(i);
//			q.countType("/", "OOP");
//		};
	}
	
	public void executeQuery(AGRepositoryConnection conn) throws Exception {
		AGTupleQuery tupleQuery = conn.prepareTupleQuery(AGQueryLanguage.SPARQL, queryString);
		TupleQueryResult result = (TupleQueryResult)tupleQuery.evaluate(); 
		String header = "The Result: ";
		printRows(header, result);
		println(tupleQuery.count()+ " Result(s)");
	}
	
	public void countEntity(String CatalogID, String RepositoryID) {
		ConnectAG_Server connect = new ConnectAG_Server();
		AGRepositoryConnection conn = connect.connectRepository(CatalogID, RepositoryID, false);
		conn.setNamespace("ontology", ontology);
		String query = 
				"SELECT  ?s ?y \n" + 
				"WHERE {?s ontology:name ?y} " ;		
		setQueryString(query);
		AGTupleQuery tupleQuery = conn.prepareTupleQuery(AGQueryLanguage.SPARQL, queryString);
		println(tupleQuery.count()+ " Entities");
	}
	
	public void countRelation(String CatalogID, String RepositoryID) {
		ConnectAG_Server connect = new ConnectAG_Server();
		AGRepositoryConnection conn = connect.connectRepository(CatalogID, RepositoryID, false);
		conn.setNamespace("relation", relation);
//	      IRI meet  // p-p relation gặp 
//		  IRI organize // o-e relation tổ chức
//		  IRI state  // p-e relation phát biểu
//		  IRI go  // p-e,l,c relation  đi đến 
//		  IRI stay // p-l,c relation ở 
//		  IRI travel // p-l,c relation thăm quan
//		  IRI join // p-e,o relation  tham gia
//		  IRI employee  // p-o relation nhân viên
//		  IRI advocate // p-c relation ủng hộ, đồng tình
//		  IRI counter // p-c relation phản đối
//		  IRI co_oparate // c-c, o-o relation hợp tác
//		  IRI compete // c-c, o-o relation cạnh tranh
//		  IRI negotiate // c-c, o-o relation đàm phán
//		      happen
		String query = 
				"SELECT  * \n" + 
				"WHERE {{?s1 relation:when ?y1 .} UNION "+
				"{?s11 relation:meet ?y11 .} UNION "+
				"{?s12 relation:organize ?y12 .} UNION "+
				"{?s13 relation:state ?y13 .} UNION "+
				"{?s14 relation:go ?y14 .} UNION "+
				"{?s15 relation:stay ?y15 .} UNION "+
				"{?s16 relation:travel ?y16.} UNION "+
				"{?s17 relation:join ?y17 .} UNION "+
				"{?s18 relation:employee ?y18 .} UNION "+
				"{?s19 relation:advocate ?y19 .} UNION "+
				"{?s110 relation:counter ?y110 .} UNION "+
				"{?s111 relation:co_oparate ?y111 .} UNION "+
				"{?s112 relation:compete ?y112 .} UNION "+
				"{?s113 relation:negotiate ?y113 .} UNION "+
				"{?s114 relation:happen ?y114 .}} ";	
		setQueryString(query);
		AGTupleQuery tupleQuery = conn.prepareTupleQuery(AGQueryLanguage.SPARQL, queryString);
		println(tupleQuery.count()+ " Relations");
	}
	
	public void countType(String CatalogID, String RepositoryID) {
		ConnectAG_Server connect = new ConnectAG_Server();
		AGRepositoryConnection conn = connect.connectRepository(CatalogID, RepositoryID, false);
		conn.setNamespace("ontology", ontology);

		String query = 
				"SELECT  ?s ?y \n" + 				
				"WHERE {?s rdf:type ontology:"+getType()+" .\n"
				+ "?s ontology:name ?y} " ;		
		setQueryString(query);
		AGTupleQuery tupleQuery = conn.prepareTupleQuery(AGQueryLanguage.SPARQL, queryString);
		println("Count the "+ getType() +" :" +tupleQuery.count() );
	}
}
