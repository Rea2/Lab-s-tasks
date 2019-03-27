package plane;

import model.*;
import model.ExperimentalTypes;
import java.util.Objects;

public class ExperimentalPlane extends Plane{

    private ExperimentalTypes experimentalType;
    private ClassificationLevel classificationLevel;

    public ExperimentalPlane(String model, int maxSpeed, int maxFlightDistance, int maxLoadCapacity,
                             ExperimentalTypes experimentalType, ClassificationLevel classificationLevel) {
        super(model, maxSpeed, maxFlightDistance, maxLoadCapacity);
        this.experimentalType = experimentalType;
        this.classificationLevel = classificationLevel;
    }

    public ClassificationLevel getClassificationLevel(){
        return classificationLevel;
    }

    public void setClassificationLevel(ClassificationLevel classificationLevel){
        this.classificationLevel = classificationLevel;
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
        ExperimentalPlane that = (ExperimentalPlane)o;
        return this.experimentalType == that.getExperimentalType()
                    && this.classificationLevel == that.getClassificationLevel();
    }

    @Override
    public String toString() {
        return  super.toString().replace("Plane{", "ExperimentalPlane{" )
                .replace("}",
                ", experimentalType=" + experimentalType +
                        ",  classificationLevel=" +  classificationLevel +
                        '}');
    }

}
