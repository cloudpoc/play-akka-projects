package models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bthiru on 11/16/2017.
 */
public class BrowseInfo implements Serializable {

    private String globalId;
    private String accountNo;
    private String mtn;

    private List browsedCategoryList;
    private List browsedDeviceList;
    private List browsedAccessoryList;
    private List cartDeviceList;
    private List cartAccessoryList;
    private List orderedDeviceList;
    private List orderedAccessoryList;
    private List cartCategoryList;
    private List orderCategoryList;

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getMtn() {
        return mtn;
    }

    public void setMtn(String mtn) {
        this.mtn = mtn;
    }

    public List getBrowsedCategoryList() {
        if(browsedCategoryList==null) browsedCategoryList= new ArrayList();
        return browsedCategoryList;
    }

    public void setBrowsedCategoryList(List browsedCategoryList) {
        this.browsedCategoryList = browsedCategoryList;
    }

    public List getBrowsedDeviceList() {
        if(browsedDeviceList==null) browsedDeviceList= new ArrayList();
        return browsedDeviceList;
    }

    public void setBrowsedDeviceList(List browsedDeviceList) {
        this.browsedDeviceList = browsedDeviceList;
    }

    public List getBrowsedAccessoryList() {
        if(browsedAccessoryList==null) browsedAccessoryList= new ArrayList();
        return browsedAccessoryList;
    }

    public void setBrowsedAccessoryList(List browsedAccessoryList) {
        this.browsedAccessoryList = browsedAccessoryList;
    }

    public List getCartDeviceList() {
        if(cartDeviceList==null) cartDeviceList= new ArrayList();
        return cartDeviceList;
    }

    public void setCartDeviceList(List cartDeviceList) {
        this.cartDeviceList = cartDeviceList;
    }

    public List getCartAccessoryList() {
        if(cartAccessoryList==null) cartAccessoryList= new ArrayList();
        return cartAccessoryList;
    }

    public void setCartAccessoryList(List cartAccessoryList) {
        this.cartAccessoryList = cartAccessoryList;
    }

    public List getOrderedDeviceList() {

        if(orderedDeviceList==null) orderedDeviceList= new ArrayList();
        return orderedDeviceList;
    }

    public void setOrderedDeviceList(List orderedDeviceList) {
        this.orderedDeviceList = orderedDeviceList;
    }

    public List getOrderedAccessoryList() {

        if(orderedAccessoryList==null) orderedAccessoryList= new ArrayList();
        return orderedAccessoryList;
    }

    public void setOrderedAccessoryList(List orderedAccessoryList) {
        this.orderedAccessoryList = orderedAccessoryList;
    }





    public List getCartCategoryList() {
        if(cartCategoryList==null) cartCategoryList= new ArrayList();
        return cartCategoryList;
    }

    public void setCartCategoryList(List cartCategoryList) {
        this.cartCategoryList = cartCategoryList;
    }

    public List getOrderCategoryList() {
        if(orderCategoryList==null) orderCategoryList= new ArrayList();
        return orderCategoryList;
    }

    public void setOrderCategoryList(List orderCategoryList) {
        this.orderCategoryList = orderCategoryList;
    }

    public String getGlobalId() {
        return globalId;
    }

    public void setGlobalId(String globalId) {
        this.globalId = globalId;
    }

}
