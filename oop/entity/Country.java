package entity;

import utilities.MyRandom;
import utilities.ReadFile;

public class Country extends Entity {
	public Country() {
		super();
	}
	public Country(String name, String label, String description, String link, String date) {
		super(name, label, description, link, date);
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Country [name=" + name + ", label=" + label + ", description=" + description + ", link=" + link
				+ ", date=" + date + "]";
	}
	
	public Country createRandom() {
		ReadFile rf = new ReadFile();
		Country c = new Country();
		this.setLabel(rf.readFile("Entity//Country//label.txt", MyRandom.rand(196)));
		this.setDescription(rf.readFile("Entity//Country//description.txt", MyRandom.rand(24)));
		this.setLink(rf.readFile("Entity//link.txt", MyRandom.rand(600)));
		this.setDate(rf.readFile("Entity//timeGetLink.txt", MyRandom.rand(1000)));	
		return c;
	}
	
}
