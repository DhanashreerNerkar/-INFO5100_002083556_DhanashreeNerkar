import java.io.*;

// Main class to test serialization and deserialization
public class Main {
    public static void main(String[] args) {
        // Create objects
        Triangle t = new Triangle(3, 2, 3, 5, 7);
        Rectangle r = new Rectangle("Rectangle", 3, 2);
        Square s = new Square("Square", 3);
        Circle c = new Circle(3);

        // Display details before serialization
        System.out.println("Before Serialization:");
        t.DisplayDetails();
        r.DisplayDetails();
        s.DisplayDetails();
        c.DisplayDetails();

        // Serialize each object into its own .ser file
        serializeObject("triangle.ser", t);
        serializeObject("rectangle.ser", r);
        serializeObject("square.ser", s);
        serializeObject("circle.ser", c);

        System.out.println("\nObjects have been serialized into separate files.");

        // Deserialize objects from their respective .ser files
        Triangle tDeserialized = (Triangle) deserializeObject("triangle.ser");
        Rectangle rDeserialized = (Rectangle) deserializeObject("rectangle.ser");
        Square sDeserialized = (Square) deserializeObject("square.ser");
        Circle cDeserialized = (Circle) deserializeObject("circle.ser");

        System.out.println("\nAfter Deserialization:");
        tDeserialized.DisplayDetails();
        rDeserialized.DisplayDetails();
        sDeserialized.DisplayDetails();
        cDeserialized.DisplayDetails();
    }

    // Method to serialize an object to a file
    public static void serializeObject(String filename, Object obj)
    {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename)))
        {
            oos.writeObject(obj);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    // Method to deserialize an object from a file
    public static Object deserializeObject(String filename)
    {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename)))
        {
            return ois.readObject();
        } catch (IOException | ClassNotFoundException e)
        {
            e.printStackTrace();
            return null;
        }
    }
}
