import java.util.Date;
import java.util.Scanner;

public class Ab {


    private static String[] transactionHistory = new String[100];
    private static int transactionCount = 0;
    private static double balance = 300.0;
    private static final int PIN = 4286;



    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Date currentDateAndTime = new Date();

        System.out.println(" ----- KU SO DHAWOW ADEGA EVC PLUS ----- ");
        System.out.print(" ku soo gal *770# : ");
        String code = input.nextLine();

        if (code.startsWith("*") && code.endsWith("#")) {
            System.out.print("Fadlan gali pinkaaga: ");
            int password = input.nextInt();
            input.nextLine();

            if (password == PIN) {
                boolean running = true;
                while (running) {
                    displayMainMenu();
                    int option = input.nextInt();
                    input.nextLine();

                    switch (option) {
                        case 0:
                            running = false;
                            System.out.println("Mahadsanid macmiil, ");
                            break;
                        case 1:
                            checkBalance();
                            break;
                        case 2:
                            airtimeServices(input, currentDateAndTime);
                            break;
                        case 3:
                            billPayment(input);
                            break;
                        case 4:
                            transferMoney(input, currentDateAndTime);
                            break;
                        case 5:
                            transactionReport();
                            break;
                        case 6:
                            bankServices(input);
                            break;
                        case 7:
                            accountManagement(input);
                            break;
                        case 8:
                            taajService(input);
                            break;
                        case 9:
                            payBill(input);
                            break;
                        default:
                            System.out.println("Fadlan dooro number sax ah.");
                    }
                }
            } else {
                System.out.println("Password khaldan!");
            }
        } else {
            System.out.println("Fadlan geli code-ka sax ah sida tusaalaha.");
        }
    }



    private static void displayMainMenu() {
        System.out.println("\nEVPLus Menu:");
        System.out.println("1. Itus Haraagaga");
        System.out.println("2. Karka ku hadalka");
        System.out.println("3. Bixi bill");
        System.out.println("4. U wareeji EVCplus");
        System.out.println("5. Warbixin kooban");
        System.out.println("6. Salam bank");
        System.out.println("7. Maareynta");
        System.out.println("8. TAAJ");
        System.out.println("9. Bill payment");
        System.out.println("0. Ka bax (Exit)");
        System.out.print("Fadlan dooro option: ");
    }



    private static void checkBalance() {
        System.out.println("Haraagaga waa $" + balance);
    }



    private static void airtimeServices(Scanner input, Date date) {
        System.out.println("Kaarka hadalka:");
        System.out.println("1. Ku shuba Airtime");
        System.out.println("2. Ugu dhub airtime");
        System.out.println("3. Ku shuba internet");
        System.out.print("Dooro: ");
        int choice = input.nextInt();
        input.nextLine();



        switch (choice) {
            case 1:
                topUpAirtime(input, date, "ku shubatay", "");
                break;
            case 2:
                System.out.print("Fadlan geli numberka: ");
                String number = input.nextLine();
                topUpAirtime(input, date, "ugu shubtay", number);
                break;
            case 3:
                System.out.print("Fadlan geli numberka: ");
                String internetNumber = input.nextLine();
                topUpAirtime(input, date, "internet u shubtay", internetNumber);
                break;
            default:
                System.out.println("Fadlan dooro number sax ah.");
        }
    }



    private static void topUpAirtime(Scanner input, Date date, String action, String recipient) {
        System.out.print("Fadlan geli lacagta: ");
        double amount = input.nextDouble();
        input.nextLine();

        System.out.println("Ma hubtaa inaad $" + amount + " " + action + " " +
                (recipient.isEmpty() ? "" : ("lambarka " + recipient)) +
                "? \n1.haa \n2.maya");
        int confirmation = input.nextInt();
        input.nextLine();

        if (confirmation == 1) {
            if (amount > balance) {
                System.out.println("Xisaabtaada kugu ma filna.");
            } else {
                balance -= amount;
                String transaction = "EVC PLUS - Waxaad " + action + " $" + amount +
                        (recipient.isEmpty() ? "" : " " + recipient) +
                        " - Taariikh: " + date +
                        " - Haraaga cusub: $" + balance;
                recordTransaction(transaction);
                System.out.println(transaction);
            }
        } else {
            System.out.println("Mahadsanid.");
        }
    }




    private static void billPayment(Scanner input) {
        System.out.println("Bixi bill:");
        System.out.print("Geli magaca shirkadda billka: ");
        String company = input.nextLine();
        System.out.print("Geli lacagta billka: ");
        double amount = input.nextDouble();
        input.nextLine();

        if (amount > balance) {
            System.out.println("Haraagaaga kugu ma filna bixinta billka.");
        } else if (amount <= 0) {
            System.out.println("Fadlan gali lacag sax ah.");
        } else {
            balance -= amount;
            String transaction = "Billka shirkadda " + company +
                    " si guul leh ayaa loo bixiyey: $" + amount +
                    " - Haraaga cusub: $" + balance;
            recordTransaction(transaction);
            System.out.println(transaction);
        }
    }



    private static void transferMoney(Scanner input, Date date) {
        System.out.print("Fadlan geli numberka: ");
        String number = input.nextLine();

        if (number.equals("614428664")) {
            System.out.println("Lambarka aad dooratay waa la xanibay.");
            return;
        }

        System.out.print("Fadlan geli lacagta: ");
        double amount = input.nextDouble();
        input.nextLine();

        if (amount <= 0) {
            System.out.println("Fadlan gali lacag sax ah.");
            return;
        }

        System.out.println("Ma hubtaa inaad $" + amount + " u wareejisay " +
                number + "?\n1.haa\n2.maya");
        int confirmation = input.nextInt();
        input.nextLine();

        if (confirmation == 1) {
            if (amount > balance) {
                System.out.println("Haraaga xisaabtaada kugu ma filna.");
            } else {
                balance -= amount;
                String transaction = "[-EVC PLUS-] $" + amount +
                        " ayaad u wareejisay " + number +
                        " - Taariikh: " + date +
                        " - Haraagaagu waa $" + balance;
                recordTransaction(transaction);
                System.out.println(transaction);
            }
        } else {
            System.out.println("Mahadsanid.");
        }
    }



    private static void transactionReport() {
        System.out.println("\nWarbixin Kooban (Finally " + transactionCount + " transactions):");
        for (int i = 0; i < transactionCount; i++) {
            System.out.println((i+1) + ". " + transactionHistory[i]);
        }
        System.out.println();
    }




    private static void bankServices(Scanner input) {
        System.out.println("Salaam Bank:");
        System.out.println("1. Itus Haraagaaga");
        System.out.println("2. Lacag dhigasho");
        System.out.println("3. Lacag qaadasho");
        System.out.println("4. Ka Wareeji EVCPLUS");
        System.out.println("5. Hubi wareejin account");
        System.out.println("6. Ka Bax");
        System.out.print("Dooro: ");
        int choice = input.nextInt();
        input.nextLine();

        switch (choice) {
            case 1:
                checkBalance();
                break;
            case 2:
                bankDeposit(input);
                break;
            case 3:
                bankWithdrawal(input);
                break;
            case 4:
                transferToEVC(input);
                break;
            case 5:
                accountTransfer(input);
                break;
            case 6:
                System.out.println("Waad ka baxday Salaam Bank.");
                break;
            default:
                System.out.println("Doorasho khaldan.");
        }
    }



    private static void bankDeposit(Scanner input) {
        System.out.print("Geli lacagta aad dhigeyso: ");
        double amount = input.nextDouble();
        input.nextLine();

        if (amount <= 0) {
            System.out.println("Fadlan geli lacag sax ah.");
        } else {
            balance += amount;
            String transaction = "Waad dhigatay $" + amount +
                    ". Haraagaagu hadda waa $" + balance;
            recordTransaction(transaction);
            System.out.println(transaction);
        }
    }



    private static void bankWithdrawal(Scanner input) {
        System.out.print("Geli lacagta aad qaadaneyso: ");
        double amount = input.nextDouble();
        input.nextLine();

        if (amount <= 0) {
            System.out.println("Fadlan geli lacag sax ah.");
        } else if (amount > balance) {
            System.out.println("Haraagaagu kugu filan ma aha.");
        } else {
            balance -= amount;
            String transaction = "Waad qaadatay $" + amount +
                    ". Haraagaagu hadda waa $" + balance;
            recordTransaction(transaction);
            System.out.println(transaction);
        }
    }



    private static void transferToEVC(Scanner input) {
        System.out.print("Geli lambarka EVC Plus: ");
        String evcNumber = input.nextLine();

        System.out.print("Geli lacagta: ");
        double amount = input.nextDouble();
        input.nextLine();

        if (amount <= 0) {
            System.out.println("Fadlan geli lacag sax ah.");
        } else if (amount > balance) {
            System.out.println("Haraagaagu kugu filan ma aha.");
        } else {
            balance -= amount;
            String transaction = "Waxaad ka wareejisay $" + amount +
                    " lambarka EVC Plus: " + evcNumber +
                    " - Haraaga cusub: $" + balance;
            recordTransaction(transaction);
            System.out.println(transaction);
        }
    }





    private static void accountTransfer(Scanner input) {
        System.out.print("Geli account numberka: ");
        String account = input.nextLine();

        System.out.print("Geli lacagta: ");
        double amount = input.nextDouble();
        input.nextLine();

        if (amount <= 0) {
            System.out.println("Fadlan geli lacag sax ah.");
        } else if (amount > balance) {
            System.out.println("Haraagaagu kugu filan ma aha.");
        } else {
            balance -= amount;
            String transaction = "Waxaad ka wareejisay $" + amount +
                    " account-ka: " + account +
                    " - Haraaga cusub: $" + balance;
            recordTransaction(transaction);
            System.out.println(transaction);
        }
    }





    private static void accountManagement(Scanner input) {
        System.out.println("Maareynta:");
        System.out.println("1. Badal pin");
        System.out.println("2. Xannib lacag bixinta");
        System.out.println("3. Dib ugu laabo");
        System.out.print("Dooro: ");
        int choice = input.nextInt();
        input.nextLine();

        switch (choice) {
            case 1:
                System.out.print("Geli pin cusub: ");
                int newPin = input.nextInt();
                input.nextLine();
                System.out.println("Pin-ka waa la badalay.");
                break;
            case 2:
                System.out.print("Gali lambarka aad xanibayso: ");
                String blockedNumber = input.nextLine();
                System.out.println("Lambarka " + blockedNumber +
                        " waa la xanibay (simulation).");
                break;
            case 3:
                System.out.println("Waxaa dib loogu laabtay menu-ga weyn.");
                break;
            default:
                System.out.println("Fadlan dooro number sax ah.");
        }
    }




    private static void taajService(Scanner input) {
        System.out.println("TAAJ:");
        System.out.println("1. Dir Lacag");
        System.out.println("2. Ka Qaado Lacag");
        System.out.print("Dooro: ");
        int choice = input.nextInt();
        input.nextLine();

        switch (choice) {
            case 1:
                System.out.print("Geli magaca qofka: ");
                String name = input.nextLine();
                System.out.print("Geli lacagta: ");
                double amount = input.nextDouble();
                input.nextLine();

                if (amount <= 0) {
                    System.out.println("Fadlan gali lacag sax ah.");
                } else if (amount > balance) {
                    System.out.println("Haraagaagu kugu filan ma aha.");
                } else {
                    balance -= amount;
                    String transaction = "Waxaad u dirtay $" + amount +
                            " qofka: " + name +
                            " - Haraaga cusub: $" + balance;
                    recordTransaction(transaction);
                    System.out.println(transaction);
                }
                break;
            case 2:
                System.out.print("Geli lacagta aad qaadaneyso: ");
                double receivedAmount = input.nextDouble();
                input.nextLine();

                if (receivedAmount <= 0) {
                    System.out.println("Fadlan gali lacag sax ah.");
                } else {
                    balance += receivedAmount;
                    String transaction = "Waxaad ka qaadatay $" + receivedAmount +
                            " - Haraaga cusub: $" + balance;
                    recordTransaction(transaction);
                    System.out.println(transaction);
                }
                break;
            default:
                System.out.println("Fadlan dooro number sax ah.");
        }
    }




    private static void payBill(Scanner input) {
        System.out.println("Bill Payment:");
        System.out.println("1. Bixi koronto");
        System.out.println("2. Bixi biyaha");
        System.out.println("3. Bixi internet");
        System.out.print("Dooro: ");
        int choice = input.nextInt();
        input.nextLine();

        System.out.print("Geli lacagta: ");
        double amount = input.nextDouble();
        input.nextLine();

        if (amount > balance) {
            System.out.println("Haraagaagu kugu filan ma aha.");
        } else if (amount <= 0) {
            System.out.println("Fadlan gali lacag sax ah.");
        } else {
            balance -= amount;
            String transaction = "Waxaad bixisay $" + amount +
                    " adeegga bill payment" +
                    " - Haraaga cusub: $" + balance;
            recordTransaction(transaction);
            System.out.println(transaction);
        }
    }




    private static void recordTransaction(String transaction) {
        if (transactionCount < transactionHistory.length) {
            transactionHistory[transactionCount] = transaction;
            transactionCount++;
        } else {
      \
        
            for (int i = 0; i < transactionHistory.length - 1; i++) {
                transactionHistory[i] = transactionHistory[i + 1];
            }
            transactionHistory[transactionHistory.length - 1] = transaction;
        }
    }
}
