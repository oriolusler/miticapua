package com.oriolsoler.pua.entities;

import jakarta.persistence.*;


@Entity
public class Image {

    @Id
    @GeneratedValue
    private Long id;

    // @Lob
    @Column(columnDefinition = "bytea")
    private byte[] content;

    private String type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pua_id")
    private Pua pua;

    public Image(byte[] content, Pua pua, String type) {
        this.content = content;
        this.pua = pua;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public Image() {
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public Pua getPua() {
        return pua;
    }

    public void setPua(Pua pua) {
        this.pua = pua;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}