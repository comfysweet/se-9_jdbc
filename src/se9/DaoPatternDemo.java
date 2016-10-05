package se9;

import java.util.Locale;

/**
 * Created by ksu on 03.10.2016.
 */
public class DaoPatternDemo {
    public static void main(String[] args) {
        Locale.setDefault(Locale.ENGLISH);
        DaoInt customerDao = new DaoImpl();

        customerDao.selectAll("CUSTOMER");
        customerDao.select("CUSTOMER", "CUSTOMER_ID", "1");
        customerDao.update("CUSTOMER", "FIRST_NAME", "Alice", "Alice11");
        customerDao.insert("CUSTOMER", "5, 'Dmitry', 'Skvorcov', 34521");
        customerDao.delete("CUSTOMER", "FIRST_NAME", "Dmitry");

        customerDao.selectAll("CUSTOMER_ORDER");
        customerDao.select("CUSTOMER_ORDER", "ORDER_ID", "2");

        customerDao.selectAll("ORDER_PRODUCT");

        customerDao.selectAll("PRODUCT");
        customerDao.insert("PRODUCT", "4, 'RECORDER', 999");
        customerDao.delete("PRODUCT", "PRODUCT_ID", 4);

    }
}
