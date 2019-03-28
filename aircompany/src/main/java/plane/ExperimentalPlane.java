package plane;

import model.ClassificationLevel;
import model.ExperimentalTypes;

import java.util.Objects;

public class ExperimentalPlane extends AbstractPlane {

    private ExperimentalTypes experimentalType;
    private ClassificationLevel classificationLevel;

    public ClassificationLevel getClassificationLevel() {
        return classificationLevel;
    }

    public ExperimentalTypes getExperimentalType() {
        return experimentalType;
    }

    public void setExperimentalType(ExperimentalTypes experimentalType) {
        this.experimentalType = experimentalType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), experimentalType, classificationLevel);
    }

    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) {
            return false;
        }
        if (!(o instanceof ExperimentalPlane)) {
            return false;
        }
        ExperimentalPlane that = (ExperimentalPlane) o;
        return this.experimentalType == that.getExperimentalType()
                && this.classificationLevel == that.getClassificationLevel();
    }

    @Override
    public String toString() {
        return super.toString().replace("AbstractPlane{", "ExperimentalPlane{")
                .replace("}",
                        ", experimentalType=" + experimentalType +
                                ",  classificationLevel=" + classificationLevel +
                                '}');
    }

    public static ExperimentalPlane.BuilderExperimental newBuilder() {
        return new ExperimentalPlane().new BuilderExperimental();
    }

    public class BuilderExperimental extends AbstractBuilder<BuilderExperimental> {

        public BuilderExperimental classificationLevel(ClassificationLevel classificationLevel) {
            ExperimentalPlane.this.classificationLevel = classificationLevel;
            return self();
        }

        public BuilderExperimental experimentalType(ExperimentalTypes experimentalType) {
            ExperimentalPlane.this.experimentalType = experimentalType;
            return self();
        }

        @Override
        public ExperimentalPlane build() {
            return ExperimentalPlane.this;
        }
    }


}
