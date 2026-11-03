package factoryPattern;

public class RectangleFactory extends ShapeFactory {
    @Override
    public Shape getShape(String shapeType) {
        if (shapeType.equalsIgnoreCase("RECTANGLE")) {
            Rectangle rectangle = new Rectangle();
            rectangle.draw();
            return rectangle;
        }
        return null;
    }
}
