import java.io.Serializable;

// Serializable abstract class Shape
abstract class Shape implements Serializable {
    public static String color = "Blue"; // Static fields are not serialized
    public String Shape_name = "";
    public double breadth, height, radius;

    abstract double CalculateArea();
    abstract double CalculatePerimeter();

    public Shape(String shape_name, double breadth, double height) {
        this.Shape_name = shape_name;
        this.breadth = breadth;
        this.height = height;
    }

    public Shape(String shape_name, double radius) {
        this.Shape_name = shape_name;
        this.radius = radius;
    }

    public void DisplayDetails() {
        System.out.println("\nShape: " + this.Shape_name);
        System.out.println("Color: " + color);
        System.out.println("Area: " + CalculateArea());
        System.out.println("Perimeter: " + CalculatePerimeter());
    }
}