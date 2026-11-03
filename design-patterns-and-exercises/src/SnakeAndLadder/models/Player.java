package models;

public class Player {
    private final String name;
    private Integer position;

    public Player(String name) {
        this.name = name;
        this.position = 1;
    }

    public String getName() {
        return name;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }
}
