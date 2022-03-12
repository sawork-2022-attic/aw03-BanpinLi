package com.example.webpos.biz;

import com.example.webpos.db.PosDB;
import com.example.webpos.model.Cart;
import com.example.webpos.model.Item;
import com.example.webpos.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PosServiceImp implements PosService {

    private PosDB posDB;

    @Autowired
    public void setPosDB(PosDB posDB) {
        this.posDB = posDB;
    }

    @Override
    public Cart getCart() {

        Cart cart = posDB.getCart();
        if (cart == null){
            cart = this.newCart();
        }
        return cart;
    }

    @Override
    public Cart newCart() {
        return posDB.saveCart(new Cart());
    }

    @Override
    public void checkout(Cart cart) {

    }

    @Override
    public void total(Cart cart) {

    }

    @Override
    public boolean update(Product product, int amount) {
        if(product == null) return false;

        return posDB.updateItem(new Item(product, amount));
    }

    @Override
    public boolean updateIncrement(String productId) {
        Item item = posDB.queryItemByProductId(productId);
        if(item == null) return false;

        return update(productId, item.getQuantity() + 1);
    }

    @Override
    public boolean updateDecrease(String productId) {
        Item item = posDB.queryItemByProductId(productId);
        if(item == null) return false;

        if(item.getQuantity() == 1) return false;

        return update(productId, item.getQuantity() - 1);
    }

    @Override
    public boolean update(String productId, int amount) {

        Product product = posDB.getProduct(productId);
        if(product == null) return false;

        return update(product, amount);
    }

    @Override
    public boolean remove(String productId) {
        Product product = posDB.getProduct(productId);
        if(product == null) return false;

        Item item = new Item(product, 1);
        return posDB.deleteItem(item);
    }

    @Override
    public boolean save(String productId) {
        Item item = posDB.queryItemByProductId(productId);
        if(item != null) {
            return update(productId, item.getQuantity() + 1);
        }

        Product product = posDB.getProduct(productId);
        if(product == null) return false;

        item = new Item(product, 1);
        return posDB.insertItem(item);
    }

    @Override
    public boolean emptyCart() {
        return posDB.deleteAllItem();
    }

    @Override
    public List<Product> products() {
        return posDB.getProducts();
    }
}
