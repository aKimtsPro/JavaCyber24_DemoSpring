package be.tftic.java.spring.api.models.forms;

import be.tftic.java.spring.domain.models.entities.Car;

public record CarUpdateForm(
        int mileage,
        String color,
        int doors,
        int power,
        double dailyRate
) {
    public Car toEntity() {
        Car car = new Car();
        car.setDoors(doors);
        car.setColor(color);
        car.setMileage(mileage);
        car.setPower(power);
        car.setDailyRate(dailyRate);
        return car;
    }
}
