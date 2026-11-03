/**
 * Player
 */
public class Player {
    String id;
    String name;
    DiscColor color;
    public Player(String id, DiscColor color) {
        this.id = id;
        this.name = "player"+id;
        this.color = color;
    }

    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public DiscColor getColor() {
        return color;
    }
}
