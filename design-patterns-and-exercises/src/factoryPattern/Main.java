package factoryPattern;

public class Main {
    public static void main(String[] args) {
        ShapeFactory shapeFactory = new ShapeFactory();

        Shape circle = shapeFactory.getShape("CIRCLE");

        Shape rectangle = shapeFactory.getShape("RECTANGLE");

        Shape square = shapeFactory.getShape("SQUARE");
    }
}
