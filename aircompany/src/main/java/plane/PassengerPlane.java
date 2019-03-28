package plane;


import java.util.Objects;

public class PassengerPlane extends Plane {

    private int passengersCapacity;

    public int getPassengersCapacity() {
        return passengersCapacity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), passengersCapacity);
    }

    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) {
            return false;
        }
        if (!(o instanceof PassengerPlane)) {
            return false;
        }
        PassengerPlane plane = (PassengerPlane) o;
        return passengersCapacity == plane.passengersCapacity;
    }

    @Override
    public String toString() {
        return super.toString().replace("}",
                ", passengersCapacity=" + passengersCapacity +
                        '}');
    }

    public static BuilderPassenger newBuilder() {
        return new PassengerPlane().new BuilderPassenger();
    }

    public class BuilderPassenger extends AbstractBuilder<BuilderPassenger> {
        public BuilderPassenger passengersCapacity(int passengersCapacity) {
            PassengerPlane.this.passengersCapacity = passengersCapacity;
            return self();
        }

        @Override
        public PassengerPlane build() {
            return PassengerPlane.this;
        }
    }

}
