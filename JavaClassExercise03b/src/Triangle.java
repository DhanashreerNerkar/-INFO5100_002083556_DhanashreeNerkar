// Serializable class Triangle
class Triangle extends Shape {
    private double side1, side2, side3;

    public Triangle(double base, double altitude, double side1, double side2, double side3) {
        super("Triangle", base, altitude);
        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;
    }

    @Override
    double CalculateArea() {
        return 0.5 * breadth * height;
    }

    @Override
    double CalculatePerimeter() {
        return side1 + side2 + side3;
    }
}