import java.util.Scanner;
//=========================================Begin
public class FloridaDentistry {
    //-------------------------------------Establish constants

    private static final Scanner keyboard = new Scanner(System.in);
    private static final int MAX_FAMILY_NUM = 6;
    private static final int MAX_UPPER_AND_LOWERS = 8;
    private static final int UPPER_VS_LOWER_ARRAY = 2;

    //-------------------------------------Begin Main Method
    public static void main(String[] args) {

        int numOfPeopleInFamily;
        String[] familyNames;
        char[][][] teethArray = new char[0][0][0];
        char userMenuInput;
        char incisor = 'I';
        char biscupids = 'B';
        char missingTeeth = 'M';

        //--------------------------------Welcome message
        System.out.println("Welcome to the Floridian Tooth Records");
        System.out.println("______________________________________");


        //--------------------------------Begin prompting for information

        System.out.print("Please enter the number of people in the family : ");
        numOfPeopleInFamily = keyboard.nextInt();

        //-------------------------------------------------Collect Num of Family Members
        while ((numOfPeopleInFamily < 0) || (numOfPeopleInFamily > MAX_FAMILY_NUM)) {
            System.out.print("Invalid number of people, try again             : ");
            numOfPeopleInFamily = keyboard.nextInt();
        }

        //--------------------------------------------------Store Family Names
        familyNames = new String[numOfPeopleInFamily];

        for (int nameIndex = 0; nameIndex < numOfPeopleInFamily; nameIndex++) {
            System.out.print("Please enter the name of family member " + (nameIndex + 1) + "        : ");
            familyNames[nameIndex] = keyboard.next();
        }

        teethArray = new char[numOfPeopleInFamily][][];
        for(int nameIndex = 0; nameIndex < numOfPeopleInFamily; nameIndex++) {

            teethArray[nameIndex] = new char[UPPER_VS_LOWER_ARRAY][];
            for (int rowIndex = 0; rowIndex < UPPER_VS_LOWER_ARRAY; rowIndex++) {

                if (rowIndex == 0) {
                    System.out.print("Please enter the uppers for " + familyNames[nameIndex] + "    : ");
                } else {
                    System.out.print("Please enter the lowers for " + familyNames[nameIndex] + "    : ");
                }

                String teethString = keyboard.next();

                teethArray[nameIndex][rowIndex] = new char[teethString.length()];

                for (int toothIndex = 0; toothIndex < teethString.length(); toothIndex++) {
                    char toothCharacter = teethString.charAt(toothIndex);
                    toothCharacter = Character.toUpperCase(toothCharacter);
                    teethArray[nameIndex][rowIndex][toothIndex] = toothCharacter;
                }
            }
    }
        System.out.println();

        //--------------------------------Create Switch cases for P, E, R, X and user menu

        do {
            System.out.print("(P)rint, (E)xtract, (R)oot, e(X)it               : ");
            userMenuInput = keyboard.next().charAt(0);
            userMenuInput = Character.toUpperCase(userMenuInput);

            if ((userMenuInput != 'P') && (userMenuInput != 'E') && (userMenuInput != 'R') && (userMenuInput != 'X')) {
                do {
                    System.out.print("Invalid menu option, try again     :");
                    userMenuInput = keyboard.next().charAt(0);
                    userMenuInput = Character.toUpperCase(userMenuInput);
                } while ((userMenuInput != 'P') && (userMenuInput != 'E') && (userMenuInput != 'R') && (userMenuInput != 'X'));
            }


            switch (userMenuInput) {
                case 'P':
                    menuPrinting(familyNames, teethArray, numOfPeopleInFamily);
                    System.out.println();
                    break;

                case 'E':
                    toothExtraction();
                    System.out.println();
                    break;

                case 'R':
                    rootCanalCalculations(teethArray, numOfPeopleInFamily);
                    System.out.println();
                    break;

                case 'X':
                    exitMessage();
                    break;

                default:
                    System.out.print("Unexpected circumstances; please contact developer.");
                    break;
                    }
            }
            while (userMenuInput != 'X') ;
        }


    //------------------------------------Method for Printing
    public static void menuPrinting(String[] familyNames, char[][][] teethArray, int numOfPeopleInFamily) {

        for (int nameIndex = 0; nameIndex < numOfPeopleInFamily; nameIndex++) {
            System.out.println();
            System.out.println(familyNames[nameIndex]);

            for (int rowIndex = 0; rowIndex < UPPER_VS_LOWER_ARRAY; rowIndex++) {
                if (rowIndex == 0) {
                    System.out.print("    Uppers:    ");
                    for (int toothIndex = 0; toothIndex < teethArray[nameIndex][rowIndex].length; toothIndex++) {
                        System.out.print((toothIndex + 1) + ":" + (teethArray[nameIndex][rowIndex][toothIndex]) + "   ");
                    }
                } else if ( rowIndex == 1 ) {
                    System.out.println();
                    System.out.print("    Lowers:    ");
                    for (int toothIndex = 0; toothIndex < teethArray[nameIndex][rowIndex].length; toothIndex++) {
                        System.out.print((toothIndex + 1) + ":" + (teethArray[nameIndex][rowIndex][toothIndex]) + "   ");
                    }
                }
            }
        }
        System.out.println();
        return;
    }

    //------------------------------------Method for Tooth Extraction Process
    public static void toothExtraction() {

        return;
    }
    //------------------------------------Method for Root Canal Indices
    public static void rootCanalCalculations(char[][][] teethArray, int numOfPeopleInFamily) {

        int totalIncisors = 0;
        int totalBicuspids = 0;
        int totalMissing = 0;

        double rootOne;
        double rootTwo;

        for (int nameIndex = 0; nameIndex < numOfPeopleInFamily; nameIndex++) {
            for (int rowIndex = 0; rowIndex < UPPER_VS_LOWER_ARRAY; rowIndex++) {
                for (int toothIndex = 0; toothIndex < teethArray[nameIndex][rowIndex].length; toothIndex++) {
                    if (teethArray[nameIndex][rowIndex][toothIndex] == 'I') {
                        totalIncisors++;
                    } else if (teethArray[nameIndex][rowIndex][toothIndex] == 'B') {
                        totalBicuspids++;
                    } else if (teethArray[nameIndex][rowIndex][toothIndex] == 'M') {
                        totalMissing++;
                    }
                }
            }
        }
        double squareRootOfQuadForm = Math.sqrt((totalBicuspids * totalBicuspids) - (4 * totalIncisors *totalMissing));
        rootOne = ((-totalBicuspids) + squareRootOfQuadForm) / (2 * totalIncisors);
        rootTwo = ((-totalBicuspids) - squareRootOfQuadForm) / (2 * totalIncisors);

        System.out.println("One root canal at " + rootOne + ".");
        System.out.println("Another root canal at " + rootTwo + ".");
        return;
    }
    //------------------------------------Method for exit message
    public static void exitMessage() {
        System.out.println("Exiting the Floridian Tooth Records  :-) ");
    }
}
