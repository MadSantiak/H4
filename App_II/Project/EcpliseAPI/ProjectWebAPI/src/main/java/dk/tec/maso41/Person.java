package dk.tec.maso41;

public class Person {
	int id;
	String name;
	String phone;
	String address;
	String note;
	boolean favorite = false;
//	Haircolor haircolor
//	ProgLang proglang
	
	public Person() {};
	public Person(int id, String name, String phone, String address) {
		super();
		this.name = name;
		this.phone = phone;
		this.address = address;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	private void getFavorite() {
		// TODO Auto-generated method stub
	}
	public void setFavorite(boolean favorite) {
		this.favorite = favorite;
	}
}
