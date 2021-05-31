package com.example.nojinx2.nangnok.data;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DataPersonality implements Serializable {

    @com.google.gson.annotations.SerializedName("id_participants")
    private String id_participants;
    @com.google.gson.annotations.SerializedName("id_event")
    private String id_event;
    @com.google.gson.annotations.SerializedName("id_users")
    private String id_users;
    @com.google.gson.annotations.SerializedName("num_reg")
    private String num_reg;
    @com.google.gson.annotations.SerializedName("identity_card")
    private String identity_card;
    @com.google.gson.annotations.SerializedName("full_name")
    private String full_name;
    @com.google.gson.annotations.SerializedName("nick_name")
    private String nick_name;
    @com.google.gson.annotations.SerializedName("address")
    private String address;
    @com.google.gson.annotations.SerializedName("place_of_birth")
    private String place_of_birth;
    @com.google.gson.annotations.SerializedName("date_of_birth")
    private String date_of_birth;
    @com.google.gson.annotations.SerializedName("gender")
    private String gender;
    @com.google.gson.annotations.SerializedName("work")
    private String work;
    @com.google.gson.annotations.SerializedName("religion")
    private String religion;
    @com.google.gson.annotations.SerializedName("height")
    private String height;
    @com.google.gson.annotations.SerializedName("weight")
    private String weight;
    @com.google.gson.annotations.SerializedName("instagram_id")
    private String instagram_id;
    @com.google.gson.annotations.SerializedName("fb_id")
    private String fb_id;
    @com.google.gson.annotations.SerializedName("line_id")
    private String line_id;
    @com.google.gson.annotations.SerializedName("whatsapp_num")
    private String whatsapp_num;
    @com.google.gson.annotations.SerializedName("reason")
    private String reason;
    @com.google.gson.annotations.SerializedName("vision_mission")
    private String vision_mission;
    @com.google.gson.annotations.SerializedName("img_transfer")
    private String img_transfer;
    @com.google.gson.annotations.SerializedName("img_cv")
    private String img_cv;
    @com.google.gson.annotations.SerializedName("bank_name")
    private String bank_name;
    @com.google.gson.annotations.SerializedName("transfer_date")
    private String transfer_date;
    @com.google.gson.annotations.SerializedName("status")
    private String status;
    @SerializedName("image")
    private ImageBean imageBean;
    @SerializedName("personality")
    private PersonalityBean personalityBean;
    @SerializedName("catatan")
    private CatatanBean catatanBean;

    public String getId_participants() {
        return id_participants;
    }

    public void setId_participants(String id_participants) {
        this.id_participants = id_participants;
    }

    public String getId_event() {
        return id_event;
    }

    public void setId_event(String id_event) {
        this.id_event = id_event;
    }

    public String getId_users() {
        return id_users;
    }

    public void setId_users(String id_users) {
        this.id_users = id_users;
    }

    public String getNum_reg() {
        return num_reg;
    }

    public void setNum_reg(String num_reg) {
        this.num_reg = num_reg;
    }

    public String getIdentity_card() {
        return identity_card;
    }

    public void setIdentity_card(String identity_card) {
        this.identity_card = identity_card;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPlace_of_birth() {
        return place_of_birth;
    }

    public void setPlace_of_birth(String place_of_birth) {
        this.place_of_birth = place_of_birth;
    }

    public String getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getInstagram_id() {
        return instagram_id;
    }

    public void setInstagram_id(String instagram_id) {
        this.instagram_id = instagram_id;
    }

    public String getFb_id() {
        return fb_id;
    }

    public void setFb_id(String fb_id) {
        this.fb_id = fb_id;
    }

    public String getLine_id() {
        return line_id;
    }

    public void setLine_id(String line_id) {
        this.line_id = line_id;
    }

    public String getWhatsapp_num() {
        return whatsapp_num;
    }

    public void setWhatsapp_num(String whatsapp_num) {
        this.whatsapp_num = whatsapp_num;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getVision_mission() {
        return vision_mission;
    }

    public void setVision_mission(String vision_mission) {
        this.vision_mission = vision_mission;
    }

    public String getImg_transfer() {
        return img_transfer;
    }

    public void setImg_transfer(String img_transfer) {
        this.img_transfer = img_transfer;
    }

    public String getImg_cv() {
        return img_cv;
    }

    public void setImg_cv(String img_cv) {
        this.img_cv = img_cv;
    }

    public String getBank_name() {
        return bank_name;
    }

    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }

    public String getTransfer_date() {
        return transfer_date;
    }

    public void setTransfer_date(String transfer_date) {
        this.transfer_date = transfer_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ImageBean getImageBean() { return imageBean; }

    public void setImageBean(ImageBean imageBean) { this.imageBean = imageBean; }

    public static class ImageBean implements Serializable {

        @SerializedName("image_name") private String image_name;

        public String getImage_name() {
            return image_name;
        }
        public void setImage_name(String image_name) {
            this.image_name = image_name;
        }

    }

    public PersonalityBean getPersonalityBean() { return personalityBean; }

    public void setPersonalityBean(PersonalityBean personalityBean) { this.personalityBean = personalityBean; }

    public static class PersonalityBean implements Serializable {

        @SerializedName("value") private String value;

        public String getValue() {
            return value;
        }
        public void setValue(String value) {
            this.value = value;
        }


    }

    public CatatanBean getCatatanBean() { return catatanBean; }

    public void setCatatanBean(CatatanBean catatanBean) { this.catatanBean = catatanBean; }

    public static class CatatanBean implements Serializable {

        @SerializedName("status") private String status;
        @SerializedName("note") private String note;

        public String getStatus() {
            return status;
        }
        public void setStatus(String status) {
            this.status = status;
        }

        public String getNote() {
            return note;
        }
        public void setNote(String note) {
            this.note = note;
        }


    }

}
