

import java.util.List;
import java.util.Scanner;

import controller.CarHelper;
import model.Car;

public class StartProgram {

		static Scanner in = new Scanner(System.in);
		static CarHelper carHelper = new CarHelper();

		private static void addACar() {
			System.out.print("Enter a make: ");
			String make = in.nextLine();
			System.out.print("Enter an model: ");
			String model = in.nextLine();
			System.out.print("Enter an year: ");
			int year = in.nextInt();
			Car toAdd = new Car(make, model, year);
			carHelper.insertCar(toAdd);
		}

		private static void deleteACar() {	
			System.out.print("Enter the make to delete: ");
			String make = in.nextLine();
			System.out.print("Enter the model to delete: ");
			String model = in.nextLine();
			System.out.print("Enter the year to delete: ");
			int year = in.nextInt();
			Car toDelete = new Car(make, model, year);
			carHelper.deleteCar(toDelete);
		}

		private static void editACar() {
			System.out.println("How would you like to search? ");
			System.out.println("1 : Search by Make");
			System.out.println("2 : Search by Model");
			System.out.println("3 : Search by Year");
			int searchBy = in.nextInt();
			in.nextLine();
			List<Car> foundItems = null;
			if (searchBy == 1) {
				System.out.print("Enter the car make: ");
				String carMake = in.nextLine();
				foundItems = carHelper.searchForCarByMake(carMake);
			} else if (searchBy == 2) {
				System.out.print("Enter the car model: ");
				String carModel = in.nextLine();
				foundItems = carHelper.searchForCarByModel(carModel);
			} else {
				System.out.print("Enter the car year: ");
				int carYear = in.nextInt();
				foundItems = carHelper.searchForCarByYear(carYear);
			}

			if (!foundItems.isEmpty()) {
				System.out.println("Found Results.");
				for (Car c : foundItems) {
					System.out.println(c.getId() + " : " + c.toString());
				}
				System.out.print("Which ID to edit: ");
				int idToEdit = in.nextInt();

				Car toEdit = carHelper.searchForCarById(idToEdit);
				System.out.println("Retrieved " + toEdit.getMake() + " " + toEdit.getModel() + " " + toEdit.getYear());
				System.out.println("1 : Update Make");
				System.out.println("2 : Update Model");
				System.out.println("3 : Update Year");
				int update = in.nextInt();
				in.nextLine();

				if (update == 1) {
					System.out.print("New Make: ");
					String newMake = in.nextLine();
					toEdit.setMake(newMake);
				} else if (update == 2) {
					System.out.print("New Model: ");
					String newModel = in.nextLine();
					toEdit.setModel(newModel);
				} else if (update == 3) {
					System.out.print("New Year: ");
					int newYear = in.nextInt();
					toEdit.setYear(newYear);
				}

				carHelper.updateCar(toEdit);

			} else {
				System.out.println("---- No results found");
			}
			
		}

		private static List<Car> searchForCar() {
			System.out.println("How would you like to search? ");
			System.out.println("1 : Search by Make");
			System.out.println("2 : Search by Model");
			System.out.println("3 : Search by Year");
			int searchBy = in.nextInt();
			in.nextLine();
			List<Car> foundItems = null;
			if (searchBy == 1) {
				System.out.print("Enter the car make: ");
				String make = in.nextLine();
				foundItems = carHelper.searchForCarByMake(make);
			} else if (searchBy == 2) {
					System.out.print("Enter the car model: ");
					String model = in.nextLine();
					foundItems = carHelper.searchForCarByModel(model);				
			} else {
				System.out.print("Enter the car year: ");
				int year = in.nextInt();
				foundItems = carHelper.searchForCarByYear(year);
			}
			return foundItems;
		}

		public static void main(String[] args) {
			runMenu();

		}

		public static void runMenu() {
			boolean goAgain = true;
			System.out.println("--- Welcome to the Car dealer list! ---");
			while (goAgain) {
				System.out.println("*  Select an option:");
				System.out.println("*  1 -- Add a car");
				System.out.println("*  2 -- Edit a car");
				System.out.println("*  3 -- Delete a car");
				System.out.println("*  4 -- View the list");
				System.out.println("*  5 -- Exit the program");
				System.out.print("*  Your selection: ");
				int selection = in.nextInt();
				in.nextLine();

				if (selection == 1) {
					addACar();
				} else if (selection == 2) {
					editACar();
				} else if (selection == 3) {
					deleteACar();
				} else if (selection == 4) {
					viewTheCarList();
				} else {
					carHelper.cleanUp();
					System.out.println("   Goodbye!   ");
					goAgain = false;
				}

			}

		}

		private static void viewTheCarList() {
			List<Car> allCars = carHelper.showAllItems();
			for (Car car : allCars) {
				System.out.println(car.returnCarDetails());
			}
			

		}

	}