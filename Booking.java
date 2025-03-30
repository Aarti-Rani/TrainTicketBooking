import java.util.*;

class Train {
    private int train_num;
    private String name;
    private String source;
    private String destination;
    private String class_of_booking;
    private int available_seats;

    public Train(String name, String source, String destination, String class_of_booking) {
        this.train_num = generateTrainNum();
        this.name = name;
        this.source = source;
        this.destination = destination;
        this.class_of_booking = class_of_booking;
        this.available_seats = 10;
    }

    private int generateTrainNum() {
        Random rand = new Random();
        return 1000 + rand.nextInt(9000);
    }

    public int getTrainNum() {
        return train_num;
    }

    public int getAvailableSeats() {
        return available_seats;
    }

    public void setAvailableSeats(int available_seats) {
        this.available_seats = available_seats;
    }
}

class Passenger {
    private String passenger_name;
    private int age;
    private int seat_required;
    private long pnr_num;

    public Passenger(String passenger_name, int age, int seat_required) {
        this.passenger_name = passenger_name;
        this.age = age;
        this.seat_required = seat_required;
        this.pnr_num = generatePnr();
    }

    public long getPnrNum() {
        return pnr_num;
    }

    private long generatePnr() {
        Random rand = new Random();
        return 1000 + (long) (rand.nextDouble() * 9000000);
    }
}

public class Booking {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String proceed = "Yes";

        while (proceed.equalsIgnoreCase("Yes")) {
            System.out.println("Enter the train name: ");
            String train_name = sc.nextLine();

            System.out.println("Enter Source: ");
            String source = sc.nextLine();

            System.out.println("Enter Destination: ");
            String destination = sc.nextLine();

            System.out.println("Enter the class of booking: ");
            String class_of_booking = sc.nextLine();

            Train train = new Train(train_name, source, destination, class_of_booking);
            System.out.println("Train Number: " + train.getTrainNum());
            System.out.println("Available Seats: " + train.getAvailableSeats());

            System.out.println("Enter passenger name: ");
            String passenger_name = sc.nextLine();

            System.out.println("Enter age: ");
            int age = Integer.parseInt(sc.nextLine());  // Using nextLine() to avoid buffer issues

            System.out.println("Enter the number of seats required: ");
            int seats_required = Integer.parseInt(sc.nextLine());  // Using nextLine() to avoid buffer issues

            Passenger passenger = new Passenger(passenger_name, age, seats_required);

            if (seats_required <= train.getAvailableSeats()) {
                System.out.println("Ticket Booked! PNR number: " + passenger.getPnrNum());
                train.setAvailableSeats(train.getAvailableSeats() - seats_required);
            } else {
                System.out.println("Seats are not available!");
            }

            // **Extra nextLine() to fix skipping issue in Windows PowerShell**
            System.out.println("Do you want to book another ticket? (Yes/No): ");
            proceed = sc.nextLine().trim();  

            // Ensuring it properly waits for input
            while (!proceed.equalsIgnoreCase("Yes") && !proceed.equalsIgnoreCase("No")) {
                System.out.println("Invalid input. Please enter 'Yes' or 'No': ");
                proceed = sc.nextLine().trim();
            }
        }

        System.out.println("Happy Journey! Thank you.");
        sc.close();
    }
}
