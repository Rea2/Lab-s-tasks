package plane;

import java.util.Objects;

public class AbstractPlane {
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
        if (!(o instanceof AbstractPlane)) return false;
        AbstractPlane plane = (AbstractPlane) o;
        return maxSpeed == plane.maxSpeed &&
                maxFlightDistance == plane.maxFlightDistance &&
                maxLoadCapacity == plane.maxLoadCapacity &&
                Objects.equals(model, plane.model);
    }

    @Override
    public String toString() {
        return "AbstractPlane{" +
                "model='" + model + '\'' +
                ", maxSpeed=" + maxSpeed +
                ", maxFlightDistance=" + maxFlightDistance +
                ", maxLoadCapacity=" + maxLoadCapacity +
                '}';
    }

    public abstract class AbstractBuilder<B extends AbstractBuilder<B>> {

        public B model(String model) {
            AbstractPlane.this.model = model;
            return self();
        }

        public B maxSpeed(int maxSpeed) {
            AbstractPlane.this.maxSpeed = maxSpeed;
            return self();
        }

        public B maxFlightDistance(int maxFlightDistance) {
            AbstractPlane.this.maxFlightDistance = maxFlightDistance;
            return self();
        }

        public B maxLoadCapacity(int maxLoadCapacity) {
            AbstractPlane.this.maxLoadCapacity = maxLoadCapacity;
            return self();
        }

        @SuppressWarnings("unchecked")
        final B self() {
            return (B) this;
        }

        public AbstractPlane build() {
            return AbstractPlane.this;
        }
    }
}
