//package com.example.layouts;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class CartSingleton {
//    private static CartSingleton instance;
//    private List<String> cartItems;
//    private List<String> cartPrices;
//    private List<Integer> cartImages;
//    private List<Integer> itemQuantities;
//
//    private CartSingleton() {
//        cartItems = new ArrayList<>();
//        cartPrices = new ArrayList<>();
//        cartImages = new ArrayList<>();
//        itemQuantities = new ArrayList<>();
//    }
//
//    public static synchronized CartSingleton getInstance() {
//        if (instance == null) {
//            instance = new CartSingleton();
//        }
//        return instance;
//    }
//
//    public List<String> getCartItems() {
//        return cartItems;
//    }
//
//    public List<String> getCartPrices() {
//        return cartPrices;
//    }
//
//    public List<Integer> getCartImages() {
//        return cartImages;
//    }
//
//    public List<Integer> getItemQuantities() {
//        return itemQuantities;
//    }
//
//    public void addItem(String item, String price, int image) {
//        cartItems.add(item);
//        cartPrices.add(price);
//        cartImages.add(image);
//        itemQuantities.add(1); // Adding default quantity 1
//    }
//}


package com.example.layouts;

import java.util.ArrayList;
import java.util.List;

public class CartSingleton {
    private static final CartSingleton instance = new CartSingleton();
    private final List<String> cartItems = new ArrayList<>();
    private final List<Integer> cartImages = new ArrayList<>();
    private final List<String> cartPrices = new ArrayList<>();
    private final List<Integer> itemQuantities = new ArrayList<>();

    private CartSingleton() {}

    public static CartSingleton getInstance() {
        return instance;
    }

    public List<String> getCartItems() {
        return cartItems;
    }

    public List<Integer> getCartImages() {
        return cartImages;
    }

    public List<String> getCartPrices() {
        return cartPrices;
    }

    public List<Integer> getItemQuantities() {
        return itemQuantities;
    }

    public void addItem(String item, int image, String price) {
        if (!cartItems.contains(item)) {
            cartItems.add(item);
            cartImages.add(image);
            cartPrices.add(price);
            itemQuantities.add(1); // Start with quantity 1
        } else {
            int index = cartItems.indexOf(item);
            itemQuantities.set(index, itemQuantities.get(index) + 1);
        }
    }
}
