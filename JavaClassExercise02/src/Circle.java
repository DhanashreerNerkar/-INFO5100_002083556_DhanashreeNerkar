class Circle extends Shape{

    public Circle(double radius)
    {super("Circle",radius);
    }

    @Override
    double CalculateArea() {
        return 3.14*radius*radius;
    }

    @Override
    double CalculatePerimeter() {
        return 3.14*2*radius;
    }


}
