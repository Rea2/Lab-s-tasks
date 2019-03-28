import plane.AbstractPlane;
import plane.ExperimentalPlane;
import model.ClassificationLevel;
import model.MilitaryType;
import org.testng.Assert;
import org.testng.annotations.Test;
import plane.MilitaryPlane;
import plane.PassengerPlane;

import java.util.Arrays;
import java.util.List;

public class AirportTest {
    private static List<AbstractPlane> planes = Arrays.asList(
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

    private static PassengerPlane planeWithMaxPassengerCapacity = PassengerPlane.newBuilder()
            .model("Boeing-747")
            .maxSpeed(980)
            .maxFlightDistance(16100)
            .maxLoadCapacity(70500)
            .passengersCapacity(242)
            .build();

    @Test
    public void testGetTransportMilitaryPlanes() {
        Airport airport = new Airport(planes);
        List<MilitaryPlane> transportMilitaryPlanes = airport.getTransportMilitaryPlanes();
        boolean flag = false;
        for (MilitaryPlane militaryPlane : transportMilitaryPlanes) {
            if ((militaryPlane.getMilitaryType() == MilitaryType.TRANSPORT)) {
                flag = true;
                break;
            }
        }
        Assert.assertEquals(flag, true);
    }

    @Test
    public void testGetPassengerPlaneWithMaxCapacity() {
        System.out.println("TEST testGetPassengerPlaneWithMaxCapacity started!");
        Airport airport = new Airport(planes);
        PassengerPlane expectedPlaneWithMaxPassengersCapacity = airport.getPassengerPlaneWithMaxPassengersCapacity();
        Assert.assertTrue(expectedPlaneWithMaxPassengersCapacity.equals(planeWithMaxPassengerCapacity));
    }

    @Test
    public void test3() {
        Airport airport = new Airport(planes);
        airport.sortByMaxLoadCapacity();
        List<? extends AbstractPlane> planesSortedByMaxLoadCapacity = airport.getPlanes();

        boolean nextPlaneMaxLoadCapacityIsHigherThanCurrent = true;
        for (int i = 0; i < planesSortedByMaxLoadCapacity.size() - 1; i++) {
            AbstractPlane currentPlane = planesSortedByMaxLoadCapacity.get(i);
            AbstractPlane nextPlane = planesSortedByMaxLoadCapacity.get(i + 1);
            if (currentPlane.getMaxLoadCapacity() > nextPlane.getMaxLoadCapacity()) {
                nextPlaneMaxLoadCapacityIsHigherThanCurrent = false;
                break;
            }
        }
        Assert.assertTrue(nextPlaneMaxLoadCapacityIsHigherThanCurrent);
    }

    @Test
    public void testHasAtLeastOneBomberInMilitaryPlanes() {
        Airport airport = new Airport(planes);
        List<MilitaryPlane> bomberMilitaryPlanes = airport.getBomberMilitaryPlanes();
        boolean flag = false;
        for (MilitaryPlane militaryPlane : bomberMilitaryPlanes) {
            if ((militaryPlane.getMilitaryType() == MilitaryType.BOMBER)) {
                flag = true;
            } else {
                Assert.fail("Test failed!");
            }
        }
    }

    @Test
    public void testExperimentalPlanesHasClassificationLevelHigherThanUnclassified() {
        Airport airport = new Airport(planes);
        List<ExperimentalPlane> experimentalPlanes = airport.getExperimentalPlanes();
        boolean hasUnclassifiedPlanes = false;
        for (ExperimentalPlane experimentalPlane : experimentalPlanes) {
            if (experimentalPlane.getClassificationLevel() == ClassificationLevel.UNCLASSIFIED) {
                hasUnclassifiedPlanes = true;
                break;
            }
        }
        Assert.assertFalse(hasUnclassifiedPlanes);
    }
}
