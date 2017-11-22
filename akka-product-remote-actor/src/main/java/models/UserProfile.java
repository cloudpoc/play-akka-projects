package models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bthiru on 11/16/2017.
 */
public class UserProfile {
    public List getRvCategoryList() {
        if(rvCategoryList==null) rvCategoryList= new ArrayList();
        return rvCategoryList;
    }

    public void setRvCategoryList(List rvCategoryList) {
        this.rvCategoryList = rvCategoryList;
    }

    public List getRvAccessoryList() {
        if(rvAccessoryList==null) rvAccessoryList= new ArrayList();
        return rvAccessoryList;
    }

    public void setRvAccessoryList(List rvAccessoryList) {
        this.rvAccessoryList = rvAccessoryList;
    }

    public List getRvDeviceList() {
        if(rvDeviceList==null) rvDeviceList= new ArrayList();
        return rvDeviceList;
    }

    public void setRvDeviceList(List rvDeviceList) {
        this.rvDeviceList = rvDeviceList;
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
    private List rvDeviceList;
    private List rvAccessoryList;
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
