package plane;

import java.util.Objects;

public class Plane {
    protected String model;
    private int maxSpeed;
    private int maxFlightDistance;
    private int maxLoadCapacity;

    public String getModel() {
        return model;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public int getMaxFlightDistance() {
        return maxFlightDistance;
    }

    public int getMaxLoadCapacity() {
        int result = this.maxLoadCapacity;
        return result;
    }

    @Override
    public int hashCode() {
        return Objects.hash(model, maxSpeed, maxFlightDistance, maxLoadCapacity);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Plane)) return false;
        Plane plane = (Plane) o;
        return maxSpeed == plane.maxSpeed &&
                maxFlightDistance == plane.maxFlightDistance &&
                maxLoadCapacity == plane.maxLoadCapacity &&
                Objects.equals(model, plane.model);
    }

    @Override
    public String toString() {
        return "Plane{" +
                "model='" + model + '\'' +
                ", maxSpeed=" + maxSpeed +
                ", maxFlightDistance=" + maxFlightDistance +
                ", maxLoadCapacity=" + maxLoadCapacity +
                '}';
    }


    public abstract class AbstractBuilder<B extends AbstractBuilder<B>> {

        public B model(String model) {
            Plane.this.model = model;
            return self();
        }

        public B maxSpeed(int maxSpeed) {
            Plane.this.maxSpeed = maxSpeed;
            return self();
        }

        public B maxFlightDistance(int maxFlightDistance) {
            Plane.this.maxFlightDistance = maxFlightDistance;
            return self();
        }

        public B maxLoadCapacity(int maxLoadCapacity) {
            Plane.this.maxLoadCapacity = maxLoadCapacity;
            return self();
        }

        @SuppressWarnings("unchecked")
        final B self() {
            return (B) this;
        }

        public Plane build() {
            return Plane.this;
        }
    }

    public class Builder extends Plane.AbstractBuilder {

        @Override
        public Plane build() {
            return Plane.this;
        }
    }
}
