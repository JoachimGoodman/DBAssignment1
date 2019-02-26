public class Logic {

    private DB db = new DB();

    public StringBuilder selectRetrieve(){
        StringBuilder result = new StringBuilder();

        for(Product tmp : db.selectProducts()){
            char[] modifyResult = tmp.toString().toCharArray();

            for(int i = 0; modifyResult[i] != ' '; i++){
                modifyResult[i] = Character.toUpperCase(modifyResult[i]);
            }

            /*while(modifyResult[counter] != '\n'){
                if(modifyResult[counter] == ' '){
                    modifyResult[counter+1] = Character.toUpperCase(modifyResult[counter+1]);
                } else {
                    modifyResult[counter+1] = Character.toLowerCase(modifyResult[counter+1]);
                }
                counter++;
            }*/



            /*for( ; counter < modifyResult[counter]; counter++){
                if(modifyResult[counter] == ' '){
                    modifyResult[counter+1] = Character.toUpperCase(modifyResult[counter+1]);
                } else if(modifyResult[counter] == '\n'){
                    break;
                }
            }*/

            result.append(modifyResult);
        }
        return result;
    }

    public boolean deleteChoice(int itemID){
        int[] idList = db.allIds();
        for(int i = 0; i < idList.length; i++){
            if(itemID == idList[i]){
                db.deleteProduct(itemID);
                return true;
            }
        }
        return false;
    }

    public boolean dbWrite(String productName, int productPrice, int productLocation, int shelfLocation){
        if(price_shelfCheck(productPrice) || locationCheck(productLocation) || price_shelfCheck(shelfLocation) || nameCheck(productName)) {
            return false;
        } else {
            String dbLocation = "L:0" + productLocation;
            dbLocation += " S:" +shelfLocation;
            db.insertProduct(productName, productPrice, dbLocation);
            return true;
        }
    }

    public boolean dbWrite(String productName, int productPrice, int productLocation, int shelfLocation, int itemID){
        if(price_shelfCheck(productPrice) || locationCheck(productLocation) || price_shelfCheck(shelfLocation) || nameCheck(productName)) {
            return false;
        } else {
            String dbLocation = "L:0" + productLocation;
            dbLocation += " S:" +shelfLocation;
            db.updateProduct(productName, productPrice, dbLocation, itemID);
            return true;
        }
    }

    public boolean nameCheck(String toExamine){
        return toExamine.length() != toExamine.replaceAll(
                "[~'#@*+%{}<>\\[\\]|\"\\_^]", "").length();
    }

    private boolean price_shelfCheck(int toExamine){
        return toExamine < 0 || toExamine > 1000;
    }

    private boolean locationCheck(int toExamine){
        return toExamine < 1 || toExamine > 3;
    }
}