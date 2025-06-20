import java.util.Arrays;
import java.util.Comparator;

interface Transport {
    void deliver();
}

class Car implements Transport {
    @Override
    public void deliver() {
        System.out.println("Delivering by Car: Fast and for smaller loads.");
    }
}

class Truck implements Transport {
    @Override
    public void deliver() {
        System.out.println("Delivering by Truck: For heavy and large loads.");
    }
}

class Bike implements Transport {
    @Override
    public void deliver() {
        System.out.println("Delivering by Bike: For quick, small deliveries in congested areas.");
    }
}

abstract class Logistics {
    public abstract Transport createTransport();

    public void planDelivery() {
        Transport transport = createTransport();
        System.out.println("Planning delivery...");
        transport.deliver();
        System.out.println("Delivery plan complete.\n");
    }
}

class RoadLogistics extends Logistics {
    @Override
    public Transport createTransport() {
        return new Car();
    }
}

class SeaLogistics extends Logistics {
    @Override
    public Transport createTransport() {
        return new Truck();
    }
}

class UrbanLogistics extends Logistics {
    @Override
    public Transport createTransport() {
        return new Bike();
    }
}

public class FactoryMethodPattern {
    public static void main(String[] args) {
        System.out.println("--- Road Logistics ---");
        Logistics roadLogistics = new RoadLogistics();
        roadLogistics.planDelivery();

        System.out.println("--- Sea Logistics ---");
        Logistics seaLogistics = new SeaLogistics();
        seaLogistics.planDelivery();

        System.out.println("--- Urban Logistics ---");
        Logistics urbanLogistics = new UrbanLogistics();
        urbanLogistics.planDelivery();
    }
}