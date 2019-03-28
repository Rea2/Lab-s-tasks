package plane;

import model.MilitaryType;

import java.util.Objects;

public class MilitaryPlane extends AbstractPlane {

    private MilitaryType militaryType;

    public MilitaryType getMilitaryType() {
        return militaryType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), militaryType);
    }

    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) {
            return false;
        }
        if (!(o instanceof MilitaryPlane)) {
            return false;
        }
        MilitaryPlane that = (MilitaryPlane) o;
        return this.militaryType == that.militaryType;
    }

    @Override
    public String toString() {
        return super.toString().replace("}",
                ", militaryType=" + militaryType +
                        '}');
    }

    public static BuilderMilitary newBuilder() {
        return new MilitaryPlane().new BuilderMilitary();
    }

    public class BuilderMilitary extends AbstractBuilder<BuilderMilitary> {

        public BuilderMilitary classificationLevel(MilitaryType militaryType) {
            MilitaryPlane.this.militaryType = militaryType;
            return self();
        }
        
        @Override
        public MilitaryPlane build() {
            return MilitaryPlane.this;
        }
    }
}
