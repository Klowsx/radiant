package com.example.radiant.Models;

public class Message {
    private Long senderId;
    private Long addresseId;
    private String content;
    private boolean view;

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long remitenteId) {
        this.senderId = remitenteId;
    }

    public Long getAddresseId() {
        return addresseId;
    }

    public void setAddresseId(Long destinatarioId) {
        this.addresseId = destinatarioId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String contenido) {
        this.content = contenido;
    }

    public boolean isView() {
        return view;
    }

    public void setView(boolean leido) {
        this.view = leido;
    }

}
