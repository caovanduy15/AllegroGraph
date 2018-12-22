package entity;

public class Entity {
	
	
	
	protected String name;
	protected String label;
	protected String description;
	protected String link;
	protected String date;
	
	
	
	public Entity() {
		super();
		
	}



	public Entity(String name, String label, String description, String link, String date) {
		super();
		this.name = name;
		this.label = label;
		this.description = description;
		this.link = link;
		this.date = date;
	}
	
	

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}


	public String getDate() {
		return date;
	}



	public void setDate(String date) {
		this.date = date;
	}

	public void getDetails(Entity e) {
		if(e instanceof Person) System.out.println(((Person)e).toString());
			else if(e instanceof Country) System.out.println(((Country)e).toString());	
			else if(e instanceof Organization) System.out.println(((Organization)e).toString());	
			else if(e instanceof Country) System.out.println(((Country)e).toString());	
			else if(e instanceof Location) System.out.println(((Location)e).toString());
			else if(e instanceof Time) System.out.println(((Time)e).toString());	
			else System.out.println(e.toString());
	}

	@Override
	public String toString() {
		return "Entity: name=" + name + ", label=" + label + ", description=" + description + ", link=" + link
				+ ", date=" + date;
	}
	
}
