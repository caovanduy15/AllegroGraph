package database;


import org.eclipse.rdf4j.query.TupleQueryResult;

import com.franz.agraph.repository.AGQueryLanguage;
import com.franz.agraph.repository.AGRepositoryConnection;
import com.franz.agraph.repository.AGTupleQuery;


public class Query extends Utility{
	
	private String inputName;
	private String inputLabel;
	private String inputTimeGetLink;
	private String inputLink;
	private String queryString;
	private String type;
	private String position;
	
	public Query() {
		super();
	}
	
	public Query(AGRepositoryConnection conn) {
		super(conn);
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
	
	public void setposition(String position) {
		this.position = position;
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
	 * searchNameByType
	 */
	
	public void searchNameByLabel(AGRepositoryConnection conn) throws Exception { 
		String query = 
				"SELECT  ?name  WHERE {"
					+ "?s ontology:label \""+getInputLabel()+"\"."
					+ "?s ontology:name ?name"
				+ "}" ;		
		setQueryString(query);
		executeQuery(conn);
	}
	
	public void searchNameByTimeGetLink(AGRepositoryConnection conn) throws Exception { 
		String query = 
				"SELECT  ?name WHERE {"
					+ "?s ontology:timeGetLink \""+getInputTimeGetLink()+"\"."
					+ "?s ontology:name ?name"
				+ "}" ;		
		setQueryString(query);
		executeQuery(conn);
	}
	
	public void searchNameByLink(AGRepositoryConnection conn) throws Exception { 
		String query = 
				"SELECT  ?name WHERE {" 
					+ "?s ontology:link \""+getInputLink()+"\"."
					+ "?s ontology:name ?name"
				+ "}";		
		setQueryString(query);
		executeQuery(conn);
	}
	
	public void searchPersonNameByPosition(AGRepositoryConnection conn) throws Exception { 
		String query = 
				"SELECT  ?name WHERE {"
					+ "?s ontology:position \""+position+"\"."
					+ "?s ontology:name ?name"
				+ "}";	
		setQueryString(query);
		executeQuery(conn);
	}
	
	public void searchBaseInforByName(AGRepositoryConnection conn) throws Exception { 
		String query =	
				"SELECT ?label ?type ?description ?link ?timeGetLink WHERE {" 
					+ "?s ontology:name \""+getInputName()+"\"."
					+ "?s ontology:label ?label ."
					+ "?s ontology:description ?description ."
					+ "?s rdf:type ?type ."
					+ "?s ontology:link ?link ."
					+ "?s ontology:timeGetLink ?timeGetLink"
				+ "}";
			
		setQueryString(query);
		executeQuery(conn);
	}
	
	public void searchLabelByName(AGRepositoryConnection conn) throws Exception { 
		String query =	
				"SELECT  ?label WHERE {"
				+	"?s ontology:name \""+getInputName()+"\"."
				+	"?s ontology:label ?label ."
				+ "}";

		setQueryString(query);
		executeQuery(conn);
	}
	
	public void searchDescriptionByName(AGRepositoryConnection conn) throws Exception {	
		String query =	
				"SELECT  ?description WHERE {"
				+	"?s ontology:name \""+getInputName()+"\"."
				+	"?s ontology:description ?description ."
				+ "}";

		setQueryString(query);
		executeQuery(conn);
	}
	
	public void searchLinkByName(AGRepositoryConnection conn) throws Exception { 
		String query=	
				"SELECT  ?link  WHERE {"
				+	"?s ontology:name \""+getInputName()+"\"."
				+	"?s ontology:link ?link ."
				+ "}";

		setQueryString(query);
		executeQuery(conn);
	}
	
	public void searchTimeGetLinkByName(AGRepositoryConnection conn) throws Exception { 
			String query=	
					"SELECT  ?timeGetLink WHERE {"
						+ "?s ontology:name \""+getInputName()+"\"."
						+ "?s ontology:timeGetLink ?timeGetLink ."
					+ "}";
		setQueryString(query);
		executeQuery(conn);
	}
	
	public void searchTypeByName(AGRepositoryConnection conn) throws Exception { 
			String query =	
				"SELECT  ?type WHERE {"
				+	"?s ontology:name \""+getInputName()+"\"."
				+	"?s rdf:type ?type ."
				+ "}";
		setQueryString(query);
		executeQuery(conn);
	}
	
	public void searchNameByType(AGRepositoryConnection conn) throws Exception {
		String query = 
				"SELECT ?name WHERE {"
					+ "?s rdf:type ontology:"+type+"."
					+ "?s ontology:name ?name "
				+ "}";
		setQueryString(query);
		executeQuery(conn);
	}
	
	/**
	 * High Query 1: Cao Van Duy meet who in 2018
	 */
	public void HighQuery1(AGRepositoryConnection conn) throws Exception {
		println("High Query 1: Cao Van Duy meet who in 2018 ??");
		String query = 
				"SELECT ?name ?labelTime WHERE {"
					+ "?s relation:meet ?o."
					+ "?s ontology:label \"Cao Van Duy\"."
					+ "?o ontology:label ?name ."
					+ "?s relation:when ?timeID ."
					+ "?timeID ontology:label ?labelTime."
					+ "filter contains(str(?labelTime), str(2018))"
				+ "}";
		setQueryString(query);
		executeQuery(conn);
	}
	
	/**
	 * High Query 2: Cao Van Duy go where (e,c,l) in 2018
	 */
	public void HighQuery2(AGRepositoryConnection conn) throws Exception {
		println("High Query 2: Cao Van Duy go where (e,c,l) in 2018 ??");
		String query = 
				"SELECT ?where ?labelTime WHERE {"
					+ "?s relation:go ?o."
					+ "?s ontology:label \"Cao Van Duy\"."
					+ "?o ontology:label ?where ."
					+ "?s relation:when ?timeID ."
					+ "?timeID ontology:label ?labelTime."
					+ "filter contains(str(?labelTime), str(2018))"
				+ "}";
		setQueryString(query);
		executeQuery(conn);
	}
	
	/**
	 * High Query 3: What event does Cao Van Duy organize in 2018
	 */
	public void HighQuery3(AGRepositoryConnection conn) throws Exception {
		println("High Query 3: What event does Cao Van Duy organize in 2018 ??");
		String query = 
				"SELECT ?relation ?name ?labelTime WHERE {"
					+ "?s ontology:organize ?o."
					+ "?s ontology:label \"Cao Van Duy\"."
					+ "?o ontology:label ?name ."
					+ "?s relation:when ?timeID ."
					+ "?timeID ontology:label ?labelTime."
					+ "FILTER contains(str(?labelTime),str(2018))"
				+ "}";
		setQueryString(query);
		executeQuery(conn);
	}
	
	/**
	 * High Query 4: What Event does Cao Van Duy state in 2018
	 * @param CatalogID
	 * @param RepositoryID
	 * @throws Exception
	 */
	public void HighQuery4(AGRepositoryConnection conn) throws Exception {
		println("High Query 4: What Event does Cao Van Duy state in 2018");
		String query = 
				"SELECT ?name ?labelTime WHERE {"+
					"?s ontology:label \"Cao Van Duy\"."+
					"?s relation:state ?o."+
					"?o ontology:label ?name ."+
					"?s relation:when ?timeID ."+
					"?timeID ontology:label ?labelTime ."+
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
	public void HighQuery5(AGRepositoryConnection conn) throws Exception {
		println("High Query 5: Cao Van Duy travel where (c,l) in 2018 ??");
		String query = 
				"SELECT ?where ?labelTime WHERE {"
					+ "?IRI ontology:label \"Cao Van Duy\"."
					+ "?IRI relation:travel ?IRI1."
					+ "?IRI1 ontology:label ?where ."
					+ "?IRI relation:when ?timeID ."
					+ "?timeID ontology:label ?labelTime ."
					+ "filter contains(str(?labelTime),str(2018))"
				+ "}";
		setQueryString(query);
		executeQuery(conn);
	}
	
	/**
	 * High Query 6: Cao Van Duy stay where (c,l) in 2018
	 */
	public void HighQuery6(AGRepositoryConnection conn) throws Exception {
		println("High Query 6: Cao Van Duy stay where (c,l) in 2018 ??");
		String query = 
				"SELECT ?where ?labelTime WHERE {"
					+ "?s relation:stay ?o."
					+ "?s ontology:label \"Cao Van Duy\"."
					+ "?o ontology:label ?where ."
					+ "?s relation:when ?timeID ."
					+ "?timeID ontology:label ?labelTime ."
					+ "filter contains(str(?labelTime),str(2018))"
				+ "}";
		setQueryString(query);
		executeQuery(conn);
	}
	
	/**
	 * High Query 7: Vietnam join Event in 2018
	 */
	public void HighQuery7(AGRepositoryConnection conn) throws Exception {
		println("High Query 7: Vietnam join Event in 2018 ??");
		String query = 
				"SELECT ?name ?labelTime WHERE {"
					+ "?s relation:join ?o. "
					+ "?s ontology:label \"VietNam\"."
					+ "?o ontology:label ?name ."
					+ "?s relation:when ?timeID ."
					+ "?timeID ontology:label ?labelTime ."
					+ "filter contains(str(?labelTime),str(2018))"
				+ "}";
		setQueryString(query);
		executeQuery(conn);
	}
	
	/**
	 * High Query 8: Cao Van Duy join Country in 2018
	 */
	public void HighQuery8(AGRepositoryConnection conn) throws Exception {
		println("High Query 8: Cao Van Duy join Organization in 2018 ??");
		String query = 
				"SELECT ?name ?labelTime WHERE {"
					+ "?s relation:join ?o."
					+ "?s ontology:label \"Cao Van Duy\"."
					+ "?o ontology:label ?name ."
					+ "?s relation:when ?timeID ."
					+ "?timeID ontology:label ?labelTime ."
					+ "filter contains(str(?labelTime),str(2018))"
				+ "}";
		setQueryString(query);
		executeQuery(conn);
	}
	
	/**
	 * High Query 9: Vietnam negotiate Country in 2018
	 */
	public void HighQuery9(AGRepositoryConnection conn) throws Exception {
		println("High Query 9: What event does happen Vietnam in 2018 ??");
		String query = 
				"SELECT ?event ?labelTime WHERE {"
					+ "?s relation:happen ?o. "
					+ "?o ontology:label \"Vietnam\" ."
					+ "?s ontology:label ?event ."
					+ "?s relation:when ?timeID ."
					+ "?timeID ontology:label ?labelTime ."
					+ "filter contains(str(?labelTime),str(2018))"
				+ "}";
		setQueryString(query);
		executeQuery(conn);
	}
	
	/**
	 * High Query 10: DH Bach Khoa organize Event in 2018
	 */
	public void HighQuery10(AGRepositoryConnection conn) throws Exception {
		println("High Query 10: DH Bach Khoa organize Event in 2018 ??");
		String query = 
				"SELECT ?event ?labelTime WHERE {"
					+ "?s relation:organize ?o. "
					+ "?s ontology:label \"DH Bach Khoa\". "
					+ "?o rdf:type ontology:Event ."
					+ "?o ontology:label ?event ."
					+ "?s relation:when ?timeID ."
					+ "?timeID ontology:label ?labelTime ."
					+ "filter contains(str(?labelTime),str(2018))"
				+ "}";
		setQueryString(query);
		executeQuery(conn);
	}
	
	public static void main(String[] args) throws Exception {
		ConnectAG_Server connect = new ConnectAG_Server();
		AGRepositoryConnection conn = connect.connectRepository("/", "duy", false);
		Query q = new Query(conn);
//		q.countRelation(conn);
//		q.countEntity(conn);
//		q.setInputLabel("Cao Van Duy");
//		q.searchNameByLabel(conn);
		q.setInputName("Cao Van Duy575440");
//		q.setType('p');
		q.HighQuery1(conn);
//		q.searchTypeByName(conn);
//		q.setposition("builder");
//		q.searchPersonNameByPosition(conn);
		q.searchBaseInforByName(conn);
		q.searchDescriptionByName(conn);
		q.searchNameByType(conn);
		q.countEntity(conn);
		char arr[] = {'p','o','c','l','t','e'};
		for (char i:arr) {
			q.setType(i);
			q.countType(conn);
		};
	}
	
	public void executeQuery(AGRepositoryConnection conn) throws Exception {
		AGTupleQuery tupleQuery = conn.prepareTupleQuery(AGQueryLanguage.SPARQL, queryString);
		TupleQueryResult result = (TupleQueryResult)tupleQuery.evaluate(); 
		String header = "The Result: ";
		printRows(header, result);
		println(tupleQuery.count()+ " Result(s)");
	}
	
	public void countEntity(AGRepositoryConnection conn) {
		String query = 
				"SELECT  ?s ?y" + 
				"WHERE {?s ontology:name ?y} " ;		
		setQueryString(query);
		AGTupleQuery tupleQuery = conn.prepareTupleQuery(AGQueryLanguage.SPARQL, queryString);
		println(tupleQuery.count()+ " Entities");
	}
	
	public void countRelation(AGRepositoryConnection conn) {
//	      IRI meet  // p-p relation gặp 
//		  IRI organize // o-e relation tổ chức
//		  IRI state  // p-e relation phát biểu
//		  IRI go  // p-e,l,c relation  đi đến 
//		  IRI stay // p-l,c relation ở 
//		  IRI travel // p-l,c relation thăm quan
//		  IRI join // p-e,o relation  tham gia
//		  IRI happen
//		  IRI when
		String query = 
				"SELECT  * " + 
				"WHERE {{?s1 relation:when ?y1 .} UNION "+
				"{?s11 relation:meet ?y11 .} UNION "+
				"{?s12 relation:organize ?y12 .} UNION "+
				"{?s13 relation:state ?y13 .} UNION "+
				"{?s14 relation:go ?y14 .} UNION "+
				"{?s15 relation:stay ?y15 .} UNION "+
				"{?s16 relation:travel ?y16.} UNION "+
				"{?s17 relation:join ?y17 .} UNION "+
				"{?s114 relation:happen ?y114 .}} ";	
		setQueryString(query);
		AGTupleQuery tupleQuery = conn.prepareTupleQuery(AGQueryLanguage.SPARQL, queryString);
		println(tupleQuery.count()+ " Relations");
	}
	
	public void countType(AGRepositoryConnection conn) {
		String query = 
				"SELECT  ?s ?y WHERE {" 				
					+ "?s rdf:type ontology:"+getType()+" ."
					+ "?s ontology:name ?y"
				+ "}" ;		
		setQueryString(query);
		AGTupleQuery tupleQuery = conn.prepareTupleQuery(AGQueryLanguage.SPARQL, queryString);
		println("Count the "+ getType() +": " +tupleQuery.count() );
	}
}
