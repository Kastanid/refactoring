package rental;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RentalPriceCalculatorTest {
    private RentalPriceCalculator rentalPriceCalculator;

    @BeforeEach
    public void setUp() {
         this.rentalPriceCalculator = new RentalPriceCalculator();
    }

    @Test
    void calculatePricePerDay() {
        assertEquals(50, rentalPriceCalculator.calculatePricePerDay(50,5,false,5,true));
        assertEquals(50, rentalPriceCalculator.calculatePricePerDay(25,5,false,5,true));
    }

    @Test
    void ageBasedPrice() {
        assertEquals(50, rentalPriceCalculator.ageBasedPrice(25,5,20,true));
        assertEquals(25, rentalPriceCalculator.ageBasedPrice(25,5,50,true));
        assertEquals(25, rentalPriceCalculator.ageBasedPrice(25,3,20,true));
        assertEquals(25, rentalPriceCalculator.ageBasedPrice(25,5,20,false));
    }

    @Test
    void licenceBasedPrice() {
        assertEquals(25*1.3, rentalPriceCalculator.licenceBasedPrice(25, 1));
        assertEquals(25, rentalPriceCalculator.licenceBasedPrice(25, 3));
        assertEquals(25, rentalPriceCalculator.licenceBasedPrice(25, 50));
    }

    @Test
    void isMaximumPrice() {
        assertEquals(900, rentalPriceCalculator.isMaximumPrice(900));
        assertEquals(1, rentalPriceCalculator.isMaximumPrice(1));
        assertEquals(1000, rentalPriceCalculator.isMaximumPrice(1500));
    }

    @Test
    void accidentBasedPrice() {
        assertEquals(50, rentalPriceCalculator.accidentBasedPrice(false, 21, 50));
        assertEquals(65, rentalPriceCalculator.accidentBasedPrice(true, 21, 50));
        assertEquals(50, rentalPriceCalculator.accidentBasedPrice(true, 51, 50));
        assertEquals(50, rentalPriceCalculator.accidentBasedPrice(false, 51, 50));
    }
}