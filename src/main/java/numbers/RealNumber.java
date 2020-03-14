package numbers;

public class RealNumber implements Number {
    private Double value;

    public RealNumber() {this.value = 0.0;}
    public RealNumber(Double v) {this.value = v;}

    public Double value() {
        return this.value;
    }

}