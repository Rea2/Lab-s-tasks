import model.MilitaryType;
import plane.AbstractPlane;
import plane.ExperimentalPlane;
import plane.MilitaryPlane;
import plane.PassengerPlane;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

// version: 1.1
// made by Vitali Shulha
// 4-Jan-2019

public class Airport {

    private List<? extends AbstractPlane> planes;

    public Airport(List<? extends AbstractPlane> planes) {
        this.planes = planes;
    }

    public List<PassengerPlane> getPassengerPlanes() {
        return (List<PassengerPlane>) getDefinedPlanes(PassengerPlane.class);
    }

    public List<MilitaryPlane> getMilitaryPlanes() {
        return (List<MilitaryPlane>) getDefinedPlanes(MilitaryPlane.class);
    }

    public List<ExperimentalPlane> getExperimentalPlanes() {
        return (List<ExperimentalPlane>) getDefinedPlanes(ExperimentalPlane.class);
    }

    private <T extends AbstractPlane> List<? extends AbstractPlane> getDefinedPlanes(Class<T> tClass) {
        return planes.stream()
                .filter(x -> tClass.isInstance(x))
                .map(x -> (T) x)
                .collect(Collectors.toList());
    }

    public PassengerPlane getPassengerPlaneWithMaxPassengersCapacity() {
        return Collections.max(getPassengerPlanes(),
                Comparator.comparingInt(PassengerPlane::getPassengersCapacity));
    }

    public List<MilitaryPlane> getTransportMilitaryPlanes() {
        return getDefinedMilitaryPlanes(MilitaryType.TRANSPORT);
    }

    public List<MilitaryPlane> getBomberMilitaryPlanes() {
        return getDefinedMilitaryPlanes(MilitaryType.BOMBER);
    }

    public List<MilitaryPlane> getDefinedMilitaryPlanes(MilitaryType militaryType) {
        return getMilitaryPlanes()
                .stream()
                .filter(z -> z.getMilitaryType() == militaryType)
                .collect(Collectors.toList());
    }

    public Airport sortByMaxDistance() {
        Collections.sort(planes, Comparator.comparingInt(AbstractPlane::getMaxFlightDistance));
        return this;
    }

    public Airport sortByMaxSpeed() {
        Collections.sort(planes, Comparator.comparingInt(AbstractPlane::getMaxSpeed));
        return this;
    }

    public Airport sortByMaxLoadCapacity() {
        Collections.sort(planes, Comparator.comparingInt(AbstractPlane::getMaxLoadCapacity));
        return this;
    }

    public List<? extends AbstractPlane> getPlanes() {
        return planes;
    }

    private void printPlanes(Collection<? extends AbstractPlane> collection) {
        collection.forEach(x -> System.out.println(x));
    }

    @Override
    public String toString() {
        return "Airport{" +
                "plane=" + planes.toString() +
                '}';
    }
}
