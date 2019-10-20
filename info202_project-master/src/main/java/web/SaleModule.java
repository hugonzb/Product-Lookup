package web;

import dao.SaleDAOInterface;
import dao.SaleDatabase;
import domain.Sale;
import org.jooby.Jooby;
import org.jooby.Status;

public class SaleModule extends Jooby {
    
    SaleDAOInterface saleDao = new SaleDatabase();

    public SaleModule(SaleDAOInterface saleDao) {
        post("/api/sales", (req, rsp) -> {
        Sale sale = req.body().to(Sale.class);
        System.out.println(sale);
        saleDao.save(sale);
        rsp.status(Status.CREATED);
        });
    }   
}
