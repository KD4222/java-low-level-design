package factoryPattern;

public class CircleFactory extends ShapeFactory {
    @Override
    public Shape getShape(String shapeType) {
        if (shapeType.equalsIgnoreCase("CIRCLE")) {
            Circle circle = new Circle();
            circle.draw();
            return circle;
        }
        return null;
    }
}
