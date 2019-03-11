
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.EmptyStackException;
import java.util.Random;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        // want to generate 1000 customers
        int cid[] = new int[1001];
        try {
            PrintWriter writer = new PrintWriter("Customer.txt", "UTF-8");
            writer.println("INSERT\n" +
                    "into Customer(SSN, custName, address, phoneNumber)\n" +
                    "values ");

            for (int i = 0; i < 999; i++) {
                writer.println("('" + generateSSN() + "', '" + generateName() + "', '"
                        + generateAddress() + "', '" + generatePhone() + "'),");
                cid[i+1] = i+1;
            }

            writer.println("('" + generateSSN() + "', '" + generateName() + "', '"
                    + generateAddress() + "', '" + generatePhone() + "');");
            cid[1000] = 1000;

            writer.close();
        }

        catch (Exception e){
            System.out.println("Unable to complete customers");
        }

        // generate 1000 credit cards
        String cards[] = new String[1000];

        try {
            PrintWriter writer2 = new PrintWriter("CreditCard.txt", "UTF-8");
            writer2.println("INSERT\n" +
                    "into CreditCard(cnum, ctype, climit, balance, active)\n" +
                    "values ");
            for (int i = 0; i < 999; i++) {
                String x = generateCard();
                writer2.println("('" + x + "', '" + generateType() + "', " + generateLimit() + ", " +
                        generateBalance() + ", false),");
                cards[i] = x;
            }
            String x = generateCard();
            writer2.println("('" + x + "', '" + generateType() + "', " + generateLimit() + ", " +
                    generateBalance() + ", false);");
            cards[999] = x;
            writer2.close();

        }
        catch (Exception e2) {
                System.out.println("Unable to complete credit cards");

        }

        int vid[] = new int[101];
        // generate 100 vendors
        try {
            PrintWriter writer3 = new PrintWriter("Vendor.txt", "UTF-8");
            writer3.println("INSERT\n" +
                    "into Vendor(vname, vlocation)\n" +
                    "values ");
            for (int i = 0; i < 99; i ++) {
                writer3.println("('" + generateName() + "', '" + generateAddress() + "'),");
                vid[i+1] = i+1;
            }
            writer3.println("('" + generateName() + "', '" + generateAddress() + "');");
            vid[100] = 100;

            writer3.close();

        }
        catch (Exception e3) {
            System.out.println("Unable to complete vendor");
        }



        int start = 0; // indexing through credit card array

        // adding ownership data
        String owners[] = new String[1001]; // indexed by cid, showing a cardnum for customer
        try {
            PrintWriter writer4 = new PrintWriter("Ownership.txt", "UTF-8");
            writer4.println("INSERT\n" +
                    "into Ownership(cid, cnum, active)\n" +
                    "values ");
            for (int i = 1; i < 1001; i ++) {
                int numcardsowned = (int) (Math.random() * 3 + 1);
                int end = start + numcardsowned;
                if (end > 1000) {
                    break;
                }
                for (int j = start; j < end; j++) {
                    if ((j + 1 == 1000) || (j + 2 == 1000) || (j + 3 == 1000)) {
                        writer4.println("(" + i + ", '" + cards[j] + "', true);");
                        owners[i+1] = cards[j];
                        break;
                    }
                    else {
                        writer4.println("(" + i + ", '" + cards[j] + "', true),");
                        owners[i+1] = cards[j];
                    }
                }
                start = end;
            }
            writer4.close();
        }
        catch (Exception e4) {
            System.out.println("Unable to complete ownership");
        }

        // creating 2000 transactions
        try {
            PrintWriter writer5 = new PrintWriter("Transaction.txt", "UTF-8");
            writer5.println("INSERT\n" +
                    "into Transaction(tdate, cid,cnum, vid, amount)\n" +
                    "values ");
            for (int i = 0; i < 2000; i ++) { // 2000 transactions
                int randcid =  (int) (Math.random() * 1000 + 1);
                while (owners[randcid] == null) {
                    randcid = (int) (Math.random() * 1000 + 1);
                }
                int randvid = (int) (Math.random()*100 + 1);
                int randamount = (int) (Math.random()*500 + 1);
                if (owners[randcid] != null) {
                    writer5.println("('" + generateDate() + "', " + randcid + ", '" + owners[randcid] + "', " + randvid +
                                    ", " +
                                    randamount + "),");
                }
            }
            int randcid =  (int) (Math.random() * 1000 + 1);
            while (owners[randcid] == null) {
                randcid = (int) (Math.random() * 1000 + 1);
            }
            int randvid = (int) (Math.random()*vid.length + 1);
            int randamount = (int) (Math.random()*500 + 1);
            writer5.println("('" + generateDate() + "', " + randcid + ", '" + owners[randcid] + "', " + randvid + ", " +
            		randamount + ");");


            writer5.close();
        }
        catch (Exception e5) {
            System.out.println("Unable to complete transactions");
        }

    }



    public static String generateSSN() {
        String returnedString = "";
        for (int j = 0; j < 9; j++) {
            int random = (int)(Math.random()* 9 + 1);
            String numberAsString = Integer.toString(random);
            returnedString += numberAsString;
        }
        return returnedString;
    }

    public static String generateName() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        String generatedString = buffer.toString();

        return generatedString;
    }

    public static String generateAddress() {
        String returnedString = "";
        int random = (int)(Math.random()*1000+1);
        String numberAsString = Integer.toString(random);
        returnedString += numberAsString + " " + generateName() + " " + "Street";
        return returnedString;
    }

    public static String generatePhone() {
        String returnedString = "";
        for (int j = 0; j < 10; j++) {
            int random = (int)(Math.random()* 9 + 1);
            String numberAsString = Integer.toString(random);
            returnedString += numberAsString;
        }
        return returnedString;
    }

    public static String generateCard() {
        String returnedString = "";
        for (int j = 0; j < 12; j++) {
            int random = (int)(Math.random()* 9 + 1);
            String numberAsString = Integer.toString(random);
            returnedString += numberAsString;
        }
        return returnedString;
    }


    public static String generateType() {
        String[] types = new String[] {"VISA", "MC", "AMERICAN EXPRESS", "DISCOVER"};
        int rnd = new Random().nextInt(types.length);
        return types[rnd];
    }

    public static String generateLimit() {
        String returnedString = "";
        int random = (int)(Math.random()*1000+500);
        String numberAsString = Integer.toString(random);
        returnedString += numberAsString;
        return returnedString;
    }

    public static String generateBalance() {
        String returnedString = "";
        int random = (int)(Math.random()*500+1);
        String numberAsString = Integer.toString(random);
        returnedString += numberAsString;
        return returnedString;
    }

    public static String generateDate() {
        String returnedString = "";
        int randomyear = (int) (Math.random()*2020+2000);
        int randommonth = (int) (Math.random()*12+1);
        int randomdate = (int) (Math.random()+28+1);
        returnedString += randomyear + "-" + randommonth + "-" + randomdate;
        return returnedString;
    }


}