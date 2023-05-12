package javafundamentals;

import java.sql.Timestamp;

public class CurrentMilliMain {
    public static void main(String[] args) {
        long currentMilli = System.currentTimeMillis();
        Timestamp currentMilliTimestamp = new Timestamp(currentMilli);

        System.out.println("currentMilli = " + currentMilli); // currentMilli = 1683710282473
        System.out.println("currentMilliTimestamp = " + currentMilliTimestamp); // currentMilliTimestamp = 2023-05-10 18:18:02.473

        System.out.println("============================");

        long divide1000 = currentMilli / 1000;
        Timestamp divide1000Timestamp = new Timestamp(divide1000);

        System.out.println("divide1000 = " + divide1000); // divide1000 = 1683710282
        System.out.println("divide1000Timestamp = " + divide1000Timestamp); // divide1000Timestamp = 1970-01-20 20:41:50.282

        /**
         * 1000 을 나누면 70년대 됨.
         * */

    }
}
