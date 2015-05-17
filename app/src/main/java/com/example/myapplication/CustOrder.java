package com.example.myapplication;

/**
 * Created by 4540 on 5/12/2015.
 */
public class CustOrder {
    private String id;
    private String[][] order;

    public CustOrder(String id, String[][] order){
        this.setId(id);
        this.setOrder(order);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String[][] getOrder() {
        return order;
    }

    public void setOrder(String[][] order) {
        this.order = order;
    }
}
