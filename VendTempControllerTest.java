import java.util.Scanner;

public class VendTempControllerTest {
    public static VendTempController controller = new VendTempController();

    public static void promptEnter() {
        System.out.print("\nPress \"ENTER\" to continue...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }

    public static void main(String[] args) {
        System.out.println("--------------- VEND TEMP CONTROLLER ---------------");
        int choice = 1;
        Scanner sc = new Scanner(System.in);

        while (choice != 5) {
            try {
                System.out.print("\nEnter a valid choice:\n" +
                        "1. Increment Temperature.\n" +
                        "2. Decrement Temperature.\n" +
                        "3. Alarm Activation.\n" +
                        "4. Display Current Temperature.\n" +
                        "5. Exit.\n");

                choice = sc.nextInt();

                switch (choice) {
                    case 1: {
                        try {
                            VendTempController.Signal tempInc = controller.IncreaseTemp();
                            if (tempInc == VendTempController.Signal.INCREASE_TEMP) {
                                System.out.println("Temperature increased to " + controller.getCurrentTemp() + " Celsius");
                            }
                        } catch (Exception e) {
                            System.out.println("Invalid Input: " + e.getMessage());
                        }
                        break;
                    }

                    case 2: {
                        try {
                            VendTempController.Signal tempDec = controller.DecreaseTemp();
                            if (tempDec == VendTempController.Signal.DECREASE_TEMP) {
                                System.out.println("Temperature decreased to " + controller.getCurrentTemp() + " Celsius");
                            }
                        } catch (Exception e) {
                            System.out.println("Invalid Input: " + e.getMessage());
                        }
                        break;
                    }
                    case 3: {
                        try {
                            VendTempController.Signal alarm = controller.alarmActivation();
                            if (alarm == VendTempController.Signal.RING_ALARM) {
                                System.out.println("Alarm is Activated. Setting the Temperature to 2 Celsius.");
                            }
                        } catch (Exception e) {
                            System.out.println("Invalid Input: " + e.getMessage());
                        }
                        break;
                    }
                    case 4: {
                        try {
                            System.out.println("Current Temperature: " + controller.getCurrentTemp() + " Celsius.\n");
                        } catch (Exception e) {
                            System.out.println("Invalid Input: " + e.getMessage());
                        }
                        break;
                    }
                    case 5: {
                        break;
                    }
                    default: {
                        System.out.println("Invalid Selection.");
                        promptEnter();
                    }
                }
            } catch (Exception e) {
                System.out.println("Invalid Input: " + e.getMessage());
                sc.nextLine(); 
            }
        }
        System.out.print("Exiting...");
    }
}
