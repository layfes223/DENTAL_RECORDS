import java.util.Scanner;

/**
 * Stores dental records for a family of a max size of 6.
 * Users enter their names and dental data.
 * Users can then print records, extract teeth, or calculate root canal indices.
 * @author Layla
 */

public class DENTAL_RECORDS {

    // DECLARE CONSTANTS
    private static final Scanner keyboard = new Scanner(System.in);
    private static final int MAX_FAMILY_SIZE = 6;
    private static final int MAX_STRING_SIZE = 8;

    /**
     * The main method for the Tooth Records Program
     * Gathers family size and
     * runs methods to store dental records and display menu options
     * @param args
     */
    // DECLARE main METHOD
    public static void main(String[] args) {
        int familySize;

        System.out.println("Welcome to the Floridian Tooth Records");

        //GATHERING FAMILY SIZE

        System.out.println("Please enter the number of people in the family: ");
        familySize = keyboard.nextInt();

        while (familySize < 1 || familySize > MAX_FAMILY_SIZE){

            System.out.print("Invalid number of people, try again: ");
            familySize = keyboard.nextInt();

        } // END OF while LOOP - FAMILY SIZE

        keyboard.nextLine();

        // CREATING ARRAYS
        String[] familyNames = new String[familySize];

        char[][][] familyDentalRecords = gatheringDentalData(familyNames);

        displayMenu(familyNames, familyDentalRecords);

    } // END OF main METHOD

    /**
     * Creates a three-dimensional array to store the family's dental record values
     * as they are inputted by the user while checking for correct values
     * @param familyNames
     * @return the three-dimensional array that includes the
     * person's name and their teeth types as a string
     */
    // DECLARE gatheringDentalData METHOD
    private static char[][][] gatheringDentalData(String[] familyNames){
        String familyNameInput;

        char[][][] teethDataArray = new char[familyNames.length][][];

        // for LOOP TO COLLECT DATA FOR EACH FAMILY MEMBER
        for (int index = 0; index < familyNames.length; index++) {

            teethDataArray[index] = new char[2][];

            System.out.print("\nEnter name for family member "
                    + (index + 1) + ": ");

            familyNameInput = keyboard.nextLine();

            familyNames[index] = familyNameInput;

            for (int rowIndex = 0; rowIndex < 2; rowIndex++) {

                String rowName =
                        (rowIndex == 0) ? "uppers" : "lowers";

                System.out.print("\nPlease enter the "
                        + rowName + " for "
                        + familyNames[index] + ": ");

                String teethTypes =
                        keyboard.nextLine().toUpperCase();

                while (teethTypes.length() > MAX_STRING_SIZE || !teethTypes.matches("[IBM]+")) {

                    if (teethTypes.length() > MAX_STRING_SIZE) {
                        System.out.println("Too many teeth, try again:");
                    }
                    else {
                        System.out.println("Invalid teeth types, try again:");
                    }

                    teethTypes = keyboard.nextLine().toUpperCase();
                }

                teethDataArray[index][rowIndex] =
                        new char[teethTypes.length()];

                for (int columnIndex = 0;
                     columnIndex < teethTypes.length();
                     columnIndex++) {

                    teethDataArray[index][rowIndex][columnIndex]
                            = teethTypes.charAt(columnIndex);
                }
            }
        }

        return teethDataArray;
    } // END OF gatheringDentalData METHOD


