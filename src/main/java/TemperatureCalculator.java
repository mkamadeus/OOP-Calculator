public class TemperatureCalculator {

    public float toFahrenheit(float d){
        return ((d * 9 / 5) + 32);
    }

    public float toDegree(float f){
        return ((f - 32) * (float) (5.0 / 9.0));
    }

}
