package factoryPattern;

public class SquareFactory extends ShapeFactory {
    @Override
    public Shape getShape(String shapeType) {
        if (shapeType.equalsIgnoreCase("SQUARE")) {
            Square square = new Square();
            square.draw();
            return square;
        }
        return null;
    }
}
