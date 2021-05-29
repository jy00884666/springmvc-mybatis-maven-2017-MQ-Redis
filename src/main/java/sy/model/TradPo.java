package sy.model;

import java.math.BigDecimal;

public class TradPo {
    private BigDecimal id;

    private String userId;

    private String textId;

    private String formYue;

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getTextId() {
        return textId;
    }

    public void setTextId(String textId) {
        this.textId = textId == null ? null : textId.trim();
    }

    public String getFormYue() {
        return formYue;
    }

    public void setFormYue(String formYue) {
        this.formYue = formYue == null ? null : formYue.trim();
    }
}