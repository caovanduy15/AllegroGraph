package entity;

import utilities.MyRandom;
import utilities.ReadFile;

public class Event extends Entity {
	public Event() {
		super();
	}


	public Event(String name, String label, String description, String link, String date) {
		super(name, label, description, link, date);
		// TODO Auto-generated constructor stub
	}


	@Override
	public String toString() {
		return "Event [name=" + name + ", label=" + label + ", description=" + description + ", link=" + link
				+ ", date=" + date + "]";
	}



	public Event createRandom() {
		ReadFile rf = new ReadFile();
		Event e = new Event();
		setLabel(rf.readFile("Entity//Event//label.txt", MyRandom.rand(245)));
		setDescription(rf.readFile("Entity//Event//description.txt", MyRandom.rand(300)));
		setLink(rf.readFile("Entity//link.txt", MyRandom.rand(600)));
		setDate(rf.readFile("Entity//timeGetLink.txt", MyRandom.rand(1000)));
		return e;
	}
	
}
