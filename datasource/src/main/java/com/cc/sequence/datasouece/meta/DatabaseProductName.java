package com.cc.sequence.datasouece.meta;

/**
 * Created by miyan(弥彦) on 2017/1/6 14:34.
 *
 */
public enum DatabaseProductName {

    MYSQL("MySql");

    private String productName;

    DatabaseProductName(String productName){
        this.productName = productName;
    }

    public String value(){return this.productName;}

    private static DatabaseProductName covert(String name){
        for (DatabaseProductName s :values()){
            if (s.productName.equalsIgnoreCase(name)){
                return s;
            }
        }
        throw new IllegalArgumentException("unsupport db product "+ name);
    }

}
