import singleton.ConfigurationManager;
import factory.Notification;
import factory.NotificationFactory;
import observer.WeatherDevice;
import observer.WeatherStation;

public class Main {
    public static void main(String[] args) {

        // Singleton Pattern
        System.out.println("Singleton Pattern Demo:");
        ConfigurationManager configManager = ConfigurationManager.getInstance();
        System.out.println("Initial Config: " + configManager.getConfig());
        configManager.setConfig("Updated Configuration");
        System.out.println("Updated Config: " + configManager.getConfig());
        System.out.println();

        // Factory Method Pattern
        System.out.println("Factory Method Pattern Demo:");
        Notification email = NotificationFactory.createNotification("email");
        Notification sms = NotificationFactory.createNotification("sms");
        Notification push = NotificationFactory.createNotification("push");

        email.notifyUser("Your account has been created.");
        sms.notifyUser("Your OTP is 123456.");
        push.notifyUser("New feature is available now!");
        System.out.println();

        // Observer Pattern
        System.out.println("Observer Pattern Demo:");
        WeatherStation weatherStation = new WeatherStation();

        WeatherDevice phone = new WeatherDevice("Phone");
        WeatherDevice tablet = new WeatherDevice("Tablet");

        weatherStation.addObserver(phone);
        weatherStation.addObserver(tablet);

        weatherStation.setWeather(25.0f, 65.0f); // Notify all observers
        weatherStation.setWeather(30.0f, 70.0f); // Notify all observers again
    }
}