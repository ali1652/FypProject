package multi.screen.fypproject;

import android.text.Editable;

public class foodItem {
    String key;
    int id;
    String brand;
    String name;
    String category;
    int quantity;
    int size;
    String measurement;

    static int idCounter =1;

    public foodItem(){

    }

    public foodItem( String brand, String name, String category,
                     int size, String measurement, int quantity) {
        this.brand = brand;
        this.name = name;
        this.category = category;
        this.quantity = quantity;
        this.id = idCounter++;
        this.size = size;
        this.measurement = measurement;

    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCounter() {
        return idCounter;
    }
////////////////////////////////////////////////////////////////
    public void setIdCounter(int idCounter) {
        foodItem.idCounter = idCounter;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getMeasurement() {
        return measurement;
    }

    public void setMeasurement(String measurement) {
        this.measurement = measurement;
    }
}
