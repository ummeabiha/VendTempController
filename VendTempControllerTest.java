public class VendTempControllerTest {
    public static void main(String[] args) {
        testSetInitialTemp();
        testRequestChange();
        testRingAlarmCase();
        testIncreaseTempCase();
        testDecreaseTempCase();
        testDoNothingCase();}

    private static void testSetInitialTemp() {
        VendTempController controller = new VendTempController();
        System.out.println("Testing setInitialTemp:");
        try {
            // Valid initial temperature
            controller.setInitialTemp(5);
            System.out.println("Initial Temperature Set: " + controller.getCurrentTemp());
            // Invalid initial temperature (out of range)
            controller.setInitialTemp(10);
            System.out.println("Initial Temperature Set (Invalid): " + controller.getCurrentTemp());
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
            System.out.println("Signal for Increasing Temperature: " + signal);
            // Valid change request (decrease temperature)
            signal = controller.requestChange(3);
            System.out.println("Signal for Decreasing Temperature: " + signal);
            // Valid change request (alarm signal)
            Signal alarmSignal = controller.requestChange(0);
            if (alarmSignal == null) {
                System.out.println("Signal for Ring Alarm: RING_ALARM");
            }
            // No change request (same temperature)
            signal = controller.requestChange(5);
            System.out.println("Signal for no change in temperature: " + signal);
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    private static void testRingAlarmCase() {
        VendTempController controller = new VendTempController();
        System.out.println("\nTesting Ring Alarm Case:");
        try {
            Signal alarmSignal = controller.requestChange(0);
            controller.PrintSignals();
            System.out.println("Current Temperature: " + controller.getCurrentTemp());

            if (alarmSignal == Signal.RING_ALARM && controller.getRequestedTemp() == null) {
                System.out.println(
                        "Requested Temperature: Temperature exceeds the allowable range (2-8). The alarm is now ringing.");
            } else {
                System.out.println("Requested Temperature: " + controller.getRequestedTemp());
            }
            System.out.println();
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    private static void testIncreaseTempCase() {
        VendTempController controller = new VendTempController();
        System.out.println("Testing Increase Temperature Case:");
        try {
            controller.setInitialTemp(5);
            Signal increaseSignal = controller.requestChange(7);
            controller.PrintSignals();
            System.out.println("Current Temperature: " + controller.getCurrentTemp());

            if (increaseSignal == Signal.INCREASE_TEMP) {
                System.out.println("Requested Temperature: Temperature is increasing. New temperature is "
                        + controller.getRequestedTemp());
            } else {
                System.out.println("Requested Temperature: " + controller.getRequestedTemp());
            }
            System.out.println();
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    private static void testDecreaseTempCase() {
        VendTempController controller = new VendTempController();
        System.out.println("Testing Decrease Temperature Case:");
        try {
            controller.setInitialTemp(5);
            Signal decreaseSignal = controller.requestChange(3);
            controller.PrintSignals();
            System.out.println("Current Temperature: " + controller.getCurrentTemp());

            if (decreaseSignal == Signal.RING_ALARM && controller.getRequestedTemp() == null) {
                System.out.println(
                        "Requested Temperature: Temperature is below the allowable range (2-8). The alarm is now ringing.");
            } else {
                System.out.println("Requested Temperature: Temperature is decreasing. New temperature is "
                        + controller.getRequestedTemp());
            }
            System.out.println();
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    private static void testDoNothingCase() {
        VendTempController controller = new VendTempController();
        System.out.println("Testing Do Nothing Case:");
        try {
            controller.setInitialTemp(4);
            Signal doNothingSignal = controller.requestChange(4);
            controller.PrintSignals();
            System.out.println("Current Temperature: " + controller.getCurrentTemp());

            if (doNothingSignal == Signal.RING_ALARM && controller.getRequestedTemp() == null) {
                System.out.println(
                        "Requested Temperature: Temperature is below the allowable range (2-8). The alarm is now ringing.");
            } else {
                System.out.println("Requested Temperature: " + controller.getRequestedTemp());
            }
            System.out.println();
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}
