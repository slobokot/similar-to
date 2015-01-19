package com.slobokot.similarto.matcher;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.slobokot.similarto.SimilarToMatchers.similarTo;
import static org.junit.Assert.*;

public class ObjectTest {

    public static class SingleStringProperty {
        public String getName() {
            return "Masha";
        }
    }

    @Test
    public void singleStringProperty() {
        assertThat(new SingleStringProperty(), similarTo("{ name : Masha }"));
    }

    public static class MultiProperty {
        public String getName() {
            return "Masha";
        }
        public int getPrice() {
            return 100;
        }
        public double getAge() {
            return 14.5;
        }
    }

    @Test
    public void multiProperty() {
        assertThat(new MultiProperty(), similarTo("{ name : Masha, price:100, age:14.5 }"));
    }

    static class ValueIsListOfComplexType {
        public List<Car> getListCar() {
            List<Car> result = new ArrayList<Car>();
            result.add(new Car("ferrari","v8"));
            result.add(new Car("mercedes","v6"));
            return result;
        }
    }

    public static class Car {
        private String car;
        private String engine;

        public Car(String car, String engine) {
            this.car = car;
            this.engine = engine;
        }

        public String getCar() {
            return car;
        }

        public String getEngine() {
            return engine;
        }
    }

    @Test
    public void valueIsListOfComplexTypeWithString() {
        assertThat(new ValueIsListOfComplexType(), similarTo(
                "{ listCar:{{car:ferrari, engine:v8}, {car:mercedes, engine:v6}} }"));
    }

    public static class ValueIsMapOfComplexType {
        public Map<String,Car> cars() {
            Map<String,Car> cars = new HashMap<String, Car>();
            cars.put("first", new Car("ferrari","v8"));
            cars.put("second", new Car("mercedes","v6"));
            return cars;
        }
    }

    @Test
    public void valueIsMapOfComplexType() {
        assertThat(new ValueIsMapOfComplexType(), similarTo(
                "{ cars : {" +
                        "first:" +
                        "{car:ferrari, engine:v8}, " +
                        "second:" +
                        "{car:mercedes, engine:v6}} }"));
    }
}