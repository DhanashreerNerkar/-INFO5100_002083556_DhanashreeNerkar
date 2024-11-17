package observer;

public class WeatherDevice implements WeatherObserver{
    private String deviceName;

    public WeatherDevice(String deviceName) {
        this.deviceName = deviceName;
    }

    @Override
    public void update(float temperature, float humidity) {
        System.out.println(deviceName + " received update -> Temperature: " + temperature + ", Humidity: " + humidity);
    }
}
