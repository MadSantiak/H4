package dk.tec.maso41;


public class Person {
    int id;
    String name;
    String phone;
    String address;
    String note;
    boolean favorite = false;
	int haircolor_id;
//	ProgLang proglang

    public Person() {};
    public Person(String name, String phone, String address) {
        this.name = name;
        this.phone = phone;
        this.address = address;
    }
    public Person(String name, String phone, String address, String note, boolean favorite) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.note = note;
        this.favorite = favorite;
    }
    public Person(String name, String phone, String address, String note, boolean favorite, int haircolor_id) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.note = note;
        this.favorite = favorite;
        this.haircolor_id = haircolor_id;
    }

    public int getHaircolor_id() {
		return haircolor_id;
	}
	public void setHaircolor_id(int haircolor_id) {
		this.haircolor_id = haircolor_id;
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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }
    public boolean getFavorite() { return favorite; }
}
