package rental;

public class RentalPriceCalculator {

	public double calculatePricePerDay(int driverAge, int driverLicenceAge, boolean driverAccidentsLastYear, int carClass, boolean highSeason) {
		double rentalPrice;

		isDriverSuitable(carClass, driverAge, driverLicenceAge);

		rentalPrice = driverAge;
		rentalPrice = ageBasedPrice(rentalPrice, carClass, driverAge, highSeason);
		rentalPrice = licenceBasedPrice(rentalPrice, driverLicenceAge);
		rentalPrice = accidentBasedPrice(driverAccidentsLastYear, driverAge, rentalPrice);
		rentalPrice = isMaximumPrice(rentalPrice);

		return rentalPrice;
	}

	public void isDriverSuitable(int carClass, int driverAge, int driverLicenceAge) {
		if (driverAge < 18) {
			throw new IllegalArgumentException("Driver too young - cannot quote the calculatePrice");
		}
		if (driverAge <= 21 && carClass > 2) {
			throw new UnsupportedOperationException("Drivers 21 y/o or less can only rent Class 1 vehicles");
		}
		if (driverLicenceAge < 1) {
			throw new IllegalArgumentException("Driver must hold driving licence at least for one year. Can not rent a car!");
		}
	}

	public double ageBasedPrice(double rentalPrice, int carClass, int driverAge, boolean highSeason) {
		if (carClass >=4 && driverAge <= 25 && highSeason) { return rentalPrice * 2; }
		return rentalPrice;
	}

	public double licenceBasedPrice(double rentalPrice, int driverLicenceAge) {
		if (driverLicenceAge < 3) { return rentalPrice * 1.3; }
		return rentalPrice;
	}

	private double isMaximumPrice(double rentalPrice) {
		if (rentalPrice > 1000) {
			return 1000.00;
		}
		return rentalPrice;
	}

	private double accidentBasedPrice(boolean driverAccidentsLastYear, int driverAge, double rentalPrice) {
		if (driverAccidentsLastYear && driverAge < 30) {
			rentalPrice += 15;
			return rentalPrice;
		}
		return rentalPrice;
	}
}