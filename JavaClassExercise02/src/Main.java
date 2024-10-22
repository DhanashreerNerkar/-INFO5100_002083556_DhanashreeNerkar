//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args)
    {
        Triangle t = new Triangle(3,2,3,5,7);
        t.DisplayDetails();

        Rectangle r = new Rectangle("Rectangle",3,2);
        r.DisplayDetails();

        Square s =  new Square("Square",3);
        s.DisplayDetails();

        Circle c=new Circle(3);
        c.DisplayDetails();
    }
}