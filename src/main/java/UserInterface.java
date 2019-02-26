import java.util.Scanner;

public class UserInterface {

    private Scanner console = new Scanner(System.in);
    private consoleColors color = new consoleColors();
    private Logic inputs = new Logic();

    public UserInterface(){

        String userInput = "";

        while(!userInput.equals("/quit")) {

            System.out.println("Welcome to the Database program");
            color.printTxtBlue("1. Select data\n")
                    .printTxtYellow("2. Update data\n")
                    .printTxtRed("3. Delete data\n")
                    .printTxtPurple("4. Insert data\n")
                    .printTxtBlack("Type /quit to exit\n")
                    .print(false);

            System.out.print("Please choose: ");
            userInput = console.next();
            color.clearTxtBuffer();

                switch (userInput) {
                    case "1":
                        color.printTxtBlue(inputs.selectRetrieve().toString()).print(true);
                        break;
                    case "2":
                        update();
                        break;
                    case "3":
                        delete();
                        break;
                    case "4":
                        insert();
                        break;
                    default:
                        System.out.println("\nNot a valid input");
                        break;
                }
            System.out.println();
            color.clearTxtBuffer();
            }
    }

    private void update(){
        System.out.println(inputs.selectRetrieve());

        color.printTxtYellow("Select the Id for the product you wish to update: ").print(false);
        int itemID = console.nextInt();
        color.clearTxtBuffer();
        if(!inputs.validIds(itemID)){
            System.out.println("Invalid Id");
            update();
            return;
        }

        console.nextLine();                 //For empty \n token from nextInt

        color.printTxtYellow("New Product Name: ").print(false);
        String newName = console.nextLine();
        color.clearTxtBuffer();

        color.printTxtYellow("New Price (0-1000); ").print(false);
        int newPrice = console.nextInt();
        color.clearTxtBuffer();

        color.printTxtYellow("New Location (1-3): ").print(false);
        int newLocation = console.nextInt();
        color.clearTxtBuffer();

        color.printTxtYellow("New Shelf (0-1000): ").print(false);
        int newShelf = console.nextInt();
        color.clearTxtBuffer();

        try {
            inputs.dbWrite(newName, newPrice, newLocation, newShelf, itemID);
        } catch(IllegalArgumentException e){
            color.printTxtYellow(e.getMessage()).print(true);
            return;
        }
        System.out.println("Successfully Updated Item");

        color.clearTxtBuffer();
    }

    private void delete(){
        System.out.println("\n\n" + inputs.selectRetrieve());
        color.printTxtRed("\nPlease select the item you want to delete by its Id: ").print(false);
        color.clearTxtBuffer();

        int itemIDInput = console.nextInt();

        try {
            inputs.deleteChoice(itemIDInput);
        } catch (IllegalArgumentException e){
            color.printTxtRed(e.getMessage()).print(true);
            return;
        }
        System.out.println("Successfully Deleted Item");

        color.clearTxtBuffer();
    }

    private void insert(){
        console.nextLine();                 //For empty \n token from nextInt

        color.printTxtPurple("\nProduct Name: ").print(false);
        String nameInput = console.nextLine();
        color.clearTxtBuffer();

        color.printTxtPurple("Product Price (0-1000): ").print(false);
        int priceInput = console.nextInt();
        color.clearTxtBuffer();

        color.printTxtPurple("Product Location (1-3): ").print(false);
        int locationInput = console.nextInt();
        color.clearTxtBuffer();

        color.printTxtPurple("Product Shelf (0-1000): ").print(false);
        int shelfInput = console.nextInt();
        color.clearTxtBuffer();

        try {
            inputs.dbWrite(nameInput, priceInput, locationInput, shelfInput, -1);
        } catch(IllegalArgumentException e){
            color.printTxtPurple(e.getMessage()).print(true);
            return;
        }
        System.out.println("Successfully Inserted Item");

        color.clearTxtBuffer();
    }
}