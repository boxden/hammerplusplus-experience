package HUC;

/*
 *
 * @author Max Danielsson * HUC inc
 *
 * Make something that does one thing and make it simple.
 */
public class Converter {
    //private double inputValue;
    private double currentValue;
    private double currentCoefToMeter; //With 
    //private String inputType;
    private String currentType;
    private final double foot = 0.3048;
    private final double yard = 0.9144;
    private final double unit = 0.01905;
    private final double inch = 0.0254;
    private final double meter = 1;
    private final double skybox = 0.3048;
    private final double decimeter = 0.1;
    private final double centimeter = 0.01;
    /*
     * calls setValue(String g, double v)
     */

    public Converter(double value, String indx) {
        setValue(value, indx);
    }

    public Converter() {
    }

    /*
     * Gets the Current value that is set by setType(int index, double value) or setValue().
     */
    public double getCurrent() {
        return currentValue;
    }

    public double getCurrentIn(String indx) {
        double secondCoef;
        secondCoef = getCoef(indx);
        return grabValue(currentCoefToMeter, secondCoef, currentValue);
    }

    /*
     * Sets the value and type.
     */
    public void setValue(double value, String indx) {
        currentType = indx;
        currentCoefToMeter = getCoef(indx);
        currentValue = value;
    }

    /*
     * 
     * self, turn around by doing 1/VALUE = REVERTED
     * Thing is, all values goes from one type to another VIA the math for turning said value into meters.
     * Sets type after String value.
     * foot 0.03048
     * yard 0.9144
     * unit 0.01905
     * inch 0.0254
     * meter 1
     * skybox 0.03048
     * decimeter 0.1
     * centimeter 0.01
     */
    public void setType(String indx) {
        currentType = indx;
        currentCoefToMeter = getCoef(indx);
    }

    private double getCoef(String indx) {
        if (indx.length() <= 4) {
            if (indx.equals("foot")) {
                return foot;
            }

            if (indx.equals("yard")) {
                return yard;
            }

            if (indx.equals("unit")) {
                return unit;
            }

            if (indx.equals("inch")) {
                return inch;
            }


        } else {
            if (indx.equals("meter")) {
                return meter;
            }


            if (indx.equals("skybox")) {
                return skybox;
            }

            if (indx.equals("decimeter")) {
                return decimeter;
            }

            if (indx.equals("centimeter")) {
                return centimeter;
            }
        }
        return 0;
    }

    private double grabValue(double from, double to, double value) {

        return (value * (from * 1 / to)); // TODO: find out WHY i need to use *10
    }
}


