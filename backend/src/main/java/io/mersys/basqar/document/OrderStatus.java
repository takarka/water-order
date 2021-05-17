package io.mersys.basqar.document;

public enum OrderStatus {
    CREATED("CREATED"),
    APPROVED("APPROVED"),
    CANCELED("APPROVED"),
    DELIVERING("DELIVERING"),
    ORDERING("ORDERING");
    
    private String caption;

    private OrderStatus(String caption) {
        this.caption = caption;
    }
    
    public String getCaption() {
        return this.caption;
    }
    
    public String getKey() {
        return name();
    }
}
