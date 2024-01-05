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
            } else {
                return Signal.DO_NOTHING;
            }
        }
        return null;
    }

    public Signal increaseTemp() {
        if (currentTemp < requestedTemp && currentTemp != 0 && requestedTemp != null) {
            currentTemp += 0.5;
            if (currentTemp < requestedTemp) {
                return Signal.INCREASE_TEMP;
            } else {
                return Signal.DO_NOTHING;
            }
        }
        return null;
    }

    public Signal decreaseTemp() {
        if (currentTemp > requestedTemp && currentTemp != 0 && requestedTemp != null) {
            currentTemp -= 0.5;
            if (currentTemp > requestedTemp) {
                return Signal.DECREASE_TEMP;
            } else {
                return Signal.DO_NOTHING;
            }
        } else if (currentTemp == 2 || currentTemp == 8) {
            return Signal.DO_NOTHING;
        }
        return null;
    }

    public Signal alarmActivation() {
        if (currentTemp > requestedTemp && currentTemp != 0 && requestedTemp != null) {
            if (currentTemp < 2 || currentTemp > 8) {
                return Signal.RING_ALARM;
            } else {
                return Signal.DO_NOTHING;
            }
        }
        return null;
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
        // Case 1: Increase Temperature
        Signal increaseSignal = controller.requestChange(7);
        controller.PrintSignals();
        System.out.println("Current Temperature: " + controller.getCurrentTemp());
        System.out.println("Requested Temperature: " + controller.getRequestedTemp());
        System.out.println();
        // Case 2: Decrease Temperature
        Signal decreaseSignal = controller.requestChange(3);
        controller.PrintSignals();
        System.out.println("Current Temperature: " + controller.getCurrentTemp());
        System.out.println("Requested Temperature: " + controller.getRequestedTemp());
    }
}
