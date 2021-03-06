import java.util.Scanner;
import static java.lang.Integer.parseInt;

@SuppressWarnings("all")

public class Date {
    //declaring instance variables
    private String month;
    private int day;
    private int year;

    //initializing no-argument constructor
    public Date() {
        month = "January";                                              //given no inputs, setting instance variables to arbitrary values
        day = 1;
        year = 2000;
    }

    //Initializing constructor
    public Date(String strDate) {
        int monthTemp, dayTemp, yearTemp;
        String[] date = strDate.split("/", 3);              //splits the input string at the "/" into a max of 3 strings
        monthTemp = parseInt(date[0]);                                  //month is declared as string, no need to use parseInt
        dayTemp = parseInt(date[1]);                                    //using parseInt to convert the day from string to int
        yearTemp = parseInt(date[2]);                                   //using parseInt to convert the year from string to int

        setDate(monthTemp, dayTemp, yearTemp);
    }

    //initializing constructor
    public Date(int monthInt, int day, int year) {
        setDate(monthInt, day, year);
    }

    //initializing constructor
    public Date(String monthString, int day, int year) {
        setDate(monthString, day, year);
    }

    //initializing constructor
    public Date(int year) {
        setDate(1, 1, year);
    }

    //initializing constructor
    public Date(Date aDate) {
        if (aDate == null) {                                             //Not a real date.
            System.out.println("Fatal Error.");                          //return "Fatal Error" and end program
            System.exit(0);
        }
        month = aDate.month;
        day = aDate.day;
        year = aDate.year;
    }

    //initializing mutator
    public void setDate(int monthInt, int day, int year) {
        if (dateOK(monthInt, day, year)) {                              //if input is a real date
            this.month = monthString(monthInt);
            this.day = day;
            this.year = year;
        }
        else {
            System.out.println("Fatal Error");                          //otherwise, return "Fatal Error" and end program
            System.exit(0);
        }
    }

    //initializing mutator
    public void setDate(String monthString, int day, int year) {
        if (dateOK(monthString, day, year)) {                           //if input is a real date
            this.month = monthString;
            this.day = day;
            this.year = year;
        }
        else {
            System.out.println("Fatal Error");                          //otherwise, return "Fatal Error" and end program
            System.exit(0);
        }
    }

    //initializing mutator
    public void setDate(int year) {
        setDate(1, 1, year);
    }

    //initializing mutator
    public void setYear(int year) {
        if ( (year < 1000) || (year > 9999) ) {
            System.out.println("Fatal Error");
            System.exit(0);
        }
        else
            this.year = year;
    }

    //initializing mutator
    public void setMonth(int monthNumber) {
        if ((monthNumber <= 0) || (monthNumber > 12)) {
            System.out.println("Fatal Error");
            System.exit(0);
        }
        else
            month = monthString(monthNumber);
    }

    //initializing mutator
    public void setDay(int day) {
        if ((day <= 0) || (day > 31)) {
            System.out.println("Fatal Error");
            System.exit(0);
        }
        else
            this.day = day;
    }

    //initializing accessor for month
    public int getMonth( ) {
        if (month.equals("January"))
            return 1;
        else if (month.equals("February"))
            return 2;
        else if (month.equalsIgnoreCase("March"))
            return 3;
        else if (month.equalsIgnoreCase("April"))
            return 4;
        else if (month.equalsIgnoreCase("May"))
            return 5;
        else if (month.equals("June"))
            return 6;
        else if (month.equalsIgnoreCase("July"))
            return 7;
        else if (month.equalsIgnoreCase("August"))
            return 8;
        else if (month.equalsIgnoreCase("September"))
            return 9;
        else if (month.equalsIgnoreCase("October"))
            return 10;
        else if (month.equals("November"))
            return 11;
        else if (month.equals("December"))
            return 12;
        else {
            System.out.println("Fatal Error");
            System.exit(0);
            return 0;                                                   //Needed to keep the compiler happy
        }
    }

    //initializing day accessor
    public int getDay( ) {
        return day;
    }

    //initializing year accessor
    public int getYear( ) {
        return year;
    }

    //method taking in month day and year and returning them as a string
    public String toString( ) {
        return (month + "/" + day + "/" + year);
    }

    //method to compare strings in Java
    public boolean equals(Date otherDate) {
        if (otherDate == null)
            return false;
        else
            return ( (month.equals(otherDate.month)) &&
                    (day == otherDate.day) && (year == otherDate.year) );
    }

    //method to check if a date precedes another date
    public boolean precedes(Date otherDate) {
        return ( (year < otherDate.year) ||
                (year == otherDate.year && getMonth( ) < otherDate.getMonth( )) ||
                (year == otherDate.year && month.equals(otherDate.month)
                        && day < otherDate.day) );
    }

    //method to take in and read user input
    public void readInput( ) {
        boolean tryAgain = true;
        Scanner keyboard = new Scanner(System.in);
        while (tryAgain) {
            System.out.println("(mm/dd/yyyy)");
            //System.out.println("Do not use a comma.");
            try {                                               //using try catch statement to avoid errors if different variable type is put in int
                String dateInput = keyboard.nextLine();         //reads full line of player input
                if (dateInput.equalsIgnoreCase("quit")){   //if user types 'quit'
                    System.out.println("Game ended.");
                    System.exit(0);                       //game will automatically exited
                }
                Date birthDate = new Date(dateInput);           //create a Date object from the string input by player
                int monthInput = birthDate.getMonth();          //use accessor to get the month from new Date object
                int dayInput = birthDate.getDay();              //use accessor to get the day from new Date Object
                int yearInput = birthDate.getYear();            //use accessor to get the year from new Date object
                if (dateOK(monthInput, dayInput, yearInput) ) { //if the date is a valid date
                    setDate(monthInput, dayInput, yearInput);
                    tryAgain = false;                           //there is no need to try again
                }
                else                                            //otherwise, request a valid date input
                    System.out.println("Illegal date. Reenter input.");
            }
            catch (Exception e){                                //if an error appears, ask for a valid date
                System.out.println("Enter a valid date");
            }
        }
    }

    //method to check if Date is a vaild date
    private boolean dateOK(int monthInt, int dayInt, int yearInt) {
        return ( (monthInt >= 1) && (monthInt <= 12) &&
                (dayInt >= 1) && (dayInt <= 31) &&
                (yearInt >= 1000) && (yearInt <= 9999) );
    }

    //method to check if Date is a vaild date
    private boolean dateOK(String monthString, int dayInt, int yearInt) {
        return ( monthOK(monthString) &&
                (dayInt >= 1) && (dayInt <= 31) &&
                (yearInt >= 1000) && (yearInt <= 9999) );
    }

    //method to check that month is a valid month
    private boolean monthOK(String month) {
        return (month.equals("January") || month.equals("February") ||
                month.equals("March") || month.equals("April") ||
                month.equals("May") || month.equals("June") ||
                month.equals("July") || month.equals("August") ||
                month.equals("September") || month.equals("October") ||
                month.equals("November") || month.equals("December") );
    }

    //method to convert input month integer into a string of month name
    private String monthString(int monthNumber) {
        switch (monthNumber) {
            case 1:
                return "January";
            case 2:
                return "February";
            case 3:
                return "March";
            case 4:
                return "April";
            case 5:
                return "May";
            case 6:
                return "June";
            case 7:
                return "July";
            case 8:
                return "August";
            case 9:
                return "September";
            case 10:
                return "October";
            case 11:
                return "November";
            case 12:
                return "December";
            default:
                System.out.println("Fatal Error");
                System.exit(0);
                return "Error"; //to keep the compiler happy
        }
    }
}
