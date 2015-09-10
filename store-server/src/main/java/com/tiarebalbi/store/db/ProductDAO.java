package com.tiarebalbi.store.db;

import com.tiarebalbi.store.api.PageRequest;
import com.tiarebalbi.store.core.catalog.Product;
import io.dropwizard.hibernate.AbstractDAO;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;

import java.util.Date;
import java.util.List;


/**
 * Product DAO
 *
 * @author Tiarê Balbi Bonamini
 * @version 1.0.0
 */
public class ProductDAO extends AbstractDAO<Product> {

    private final static Logger LOGGER = Logger.getLogger("c.t.s.d.ProductDAO");

    public ProductDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Product findById(Long id) {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("finding a product by ID");
        }

        return get(id);
    }

    public List<Product> getList(int page, int size) {

        PageRequest pagination = PageRequest.builder().page(page).size(size).build();

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("finding a list of products using a query");
        }

        return currentSession().createQuery("FROM Product s")
                .setFirstResult(pagination.getOffset())
                .setMaxResults(pagination.getSize())
                .list();
    }

    public List<String> getPictures(Long idProduct) {
        return currentSession().createSQLQuery("SELECT p.picture FROM product_picture p WHERE p.product_id = :idProduct").setParameter("idProduct", idProduct).list();
    }

    public Product save(Product product) {

        if (LOGGER.isDebugEnabled()) {
            if (product.getId() == null) {
                LOGGER.debug("Saving a new product");
            } else {
                LOGGER.debug("Updating the product: " + product.getId());
            }
        }

        // Define default date
        if (product.getPublicationDate() == null) {
            product.setPublicationDate(new Date());
        }

        return super.persist(product);
    }
}
