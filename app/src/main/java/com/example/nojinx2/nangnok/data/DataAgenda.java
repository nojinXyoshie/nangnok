package com.example.nojinx2.nangnok.data;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DataAgenda implements Serializable {

    @com.google.gson.annotations.SerializedName("id_agenda")
    private String id_agenda;
    @com.google.gson.annotations.SerializedName("id_event")
    private String id_event;
    @com.google.gson.annotations.SerializedName("agenda_name")
    private String agenda_name;
    @com.google.gson.annotations.SerializedName("description")
    private String description;
    @com.google.gson.annotations.SerializedName("vanue")
    private String vanue;
    @com.google.gson.annotations.SerializedName("agenda_date")
    private String agenda_date;
    @SerializedName("detail_agenda")
    private DetailBean detail_agenda;

    public String getId_agenda() {
        return id_agenda;
    }

    public void setId_agenda(String id_agenda) {
        this.id_agenda = id_agenda;
    }

    public String getId_event() {
        return id_event;
    }

    public void setId_event(String id_event) {
        this.id_event = id_event;
    }

    public String getAgenda_name() {
        return agenda_name;
    }

    public void setAgenda_name(String agenda_name) {
        this.agenda_name = agenda_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVanue() {
        return vanue;
    }

    public void setVanue(String vanue) {
        this.vanue = vanue;
    }

    public String getAgenda_date() {
        return agenda_date;
    }

    public void setAgenda_date(String agenda_date) {
        this.agenda_date = agenda_date;
    }

    public DetailBean getDetail_agenda() { return detail_agenda; }

    public void setDetail_agenda(DetailBean detail_agenda) { this.detail_agenda = detail_agenda; }

    public static class DetailBean implements Serializable {

        @SerializedName("id_detail_agenda") private String id_detail_agenda;
        @SerializedName("id_agenda") private String id_agenda;
        @SerializedName("no") private String no;
        @SerializedName("activity_name") private String agenda_session;
        @SerializedName("person_in_charge") private String person_in_charge;
        @SerializedName("time_start") private String time_start;
        @SerializedName("time_end") private String time_end;
        @SerializedName("information") private String information;

        public String getId_detail_agenda() {
            return id_detail_agenda;
        }
        public void setIdDetail(String id_detail_agenda) {
            this.id_detail_agenda = id_detail_agenda;
        }

        public String getId_agenda() {
            return id_agenda;
        }
        public void setId_agenda(String id_agenda) {
            this.id_agenda = id_agenda;
        }

        public String getNo() {
            return no;
        }
        public void setNo(String no) {
            this.no = no;
        }

        public String getAgenda_session() {
            return agenda_session;
        }
        public void setAgenda_session(String agenda_session) {
            this.agenda_session = agenda_session;
        }

        public String getPerson_in_charge() {
            return person_in_charge;
        }
        public void setPerson_in_charge(String person_in_charge) {
            this.person_in_charge = person_in_charge;
        }

        public String getTime_start() {
            return time_start;
        }
        public void setTime_start(String time_start) {
            this.time_start = time_start;
        }

        public String getTime_end() {
            return time_end;
        }
        public void setTime_end(String time_end) {
            this.time_end = time_end;
        }

        public String getInformation() {
            return information;
        }
        public void setInformation(String information) {
            this.information = information;
        }

    }

}