package models;

public class ProductStore {
    private static ProductStore productStore;

    public static ProductStore getInstance() {
        if (productStore == null) {
            productStore = new ProductStore();
        }
        return productStore;
    }

    public Product getProduct(String id) {
        Product p = new Product();
        p.setId("P001");
        p.setProductImage("");
        p.setShortDesc("iPhone X features a new all-screen design. Face ID, which makes your face your password. And the most powerful and smartest chip ever in a smartphone");
        p.setQty(100);
        p.setSalePrice(1000.00);
        p.setItemName("IphoneX");
        return p;
    }
}