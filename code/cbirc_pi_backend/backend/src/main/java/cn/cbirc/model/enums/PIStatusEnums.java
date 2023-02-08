package cn.cbirc.model.enums;

public enum PIStatusEnums {
    DRAFT("DRAFT"),
    PUBLIC("PUBLIC"),
    ABOLISH("ABOLISH");

    private String status;

    PIStatusEnums(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
