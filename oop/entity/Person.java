package entity;

import utilities.MyRandom;
import utilities.ReadFile;

public class Person  extends Entity{
	private String position;
	
	
	public Person() {
		super();
	}

	public Person(String name, String label, String description, String link, String date) {
		super(name, label, description, link, date);
		// TODO Auto-generated constructor stub
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Person(String name, String label, String description, String link, String date, String position) {
		super(name, label, description, link, date);
		this.position = position;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", label=" + label + ", description=" + description +  ", position=" + position  
				+ ", link=" + link + ", date=" + date + "]";
	}

	public Person createRandom() {
		ReadFile rf = new ReadFile();
		Person p = new Person();
		setLabel(rf.readFile("Entity//Person//label.txt", MyRandom.rand(600)));
		setDescription(rf.readFile("Entity//Person//description.txt", MyRandom.rand(596)));
		setLink(rf.readFile("Entity//link.txt", MyRandom.rand(600)));
		setDate(rf.readFile("Entity//timeGetLink.txt", MyRandom.rand(1000)));
		setPosition(rf.readFile("Entity//Person//Position.txt", MyRandom.rand(92)));
		return p;
	}
	
	
	
	
	
}
