package database;

import java.util.concurrent.atomic.AtomicInteger;

import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.model.Literal;
import org.eclipse.rdf4j.model.Model;
import org.eclipse.rdf4j.model.impl.TreeModel;
import org.eclipse.rdf4j.model.vocabulary.RDF;

import com.franz.agraph.repository.AGRepositoryConnection;
import com.franz.agraph.repository.AGValueFactory;

import entity.*;

public class InsertTriples extends Utility {
	
	private static AtomicInteger count = new AtomicInteger(0);

	public void assertTriples(String CatalogID, String RepositoryID) throws Exception {
		ConnectAG_Server server = new ConnectAG_Server();
		// Asserts some statements and counts them.
		AGRepositoryConnection conn = server.connectRepository(CatalogID, RepositoryID, false);

		Model model = new TreeModel();
		model = createRelationTriples(conn);
		println("\nStarting Asserting");
		conn.add(model);
		conn.close();
		println("----------------------DONE!!------------------------");

	}

	public void assertTriples() throws Exception {
		assertTriples(ConnectAG_Server.CATALOG_ID, ConnectAG_Server.REPOSITORY_ID);
	}

	
	public Model createRelationTriples(AGRepositoryConnection conn) throws Exception{
		Model model = new TreeModel();
		  AGValueFactory vf = conn.getRepository().getValueFactory();
		  Person p = new Person();
	      Country c = new Country();
	      Event e = new Event();
	      Location l = new Location();
	      Organization o = new Organization();
	      Time t = new Time();
	      
	      Person p1 = new Person();
	      Person p2 = new Person();
 	      Country c1 = new Country();
	      Organization o1 = new Organization();
	      
	      IRI context = vf.createIRI("http://example.org/context");
	      
	      //	add vocabularies
	      IRI person = vf.createIRI(ontology, "Person");
	      IRI country = vf.createIRI(ontology, "Country");
	      IRI event = vf.createIRI(ontology, "Event");
	      IRI location = vf.createIRI(ontology, "Location");
	      IRI organization = vf.createIRI(ontology, "Organization");
	      IRI time = vf.createIRI(ontology, "Time");
	      
	      IRI meet = vf.createIRI(relation, "meet"); // p-p relation gặp
	      IRI organize = vf.createIRI(relation, "organize"); // o-e relation tổ chức
	      IRI state = vf.createIRI(relation, "state"); // p-e relation phát biểu
	      IRI go = vf.createIRI(relation, "go"); // p-e,l,c relation  đi đến 
	      IRI stay = vf.createIRI(relation, "stay"); // p-l,c relation ở 
	      IRI travel = vf.createIRI(relation, "travel"); // p-l,c,o; c-l relation thăm quan
	      IRI join = vf.createIRI(relation, "join"); // p-e,o; c-e relation  tham gia
	      IRI visit = vf.createIRI(relation, "visit"); // p-p thăm
	      IRI happen = vf.createIRI(relation, "happen"); // e-o,l,c xảy ra
	      IRI when = vf.createIRI(relation, "when");
	      
	      IRI name = vf.createIRI(ontology,"name");
	      IRI label = vf.createIRI(ontology, "label");
	      IRI description = vf.createIRI(ontology,"description");
	      IRI link = vf.createIRI(ontology, "link");
	      IRI timeGetLink = vf.createIRI(ontology, "timeGetLink");
	      IRI position = vf.createIRI(ontology, "position");
	      
//	       Create some resources and literals to make statements from.
	      for (int i = 0;i<100;i++) {
	      	
	      		p.createRandom();
	              p.setName(p.getLabel()+count.getAndIncrement());
	              
	              IRI pID = vf.createIRI(ontology+"Person/", p.getName());
	              Literal pname = vf.createLiteral(p.getName());
	              Literal plabel = vf.createLiteral(p.getLabel());
	              Literal pdescription = vf.createLiteral(p.getDescription());
	              Literal plink = vf.createLiteral(p.getLink());
	              Literal ptime = vf.createLiteral(p.getDate());
	              Literal pposition = vf.createLiteral(p.getPosition());
	              
	              model.add(pID,name,pname,context);
	              model.add(pID,label,plabel, context);
	              model.add(pID,RDF.TYPE,person,context);
	              model.add(pID,description,pdescription, context);
	              model.add(pID,link,plink, context);
	              model.add(pID,timeGetLink,ptime, context);
	              model.add(pID,position,pposition, context);

	      	

	      		p1.createRandom();
	              p1.setName(p1.getLabel()+count.getAndIncrement());
	              
	              IRI p1ID = vf.createIRI(ontology+"Person/", p1.getName());
	              Literal p1name = vf.createLiteral(p1.getName());
	              Literal p1label = vf.createLiteral(p1.getLabel());
	              Literal p1description = vf.createLiteral(p1.getDescription());
	              Literal p1link = vf.createLiteral(p.getLink());
	              Literal p1time = vf.createLiteral(p.getDate());
	              Literal p1position = vf.createLiteral(p1.getPosition());
	              
	              model.add(p1ID,name,p1name,context);
	              model.add(p1ID,label,p1label, context);
	              model.add(p1ID,RDF.TYPE,person,context);
	              model.add(p1ID,description,p1description, context);
	              model.add(p1ID,link,p1link, context);
	              model.add(p1ID,timeGetLink,p1time, context);
	              model.add(p1ID,position,p1position, context);
	              
	            p2.createRandom();
	              p2.setName(p2.getLabel()+count.getAndIncrement());
	              
	              IRI p2ID = vf.createIRI(ontology+"Person/", p2.getName());
	              Literal p2name = vf.createLiteral(p2.getName());
	              Literal p2label = vf.createLiteral(p2.getLabel());
	              Literal p2description = vf.createLiteral(p2.getDescription());
	              Literal p2link = vf.createLiteral(p2.getLink());
	              Literal p2time = vf.createLiteral(p2.getDate());
	              Literal p2position = vf.createLiteral(p2.getPosition());
	              
	              model.add(p2ID,name,p2name,context);
	              model.add(p2ID,label,p2label, context);
	              model.add(p2ID,RDF.TYPE,person,context);
	              model.add(p2ID,description,p2description, context);
	              model.add(p2ID,link,p2link, context);
	              model.add(p2ID,timeGetLink,p2time, context);
	              model.add(p2ID,position,p2position, context);
	      	

	      		c.createRandom();
	              c.setName(c.getLabel()+count.getAndIncrement());
	              
	              IRI cID = vf.createIRI(ontology+"Country/", c.getName());
	              Literal cname = vf.createLiteral(c.getName());
	              Literal clabel = vf.createLiteral(c.getLabel());
	              Literal cdescription = vf.createLiteral(c.getDescription());
	              Literal clink = vf.createLiteral(p.getLink());
	              Literal ctime = vf.createLiteral(p.getDate());
	                             
	              model.add(cID,name,cname,context);
	              model.add(cID,label,clabel, context);
	              model.add(cID,RDF.TYPE,country,context);
	              model.add(cID,description,cdescription, context);
	              model.add(cID,link,clink, context);
	              model.add(cID,timeGetLink,ctime, context);

	      	

	      		c1.createRandom();
	              c1.setName(c1.getLabel()+count.getAndIncrement());
	              
	              IRI c1ID = vf.createIRI(ontology+"Country/", c1.getName());
	              Literal c1name = vf.createLiteral(c1.getName());
	              Literal c1label = vf.createLiteral(c1.getLabel());
	              Literal c1description = vf.createLiteral(c1.getDescription());
	              Literal c1link = vf.createLiteral(p.getLink());
	              Literal c1time = vf.createLiteral(p.getDate());
	                             
	              model.add(c1ID,name,c1name,context);
	              model.add(c1ID,label,c1label, context);
	              model.add(c1ID,RDF.TYPE,country,context);
	              model.add(c1ID,description,c1description, context);
	              model.add(c1ID,link,c1link, context);
	              model.add(c1ID,timeGetLink,c1time, context);

	      	

	      		e.createRandom();
	              e.setName(e.getLabel()+count.getAndIncrement());
	              
	              IRI eID = vf.createIRI(ontology+"Event/", e.getName());
	              Literal ename = vf.createLiteral(e.getName());
	              Literal elabel = vf.createLiteral(e.getLabel());
	              Literal edescription = vf.createLiteral(e.getDescription());
	              Literal elink = vf.createLiteral(p.getLink());
	              Literal etime = vf.createLiteral(p.getDate()); 
	                             
	              model.add(eID,name,ename,context);
	              model.add(eID,label,elabel, context);
	              model.add(eID,RDF.TYPE,event,context);
	              model.add(eID,description,edescription, context);
	              model.add(eID,link,elink, context);
	              model.add(eID,timeGetLink,etime, context);

	      	

	      		l.createRandom();
	              l.setName(l.getLabel()+count.getAndIncrement());
	              
	              IRI lID = vf.createIRI(ontology+"Location/", l.getName());
	              Literal lname = vf.createLiteral(l.getName());
	              Literal llabel = vf.createLiteral(l.getLabel());
	              Literal ldescription = vf.createLiteral(l.getDescription());
	              Literal llink = vf.createLiteral(p.getLink());
	              Literal ltime = vf.createLiteral(p.getDate());
	                             
	              model.add(lID,name,lname,context);
	              model.add(lID,label,llabel, context);
	              model.add(lID,RDF.TYPE,location,context);
	              model.add(lID,description,ldescription, context);
	              model.add(lID,link,llink, context);
	              model.add(lID,timeGetLink,ltime, context);

	      	

	      		o.createRandom();
	              o.setName(o.getLabel()+count.getAndIncrement());
	              
	              IRI oID = vf.createIRI(ontology+"Organization/", o.getName());
	              Literal oname = vf.createLiteral(o.getName());
	              Literal olabel = vf.createLiteral(o.getLabel());
	              Literal odescription = vf.createLiteral(o.getDescription());
	              Literal olink = vf.createLiteral(p.getLink());
	              Literal otime = vf.createLiteral(p.getDate());
	                             
	              model.add(oID,name,oname,context);
	              model.add(oID,label,olabel, context);
	              model.add(oID,RDF.TYPE,organization,context);
	              model.add(oID,description,odescription, context);
	              model.add(oID,link,olink, context);
	              model.add(oID,timeGetLink,otime, context);

	      	

	      		o1.createRandom();
	              o1.setName(o1.getLabel()+count.getAndIncrement());
	              
	              IRI o1ID = vf.createIRI(ontology+"Organization/", o1.getName());
	              Literal o1name = vf.createLiteral(o1.getName());
	              Literal o1label = vf.createLiteral(o1.getLabel());
	              Literal o1description = vf.createLiteral(o1.getDescription());
	              Literal o1link = vf.createLiteral(p.getLink());
	              Literal o1time = vf.createLiteral(p.getDate());
	                             
	              model.add(o1ID,name,o1name,context);
	              model.add(o1ID,label,o1label, context);
	              model.add(o1ID,RDF.TYPE,organization,context);
	              model.add(o1ID,description,o1description, context);
	              model.add(o1ID,link,o1link, context);
	              model.add(o1ID,timeGetLink,o1time, context);
	      	

	      		t.createRandom();
	              t.setName(t.getLabel()+count.getAndIncrement());
	              
	              IRI tID = vf.createIRI(ontology+"Time/", t.getName());
	              Literal tname = vf.createLiteral(t.getName());
	              Literal tlabel = vf.createLiteral(t.getLabel());
	              Literal tdescription = vf.createLiteral(t.getDescription());
	              Literal tlink = vf.createLiteral(p.getLink());
	              Literal ttime = vf.createLiteral(p.getDate());
	                             
	              model.add(tID,name,tname,context);
	              model.add(tID,label,tlabel, context);
	              model.add(tID,RDF.TYPE,time,context);
	              model.add(tID,description,tdescription, context);
	              model.add(tID,link,tlink, context);
	              model.add(tID,timeGetLink,ttime, context);

//	      IRI meet  // p-p relation gặp 
//		  IRI organize // o-e, c-e, p-e relation tổ chức
//		  IRI state  // p-e, o-e, c-e relation phát biểu
//		  IRI go  // p-e,l,c; o-l,c; relation  đi đến 
//		  IRI stay // p-l,c; o-l,c; relation ở 
//		  IRI travel // p-l,c; o-l,c relation thăm quan
//		  IRI join // p-e,o; o-e, c-e relation  tham gia
		      
	        // Person Relation    
	      	switch (i % 12 +1) {
			case 1: // meet
				model.add(pID,meet,p1ID,context);
				model.add(p1ID,meet,pID,context);
				model.add(pID,when,tID,context);
				model.add(p1ID,when,tID,context);
				break;
				
			case 2: // organize e
				model.add(p1ID,organize,eID,context);
				model.add(eID,when,tID,context);
				break;
				
			case 3: // state e
				model.add(p1ID,state,eID,context);
				model.add(p1ID,when,tID,context);
				break;
				
			case 4: //go e
				model.add(pID,go,eID,context);
				model.add(pID,when,tID,context);
				break;
				
			case 5: //go l
				model.add(pID,go,lID,context);
				model.add(pID,when,tID,context);
				break;
			
			case 6: //go c
				model.add(pID,go,cID,context);
				model.add(pID,when,tID,context);
				break;
				
			case 7: //stay l
				model.add(pID,stay,lID,context);
				model.add(pID,when,tID,context);
				break;
				
			case 8: //stay c
				model.add(pID,stay,cID,context);
				model.add(pID,when,tID,context);
				break;
				
			case 9:// travel l
				model.add(p1ID,travel,lID,context);
				model.add(p1ID,when,tID,context);
				break;
				
			case 10:// travel c
				model.add(p1ID,travel,cID,context);
				model.add(p1ID,when,tID,context);
				break;
				
			case 11: // join e
				model.add(pID,join,eID,context);
				model.add(pID,when,tID,context);
				break;
				
			case 12: // join o
				model.add(pID,join,oID,context);
				model.add(pID,when,tID,context);
				break;
				
			default:
				break;
			}
	      	
	      	// Organization Relation
	      	switch (i % 9 + 1) {
	      	
			case 1: // organize e
				model.add(oID,organize,eID,context);
				model.add(oID,when,tID,context);
				break;
				
			case 2: // state e
				model.add(oID,state,eID,context);
				model.add(oID,when,tID,context);
				break;
				
			case 3: // go l
				model.add(oID,go,lID,context);
				model.add(o1ID,when,tID,context);
				break;
				
			case 4: // go c
				model.add(oID,go,cID,context);
				model.add(o1ID,when,tID,context);
				break;
				
			case 5: // stay l
				model.add(oID,stay,lID,context);
				model.add(o1ID,when,tID,context);
				break;
				
			case 6: // stay c
				model.add(oID,stay,cID,context);
				model.add(o1ID,when,tID,context);
				break;
				
			case 7: // travel l
				model.add(oID,travel,lID,context);
				model.add(o1ID,when,tID,context);
				break;
				
			case 8: // travel c
				model.add(oID,travel,cID,context);
				model.add(o1ID,when,tID,context);
				break;
				
			case 9: // join e
				model.add(oID,join,eID,context);
				model.add(o1ID,when,tID,context);
				break;
				
			default:
				break;
			}
	      	
	      	// Country Relation
	      	switch (i % 3 +1 ) {
			
			case 1: // organize e
				model.add(cID,organize,eID,context);
				model.add(cID,when,tID,context);
				break;				
			case 2: // state e
				model.add(cID,state,eID,context);
				model.add(cID,when,tID,context);
				break;
			case 3: // join e
				model.add(cID,join,eID,context);
				model.add(cID,when,tID,context);
				break;				
			default:
				break;
			}
	      	
	      	// Event Relation
	      	switch (i % 3 +1 ) {
			
			case 1: // happen o
				model.add(eID,happen,oID,context);
				model.add(eID,when,tID,context);
				break;				
			case 2: // happen l
				model.add(eID,happen,lID,context);
				model.add(eID,when,tID,context);
				break;
			case 3: // happen c
				model.add(eID,happen,cID,context);
				model.add(eID,when,tID,context);
				break;				
			default:
				break;
			}
	    }

		return model;
	}
		
}
