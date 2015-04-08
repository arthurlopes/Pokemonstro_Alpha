package Map;

public class City {
	
	private Building[] buildings;
	private int number_buildings;
	private String name;
	
	public City(String name, int number_buildings) {
		buildings = new Building[number_buildings];
		this.number_buildings =  number_buildings;
		this.name = name;
		
		buildings[0] = new Building("/Images/background.png", 0, 0, "background");
		buildings[1] = new Building("/Images/house.png", 50, 60, "house1");
		buildings[2] = new Building("/Images/house.png", 160, 60, "house2");
		buildings[3] = new Building("/Images/center.png", 160, 120, "center");
	}
	
	public Building[] getBuildings() {
		return buildings;
	}
}
