package vo;

import java.sql.Date;

public class Product {
    private String productID;
    private String productName;
    private Date entryDate;
    private Date leftDate;
    private String QR;
    private String EPC;

    public String getProductName() {
        return productName;
    }

    public Date getEntryDate() {
        return entryDate;
    }

    public Date getLeftDate() {
        return leftDate;
    }

    public String getProductID() {
        return productID;
    }

    public String getEPC() {
        return EPC;
    }

    public String getQR() {
        return QR;
    }

    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public void setLeftDate(Date leftDate) {
        this.leftDate = leftDate;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setEPC(String EPC) {
        this.EPC = EPC;
    }

    public void setQR(String QR) {
        this.QR = QR;
    }

    public Product(){

    }
}

