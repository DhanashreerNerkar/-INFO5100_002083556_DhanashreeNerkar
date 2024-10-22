 class Rectangle extends Shape{

     public Rectangle(String shapename,double breadth, double height)
     {
         super(shapename, breadth, height);
     }
     @Override
     double CalculateArea() {
         return breadth * height;
     }

     @Override
     double CalculatePerimeter() {
         return 2*(breadth + height);
     }
 }
