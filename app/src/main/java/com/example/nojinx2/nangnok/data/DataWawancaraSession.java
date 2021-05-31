package com.example.nojinx2.nangnok.data;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DataWawancaraSession implements Serializable {

    @com.google.gson.annotations.SerializedName("id_judgement")
    private String id_judgement;
    @com.google.gson.annotations.SerializedName("id_event")
    private String id_event;
    @com.google.gson.annotations.SerializedName("id_detail_assesment")
    private String id_detail_assesment;
    @com.google.gson.annotations.SerializedName("id_session_assesment")
    private String id_session_assesment;
    @com.google.gson.annotations.SerializedName("id_users")
    private String id_users;
    @com.google.gson.annotations.SerializedName("id_participants")
    private String id_participants;
    @com.google.gson.annotations.SerializedName("value")
    private String value;
    @SerializedName("session")
    private sessionBean sessionBean;

    public String getId_judgement() {
        return id_judgement;
    }

    public void setId_judgement(String id_judgement) {
        this.id_judgement = id_judgement;
    }

    public String getId_event() {
        return id_event;
    }

    public void setId_event(String id_event) {
        this.id_event = id_event;
    }

    public String getId_detail_assesment() {
        return id_detail_assesment;
    }

    public void setId_detail_assesment(String id_detail_assesment) {
        this.id_detail_assesment = id_detail_assesment;
    }

    public String getId_session_assesment() {
        return id_session_assesment;
    }

    public void setId_session_assesment(String id_session_assesment) {
        this.id_session_assesment = id_session_assesment;
    }

    public String getId_users() {
        return id_users;
    }

    public void setId_users(String id_users) {
        this.id_users = id_users;
    }

    public String getId_participants() {
        return id_participants;
    }

    public void setId_participants(String id_participants) {
        this.id_participants = id_participants;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public sessionBean getSessionBean() { return sessionBean; }

    public void setSessionBean(sessionBean sessionBean) { this.sessionBean = sessionBean; }

    public static class sessionBean implements Serializable {

        @SerializedName("assesment_type") private String assesment_type;

        public String getAssesment_type() {
            return assesment_type;
        }
        public void setAssesment_type(String assesment_type) {
            this.assesment_type = assesment_type;
        }

    }

}
