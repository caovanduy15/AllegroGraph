package entity;

import utilities.MyRandom;
import utilities.ReadFile;

public class Organization  extends Entity{
	public Organization() {
		super();
	}


	public Organization(String name, String label, String description, String link, String date) {
		super(name, label, description, link, date);
		// TODO Auto-generated constructor stub
	}


	public Organization(String name, String label, String description, String link, String date, String timeSet) {
		super(name, label, description, link, date);
	}




	@Override
	public String toString() {
		return "Organization [name=" + name + ", label=" + label + ", description="
				+ description + ", link=" + link + ", date=" + date + "]";
	}

 
	public Organization createRandom() {
		ReadFile rf = new ReadFile();
		Organization o = new Organization();
		setLabel(rf.readFile("Entity//Organization//label.txt", MyRandom.rand(1000)));
		setDescription(rf.readFile("Entity//Organization//description.txt", MyRandom.rand(1000)));
		setLink(rf.readFile("Entity//link.txt", MyRandom.rand(600)));
		setDate(rf.readFile("Entity//timeGetLink.txt", MyRandom.rand(1000)));
		return o;
	}
	
}
