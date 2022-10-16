package towson.cosc435.cookbook;

public class Ingredient {
    String name;
    String measurement;
    String quantity;

    public Ingredient(String name, String measurement, String quantity) {
        this.name = name;
        this.measurement = measurement;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getMeasurement() {
        return measurement;
    }
    public void setMeasurement(String measurement) {
        this.measurement = measurement;
    }

    public String getQuantity() {
        return quantity;
    }
    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return quantity + " " + measurement+ " " + name;
    }
}
