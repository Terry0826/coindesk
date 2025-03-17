package com.cathay.coindesk.model.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Currency {
    @Id
    private String code;
    private String chineseName;

    // 無參構造函數 (JPA 要求)
    public Currency() {}

    public Currency(String code, String chineseName) {
        this.code = code;
        this.chineseName = chineseName;
    }

    // Getters 和 Setters
    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
    public String getChineseName() { return chineseName; }
    public void setChineseName(String chineseName) { this.chineseName = chineseName; }
}