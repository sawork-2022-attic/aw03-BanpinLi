package com.example.webpos.biz;

import com.example.webpos.model.Cart;
import com.example.webpos.model.Item;
import com.example.webpos.model.Product;

import java.util.List;

public interface PosService {
    public Cart getCart();

    public Cart newCart();

    public void checkout(Cart cart);

    public void total(Cart cart);

    public boolean update(Product product, int amount);

    public boolean updateIncrement(String productId);

    public boolean updateDecrease(String productId);

    public boolean update(String productId, int amount);

    boolean remove(String productId);

    boolean save(String productId);

    boolean emptyCart();

    public List<Product> products();
}
