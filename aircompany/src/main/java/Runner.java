import model.MilitaryType;
import plane.MilitaryPlane;
import plane.PassengerPlane;
import plane.AbstractPlane;

import java.util.Arrays;
import java.util.List;

public class Runner {
    static List<AbstractPlane> planes = Arrays.asList(
            PassengerPlane.newBuilder()
                    .model("Boeing-737")
                    .maxSpeed(900)
                    .maxFlightDistance(12000)
                    .maxLoadCapacity(60500)
                    .passengersCapacity(164)
                    .build(),

            PassengerPlane.newBuilder()
                    .model("Boeing-737-800")
                    .maxSpeed(940)
                    .maxFlightDistance(12300)
                    .maxLoadCapacity(63870)
                    .passengersCapacity(192)
                    .build(),

            PassengerPlane.newBuilder()
                    .model("Boeing-747")
                    .maxSpeed(980)
                    .maxFlightDistance(16100)
                    .maxLoadCapacity(70500)
                    .passengersCapacity(242)
                    .build(),

            PassengerPlane.newBuilder()
                    .model("Airbus A320")
                    .maxSpeed(980)
                    .maxFlightDistance(16100)
                    .maxLoadCapacity(70500)
                    .passengersCapacity(242)
                    .build(),

            PassengerPlane.newBuilder()
                    .model("Airbus A330")
                    .maxSpeed(980)
                    .maxFlightDistance(16100)
                    .maxLoadCapacity(70500)
                    .passengersCapacity(242)
                    .build(),

            PassengerPlane.newBuilder()
                    .model("Embraer 190")
                    .maxSpeed(980)
                    .maxFlightDistance(16100)
                    .maxLoadCapacity(70500)
                    .passengersCapacity(242)
                    .build(),

            PassengerPlane.newBuilder()
                    .model("Sukhoi Superjet 100")
                    .maxSpeed(980)
                    .maxFlightDistance(16100)
                    .maxLoadCapacity(70500)
                    .passengersCapacity(242)
                    .build(),

            PassengerPlane.newBuilder()
                    .model("Bombardier CS300")
                    .maxSpeed(980)
                    .maxFlightDistance(16100)
                    .maxLoadCapacity(70500)
                    .passengersCapacity(242)
                    .build(),

            MilitaryPlane.newBuilder()
                    .model("B-1B Lancer")
                    .maxSpeed(980)
                    .maxFlightDistance(16100)
                    .maxLoadCapacity(70500)
                    .classificationLevel(MilitaryType.BOMBER)
                    .build(),

            MilitaryPlane.newBuilder()
                    .model("B-2 Spirit")
                    .maxSpeed(980)
                    .maxFlightDistance(16100)
                    .maxLoadCapacity(70500)
                    .classificationLevel(MilitaryType.BOMBER)
                    .build(),

            MilitaryPlane.newBuilder()
                    .model("B-52 Stratofortress")
                    .maxSpeed(980)
                    .maxFlightDistance(16100)
                    .maxLoadCapacity(70500)
                    .classificationLevel(MilitaryType.BOMBER)
                    .build(),
            MilitaryPlane.newBuilder()
                    .model("F-15")
                    .maxSpeed(980)
                    .maxFlightDistance(16100)
                    .maxLoadCapacity(70500)
                    .classificationLevel(MilitaryType.FIGHTER)
                    .build(),

            MilitaryPlane.newBuilder()
                    .model("F-22")
                    .maxSpeed(980)
                    .maxFlightDistance(16100)
                    .maxLoadCapacity(70500)
                    .classificationLevel(MilitaryType.FIGHTER)
                    .build(),

            MilitaryPlane.newBuilder()
                    .model("C-130 Hercules")
                    .maxSpeed(980)
                    .maxFlightDistance(16100)
                    .maxLoadCapacity(70500)
                    .classificationLevel(MilitaryType.TRANSPORT)
                    .build()
    );

    public static void main(String[] args) {
        Airport airport = new Airport(planes);
        Airport militaryAirport = new Airport(airport.getMilitaryPlanes());
        Airport passengerAirport = new Airport(airport.getPassengerPlanes());
        System.out.println("Military airport sorted by max distance: " + militaryAirport
                .sortByMaxDistance()
                .toString());
        System.out.println("Passenger airport sorted by max speed: " + passengerAirport
                .sortByMaxSpeed()
                .toString());

        System.out.println("Plane with max passenger capacity: " + passengerAirport.getPassengerPlaneWithMaxPassengersCapacity());
        planes.forEach(x -> System.out.println(x));
    }
}

