public class VendTempControllerTest {
    public static void main(String[] args) {
        testSetInitialTemp();
        testRequestChange();
        testIncreaseTemp();
        testDecreaseTemp();
        testAlarmActivation();
        testGetCurrentTemp();
        testGetRequestedTemp();
    }

    private static void testSetInitialTemp() {
        VendTempController controller = new VendTempController();
        System.out.println("Testing setInitialTemp:");
        try {
            // Valid initial temperature
            controller.setInitialTemp(5);
            System.out.println("Current Temperature after setting initial temp to 5: " + controller.getCurrentTemp());
            // Invalid initial temperature (out of range)
            controller.setInitialTemp(10);
            System.out
                    .println("Current Temperature after setting invalid initial temp: " + controller.getCurrentTemp());
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    private static void testRequestChange() {
        VendTempController controller = new VendTempController();
        System.out.println("\nTesting requestChange:");
        try {
            // Valid change request (increase temperature)
            controller.setInitialTemp(5);
            Signal signal = controller.requestChange(7);
            System.out.println("Signal for increasing temperature: " + signal);
            // Valid change request (decrease temperature)
            signal = controller.requestChange(3);
            System.out.println("Signal for decreasing temperature: " + signal);
            // No change request (same temperature)
            signal = controller.requestChange(5);
            System.out.println("Signal for no change in temperature: " + signal);
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    private static void testIncreaseTemp() {
        VendTempController controller = new VendTempController();
        System.out.println("\nTesting increaseTemp:");
        try {
            // Increase temperature
            controller.setInitialTemp(5);
            controller.requestChange(7);
            Signal signal = controller.increaseTemp();
            System.out.println("Signal for increasing temperature: " + signal);
            // No increase needed (already at requested temperature)
            signal = controller.increaseTemp();
            System.out.println("Signal for no increase in temperature: " + signal);
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    private static void testDecreaseTemp() {
        VendTempController controller = new VendTempController();
        System.out.println("\nTesting decreaseTemp:");
        try {
            // Decrease temperature
            controller.setInitialTemp(5);
            controller.requestChange(3);
            Signal signal = controller.decreaseTemp();
            System.out.println("Signal for decreasing temperature: " + signal);
            // No decrease needed (already at requested temperature)
            signal = controller.decreaseTemp();
            System.out.println("Signal for no decrease in temperature: " + signal);
            // No decrease needed (at minimum temperature)
            controller.setInitialTemp(2);
            signal = controller.decreaseTemp();
            System.out.println("Signal for no decrease at minimum temperature: " + signal);
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    private static void testAlarmActivation() {
        VendTempController controller = new VendTempController();
        System.out.println("\nTesting alarmActivation:");
        try {
            // Activate alarm (temperature out of range)
            controller.setInitialTemp(10);
            controller.requestChange(8);
            Signal signal = controller.alarmActivation();
            System.out.println("Signal for activating alarm: " + signal);
            // No alarm activation (within range)
            controller.setInitialTemp(5);
            controller.requestChange(7);
            signal = controller.alarmActivation();
            System.out.println("Signal for no alarm activation: " + signal);
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    private static void testGetCurrentTemp() {
        VendTempController controller = new VendTempController();
        System.out.println("\nTesting getCurrentTemp:");
        try {
            // Get current temperature
            int currentTemp = controller.getCurrentTemp();
            System.out.println("Current Temperature: " + currentTemp);
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    private static void testGetRequestedTemp() {
        VendTempController controller = new VendTempController();
        System.out.println("\nTesting getRequestedTemp:");
        try {
            // Get requested temperature
            Integer requestedTemp = controller.getRequestedTemp();
            System.out.println("Requested Temperature: " + requestedTemp);
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}