import java.lang.ModuleLayer.Controller;

public class VendTempController {
    private int currentTemp;
    private Integer requestedTemp;

    public VendTempController() {
        currentTemp = 2;
        requestedTemp = null;
    }

    private boolean inRange(int val) {
        return val >= 2 && val <= 8;
    }

    public void setInitialTemp(int initialTemp) {
        if (inRange(initialTemp) && currentTemp == 2) {
            currentTemp = initialTemp;
        }
    }

    public Signal requestChange(int initialTemp) {
        if (inRange(initialTemp) && currentTemp != 0) {
            requestedTemp = initialTemp;
            if (initialTemp > currentTemp) {
                return Signal.INCREASE_TEMP;
            } else if (initialTemp < currentTemp) {
                return Signal.DECREASE_TEMP;
            } else if (initialTemp < 2 || initialTemp > 8) {
                return Signal.RING_ALARM;
            } else {
                return Signal.DO_NOTHING;
            }
        }
        return null;
    }

    public Signal increaseTemp() {
        if (requestedTemp != null) {
            if (currentTemp < requestedTemp && currentTemp != 0) {
                currentTemp += 0.5;
                if (currentTemp < requestedTemp) {
                    return Signal.INCREASE_TEMP;
                } else {
                    return Signal.DO_NOTHING;
                }
            } else {
                return Signal.DO_NOTHING;
            }
        }
        return null;
    }

    public Signal decreaseTemp() {
        if (requestedTemp != null) {
            if (currentTemp > requestedTemp && currentTemp != 0) {
                currentTemp -= 0.5;
                if (currentTemp > requestedTemp) {
                    return Signal.DECREASE_TEMP;
                } else {
                    return Signal.DO_NOTHING;
                }
            } else {
                return Signal.DO_NOTHING;
            }
        }
        return null;
    }

    public Signal alarmActivation() {
        if (requestedTemp == null
                || (currentTemp > requestedTemp && currentTemp != 0 && (currentTemp < 2 || currentTemp > 8))) {
            return Signal.RING_ALARM;
        } else {
            return Signal.DO_NOTHING;
        }
    }

    public int getCurrentTemp() {
        return currentTemp;
    }

    public Integer getRequestedTemp() {
        return requestedTemp;
    }

    public void PrintSignals() {
        Signal increaseSignal = increaseTemp();
        Signal decreaseSignal = decreaseTemp();
        Signal alarmSignal = alarmActivation();
        System.out.println("Increase Signal: " + increaseSignal);
        System.out.println("Decrease Signal: " + decreaseSignal);
        System.out.println("Alarm Signal: " + alarmSignal);
    }

    public static void main(String[] args) {
        VendTempController controller = new VendTempController();
        controller.setInitialTemp(5);

        // Case 1: Ring Alarm
        Signal alarmSignal = controller.requestChange(0);
        controller.PrintSignals();
        System.out.println("Current Temperature: " + controller.getCurrentTemp());

        if (controller.alarmActivation() == Signal.RING_ALARM && controller.getRequestedTemp() == null) {
            System.out.println(
                    "Requested Temperature: Temperature exceeds the allowable range (2-8). The alarm is now ringing.");

        } else {
            System.out.println("Requested Temperature: " + controller.getRequestedTemp());
        }
        System.out.println();

        // Case 2: Increase Temperature
        controller.setInitialTemp(3);
        Signal increaseSignal = controller.requestChange(7);
        controller.PrintSignals();
        System.out.println("Current Temperature: " + controller.getCurrentTemp());

        if (controller.alarmActivation() == Signal.RING_ALARM && controller.getRequestedTemp() == null) {
            System.out.println(
                    "Requested Temperature: Temperature exceeds the allowable range (2-8). The alarm is now ringing.");

        } else {
            System.out.println("Requested Temperature: " + controller.getRequestedTemp());
        }
        System.out.println();

        // Case 3: Decrease Temperature
        Signal decreaseSignal = controller.requestChange(3);
        controller.PrintSignals();
        System.out.println("Current Temperature: " + controller.getCurrentTemp());
        if (controller.alarmActivation() == Signal.RING_ALARM && controller.getRequestedTemp() == null) {
            System.out.println(
                    "Requested Temperature: Temperature is below the allowable range (2-8). The alarm is now ringing.");

        } else {
            System.out.println("Requested Temperature: " + controller.getRequestedTemp());
        }
        System.out.println();

        // Case 4: DO Nothing
        Signal doNotingSignal = controller.requestChange(4);
        controller.PrintSignals();

        System.out.println("Current Temperature: " + controller.getCurrentTemp());
        if (controller.alarmActivation() == Signal.RING_ALARM && controller.getRequestedTemp() == null) {
            System.out.println(
                    "Requested Temperature: Temperature is below the allowable range (2-8). The alarm is now ringing.");
        } else {
            System.out.println("Requested Temperature: " + controller.getRequestedTemp());
        }
        System.out.println();
    }
}
