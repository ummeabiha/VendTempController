import java.util.Scanner;

public class VendTempController {
    private Integer requestedTemp;
    private Integer currentTemp;
    public static final int MAX = 8;
    public static final int MIN = 2;

    public enum Signal {
        INCREASE_TEMP, DECREASE_TEMP, DO_NOTHING, RING_ALARM
    };

    public VendTempController() {
        this.requestedTemp = null;
        this.currentTemp = 2;
    }

    private boolean inRange(int tempVal) {
        return (tempVal >= MIN && tempVal <= MAX);
    }

    private boolean invariant() {
        return (this.requestedTemp == null || inRange(this.requestedTemp)) &&
                (inRange(currentTemp));
    }

    public Signal IncreaseTemp() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the desired temperature: ");
        int requestedTemp = scanner.nextInt();

        if (inRange(requestedTemp)) {
            if (currentTemp == requestedTemp) {
                System.out.println("Current Temperature is same as desired temperture.");
                return Signal.DO_NOTHING;
            } else {
                while (currentTemp != requestedTemp) {
                    if (currentTemp < requestedTemp) {
                        currentTemp = requestedTemp;
                    } else {
                        System.out.println("Current Temperature is greater than desired temperture. (Invalid Choice)");
                        return Signal.DO_NOTHING;
                    }
                    if (!invariant()) {
                        System.out.println("Temperature out of valid range. Resetting to 2 Celsius.");
                        currentTemp = 2;
                        return Signal.RING_ALARM;
                    }
                }
                return Signal.INCREASE_TEMP;
            }
        } else {
            System.out.println("Invalid desired temperature. Please enter a value between 2 and 8 Celsius.");
            return Signal.DO_NOTHING;
        }
    }

    public Signal DecreaseTemp() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the desired temperature: ");
        int requestedTemp = scanner.nextInt();

        if (inRange(requestedTemp)) {
            if (currentTemp == requestedTemp) {
                System.out.println("Current Temperature is same as desired temperture.");
                return Signal.DO_NOTHING;
            } else {
                while (currentTemp != requestedTemp) {
                    if (currentTemp > requestedTemp) {
                        currentTemp = requestedTemp;

                    } else {
                        System.out.println("Current Temperature is less than desired temperture. (Invalid Choice)");
                        return Signal.DO_NOTHING;
                    }
                    if (!invariant()) {
                        System.out.println("Temperature out of valid range. Resetting to 2 Celsius.");
                        currentTemp = 2;
                        return Signal.RING_ALARM;
                    }
                }
                return Signal.DECREASE_TEMP;
            }
        } else {
            System.out.println("Invalid desired temperature. Please enter a value between 2 and 8 Celsius.");
            return Signal.DO_NOTHING;
        }
    }

    public Signal alarmActivation() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the desired temperature: ");
        int requestedTemp = scanner.nextInt();

        if (inRange(requestedTemp) && inRange(this.getCurrentTemp())) {
            System.out.println("Vending Machine is in a valid temperature range (2-8). Hence, Alarm not ringing");
            return Signal.DO_NOTHING;
        } else {
            currentTemp = 2;
            return Signal.RING_ALARM;
        }
    }

    public int getCurrentTemp() {
        return currentTemp;
    }

    public int getRequestedTemp() {
        return this.requestedTemp;
    }

    public Signal requestChange(int tempIn) {
        Signal tempsignal = Signal.DO_NOTHING;
        if (inRange(tempIn)) {
            this.requestedTemp = tempIn;
            if (tempIn > this.currentTemp) {
                tempsignal = Signal.INCREASE_TEMP;
            }
            if (tempIn < this.currentTemp) {
                tempsignal = Signal.DECREASE_TEMP;
            }
            if (tempIn == 0) {
                tempsignal = Signal.RING_ALARM;
            }
            return tempsignal;
        } else {
            System.out.print("Invalid Temperature Range. Provide a valid range between (2-8) Celsius\n");
            return Signal.DO_NOTHING;
        }
    }
}