    /**
     * Method for displaying menu options to user running processes
     * This method runs until user selects the "exit" choice
     * @param familyNames
     * @param familyDentalRecords
     */
    // DECLARING displayMenu METHOD
    private static void displayMenu(String[] familyNames, char[][][] familyDentalRecords){
        char userChoice;

        System.out.println("(P)rint, (E)xtract, (R)oot, e(X)it");
        userChoice = keyboard.next().charAt(0);

        userChoice = Character.toUpperCase(userChoice);

        do {
            if (userChoice == 'P'){
                for (int index = 0; index < familyNames.length; index++){
                    System.out.print("\n" + familyNames[index]);

                    System.out.print("\nUppers: ");
                    for (int columnIndex = 0; columnIndex < familyDentalRecords[index][0].length; columnIndex++){

                        System.out.print((columnIndex + 1) + ":" + familyDentalRecords[index][0][columnIndex]);

                    }

                    System.out.print("\nLowers: ");
                    for (int columnIndex = 0; columnIndex < familyDentalRecords[index][1].length; columnIndex++){

                        System.out.print((columnIndex + 1) + ":" + familyDentalRecords[index][1][columnIndex]);

                    }
                }

                System.out.println("\n(P)rint, (E)xtract, (R)oot, e(X)it");

            }
            else if (userChoice == 'E'){

                keyboard.nextLine();

                // FIND FAMILY MEMBER
                String memberName;
                int memberIndex = -1;

                System.out.print("Which family member: ");
                memberName = keyboard.nextLine();

                while (memberIndex == -1) {

                    for (int i = 0; i < familyNames.length; i++) {

                        if (familyNames[i].equalsIgnoreCase(memberName)) {
                            memberIndex = i;
                        }
                    }

                    if (memberIndex == -1) {

                        System.out.print(
                                "Invalid family member, try again: ");

                        memberName = keyboard.nextLine();
                    }
                }

                // FIND UPPER OR LOWER
                System.out.print(
                        "Which tooth layer (U)pper or (L)ower: ");

                char rowChoice =
                        keyboard.nextLine().toUpperCase().charAt(0);

                while (rowChoice != 'U' && rowChoice != 'L') {

                    System.out.print(
                            "Invalid layer, try again: ");

                    rowChoice =
                            keyboard.nextLine().toUpperCase().charAt(0);
                }

                int row;

                if (rowChoice == 'U') {
                    row = 0;
                }
                else {
                    row = 1;
                }

                // TOOTH NUMBER
                System.out.print("Which tooth number: ");

                int toothNumber = keyboard.nextInt();

                while (toothNumber < 1 ||
                        toothNumber >
                                familyDentalRecords[memberIndex][row].length) {

                    System.out.print(
                            "Invalid tooth number, try again: ");

                    toothNumber = keyboard.nextInt();
                }

                // CHECK IF MISSING
                while (familyDentalRecords
                        [memberIndex]
                        [row]
                        [toothNumber - 1] == 'X') {

                    System.out.print(
                            "Missing tooth, try again: ");

                    toothNumber = keyboard.nextInt();

                    while (toothNumber < 1 ||
                            toothNumber >
                                    familyDentalRecords[memberIndex][row].length) {

                        System.out.print(
                                "Invalid tooth number, try again: ");

                        toothNumber = keyboard.nextInt();
                    }
                }

                System.out.println("Tooth extracted.");

                System.out.println("\n(P)rint, (E)xtract, (R)oot, e(X)it");


            }
            else if (userChoice == 'R'){

                int I = 0;
                int B = 0;
                int M = 0;

                // COUNTING ALL TEETH
                for (int person = 0; person < familyDentalRecords.length; person++) {

                    for (int row = 0; row < familyDentalRecords[person].length; row++) {

                        for (int tooth = 0; tooth < familyDentalRecords[person][row].length; tooth++) {

                            char currentTooth = familyDentalRecords[person][row][tooth];

                            if (currentTooth == 'I') {
                                I++;
                            }
                            else if (currentTooth == 'B') {
                                B++;
                            }
                            else if (currentTooth == 'M') {
                                M++;
                            }
                        }
                    }
                }

                // Handle case where I = 0
                if (I == 0) {

                    System.out.println("NO root canal indices.");

                }
                else {
                    double discriminant = (B * B) - (4 * I * (-M));

                    if (discriminant < 0) {

                        System.out.println("No root canal indices.");

                    }
                    else {

                        double root1 = (-B + Math.sqrt(discriminant)) / (2.0 * I);

                        double root2 = (-B - Math.sqrt(discriminant)) / (2.0 * I);

                        System.out.printf("One root canal at = %.2f%n", root1);

                        System.out.printf("Another root canal at = %.2f%n", root2);
                    }
                }

                System.out.println("\n(P)rint, (E)xtract, (R)oot, e(X)it");

            }

            else{

                System.out.println("Invalid menu option, try again");

            }

            userChoice = keyboard.next().toUpperCase().charAt(0);

        } while (userChoice != 'X');

        System.out.println("Exiting the Floridian Tooth Records :-)");

    }// END OF displayMenu METHOD


}// END OF DENTAL_RECORDS CLASS
