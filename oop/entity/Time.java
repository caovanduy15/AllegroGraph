package entity;

import utilities.MyRandom;
import utilities.ReadFile;

public class Time  extends Entity{
	public Time() {
		super();
	}
	public Time(String name, String label, String description, String link, String date) {
		super(name, label, description, link, date);
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Time [name=" + name + ", label=" + label + ", description=" + description + ", link=" + link + ", date="
				+ date + "]";
	}
	
	public Time createRandom() {
		ReadFile rf = new ReadFile();
		Time t = new Time();
		setLabel(rf.readFile("Entity//Time//label.txt", MyRandom.rand(800)));
		setDescription(rf.readFile("Entity//Time//description.txt", MyRandom.rand(600)));
		setLink(rf.readFile("Entity//link.txt", MyRandom.rand(600)));
		setDate(rf.readFile("Entity//timeGetLink.txt", MyRandom.rand(1000)));
		return t;
	}
	
}
