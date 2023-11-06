package dk.tec.maso41;

public class Haircolor {
    int id;
    String name;

    public Haircolor() {}
    public Haircolor(String name) {
        this.name = name;
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
}


