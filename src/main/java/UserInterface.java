import java.util.Scanner;

public class UserInterface {

    Scanner console = new Scanner(System.in);
    consoleColors cc = new consoleColors();
    InputLogic il = new InputLogic();
    SelectLogic sl = new SelectLogic();

    public UserInterface(){

        String userInput = "";                          //Uncertain if input should be of String type

        while(!userInput.equals("/quit")) {

            System.out.println("Welcome to the Database program");
            cc.printTxtBlue("1. Select data\n")
                    .printTxtYellow("2. Update data\n")
                    .printTxtRed("3. Delete data\n")
                    .printTxtPurple("4. Insert data\n")
                    .print(false);

            System.out.print("Please choose: ");
            userInput = console.next();
            cc.clearTxtBuffer();

                switch (userInput.charAt(0)) {
                    case '1':
                        cc.printTxtBlue("\n\n" + sl.selectRetrieve().toString()).print(true);
                        break;
                    case '2':
                        update();
                        break;
                    case '3':
                        delete();
                        break;
                    case '4':
                        insert();
                        break;
                }
            System.out.println();
            cc.clearTxtBuffer();
            }
    }

    private void update(){

    }

    private void delete(){

    }

    private void insert(){
        cc.printTxtPurple("Product Name: ").print(false);
        String nameInput = console.next();
        cc.clearTxtBuffer();

        cc.printTxtPurple("Product Price (0-1000): ").print(false);
        int priceInput = console.nextInt();
        cc.clearTxtBuffer();

        cc.printTxtPurple("Product Warehouse (1, 2 or 3): ").print(false);
        int locationInput = console.nextInt();
        cc.clearTxtBuffer();

        cc.printTxtPurple("Product Shelf (0-1000): ").print(false);
        int shelfInput = console.nextInt();
        cc.clearTxtBuffer();

        if(il.insertIntoDB(nameInput, priceInput, locationInput, shelfInput)){
            System.out.println("Successfully inserted item");
        } else {
            System.out.println("Failed to insert item, illegal characters included");
        }
    }
}