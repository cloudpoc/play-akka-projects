package models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bthiru on 11/16/2017.
 */
public class SiteHistory {
    public List getRvCategoryList() {
        if(rvCategoryList==null) rvCategoryList= new ArrayList();
        return rvCategoryList;
    }

    public void setRvCategoryList(List rvCategoryList) {
        this.rvCategoryList = rvCategoryList;
    }

    public List getRvProductList() {
        if(rvProductList==null) rvProductList= new ArrayList();
        return rvProductList;
    }

    public void setRvProductList(List rvProductList) {
        this.rvProductList = rvProductList;
    }

    public List getCartCategoryList() {
        if(cartCategoryList==null) cartCategoryList= new ArrayList();
        return cartCategoryList;
    }

    public void setCartCategoryList(List cartCategoryList) {
        this.cartCategoryList = cartCategoryList;
    }

    public List getCartProductList() {
        if(cartProductList==null) cartProductList= new ArrayList();
        return cartProductList;
    }

    public void setCartProductList(List cartProductList) {
        this.cartProductList = cartProductList;
    }

    public List getOrderCategoryList() {
        if(orderCategoryList==null) orderCategoryList= new ArrayList();
        return orderCategoryList;
    }

    public void setOrderCategoryList(List orderCategoryList) {
        this.orderCategoryList = orderCategoryList;
    }

    public List getOrderProductList() {
        if(orderProductList==null) orderProductList= new ArrayList();
        return orderProductList;
    }

    public void setOrderProductList(List orderProductList) {
        this.orderProductList = orderProductList;
    }

    private List rvCategoryList;
    private List rvProductList;
    private List cartCategoryList;
    private List cartProductList;
    private List orderCategoryList;
    private List orderProductList;

    public String getGlobalId() {
        return globalId;
    }

    public void setGlobalId(String globalId) {
        this.globalId = globalId;
    }

    private String globalId;
}
