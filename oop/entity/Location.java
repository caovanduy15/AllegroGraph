package entity;

import utilities.MyRandom;
import utilities.ReadFile;

public class Location extends Entity {
	public Location() {
		super();
	}
	public Location(String name, String label, String description, String link, String date) {
		super(name, label, description, link, date);
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Location [name=" + name + ", label=" + label + ", description=" + description + ", link=" + link
				+ ", date=" + date + "]";
	}
	
	public Location createRandom() {
		ReadFile rf = new ReadFile();
		Location l = new Location();
		setLabel(rf.readFile("Entity//Location//label.txt", MyRandom.rand(136)));
		setDescription(rf.readFile("Entity//Location//description.txt", MyRandom.rand(378)));
		setLink(rf.readFile("Entity//link.txt", MyRandom.rand(600)));
		setDate(rf.readFile("Entity//timeGetLink.txt", MyRandom.rand(1000)));
		return l;
	}

}
