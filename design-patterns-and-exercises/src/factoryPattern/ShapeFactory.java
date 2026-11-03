package factoryPattern;

public class ShapeFactory {

    public Shape getShape(String shapeType) {
        if (shapeType.equalsIgnoreCase("CIRCLE")) {
            return new CircleFactory().getShape("CIRCLE");
        } else if (shapeType.equalsIgnoreCase("SQUARE")) {
            return new SquareFactory().getShape("SQUARE");
        } else if (shapeType.equalsIgnoreCase("RECTANGLE")) {
            return new RectangleFactory().getShape("RECTANGLE");
        }
        return null;
    }
}
