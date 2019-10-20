/**
 * @author baihu868
 * INFO202 2019
 * Administration.java
 */
import dao.DaoInterface;
import dao.ProductDatabase;
import gui.ProductAdministration;

public class Administration {
    static DaoInterface di;
    public static void main(String[] args) {
        di = new ProductDatabase();
        ProductAdministration pa = new ProductAdministration(di);
        pa.setVisible(true);
    }   
}
