package com.example.nojinx2.nangnok.data;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DataWawancara implements Serializable {

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
    @SerializedName("wawancara")
    private WawancaraBean wawancaraBean;

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

    public WawancaraBean getWawancaraBean() { return wawancaraBean; }

    public void setWawancaraBean(WawancaraBean wawancaraBean) { this.wawancaraBean = wawancaraBean; }

    public static class WawancaraBean implements Serializable {

        @SerializedName("pariwisata")
        private PariwisataBean pariwisataBean;

        public PariwisataBean getPariwisataBean() { return pariwisataBean; }

        public void setPariwisataBean(PariwisataBean pariwisataBean) { this.pariwisataBean = pariwisataBean; }

        public static class PariwisataBean implements Serializable {

            @SerializedName("sub_assesment")
            private String sub_assesment;

            @SerializedName("catatan_pariwisata")
            private CatatanPariwisataBean catatanPariwisataBean;
            @SerializedName("catatan_kesenian")
            private CatatanKesenianBean catatanKesenianBean;
            @SerializedName("catatan_pengumum")
            private CatatanPengumumBean catatanPengumumBean;
            @SerializedName("catatan_agama")
            private CatatanAgamaBean catatanAgamaBean;
            @SerializedName("catatan_basing")
            private CatatanBasingBean catatanBasingBean;

            @SerializedName("interview_pariwisata")
            private InterviewPariwisataBean interviewPariwisataBean;
            @SerializedName("grooming_pariwisata")
            private GroomingPariwisataBean groomingPariwisataBean;
            @SerializedName("manner_pariwisata")
            private MannerPariwisataBean mannerPariwisataBean;

            @SerializedName("interview_kesenian")
            private InterviewKesenianBean interviewKesenianBean;
            @SerializedName("grooming_kesenian")
            private GroomingKesenianBean groomingKesenianBean;
            @SerializedName("manner_kesenian")
            private MannerKesenianBean mannerKesenianBean;

            @SerializedName("interview_pengetahuan")
            private InterviewPengetahuanBean interviewPengetahuanBean;
            @SerializedName("grooming_pengetahuan")
            private GroomingPengetahuanBean groomingPengetahuanBean;
            @SerializedName("manner_pengetahuan")
            private MannerPengetahuanBean mannerPengetahuanBean;

            @SerializedName("interview_agama")
            private InterviewAgamaBean interviewAgamaBean;
            @SerializedName("grooming_agama")
            private GroomingAgamaBean groomingAgamaBean;
            @SerializedName("manner_agama")
            private MannerAgamaBean mannerAgamaBean;

            @SerializedName("interview_basing")
            private InterviewBasingBean interviewBasingBean;
            @SerializedName("grooming_basing")
            private GroomingBasingBean groomingBasingBean;
            @SerializedName("manner_basing")
            private MannerBasingBean mannerBasingBean;

            public String getSub_assesment() {
                return sub_assesment;
            }

            public void setSub_assesment(String sub_assesment) {
                this.sub_assesment = sub_assesment;
            }

            public CatatanPariwisataBean getCatatanPariwisataBean() { return catatanPariwisataBean; }

            public void setCatatanPariwisataBean(CatatanPariwisataBean catatanPariwisataBean) { this.catatanPariwisataBean = catatanPariwisataBean; }

            public static class CatatanPariwisataBean implements Serializable {
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

            public CatatanKesenianBean getCatatanKesenianBean() { return catatanKesenianBean; }

            public void setCatatanKesenianBean(CatatanKesenianBean catatanKesenianBean) { this.catatanKesenianBean = catatanKesenianBean; }

            public static class CatatanKesenianBean implements Serializable {
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

            public CatatanPengumumBean getCatatanPengumumBean() { return catatanPengumumBean; }

            public void setCatatanPengumumBean(CatatanPengumumBean catatanPengumumBean) { this.catatanPengumumBean = catatanPengumumBean; }

            public static class CatatanPengumumBean implements Serializable {
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

            public CatatanAgamaBean getCatatanAgamaBean() { return catatanAgamaBean; }

            public void setCatatanAgamaBean(CatatanAgamaBean catatanAgamaBean) { this.catatanAgamaBean = catatanAgamaBean; }

            public static class CatatanAgamaBean implements Serializable {
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

            public CatatanBasingBean getCatatanBasingBean() { return catatanBasingBean; }

            public void setCatatanBasingBean(CatatanBasingBean catatanBasingBean) { this.catatanBasingBean = catatanBasingBean; }

            public static class CatatanBasingBean implements Serializable {
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

            public InterviewPariwisataBean getInterviewPariwisataBean() { return interviewPariwisataBean; }

            public void setInterviewPariwisataBean(InterviewPariwisataBean interviewPariwisataBean) { this.interviewPariwisataBean = interviewPariwisataBean; }

            public static class InterviewPariwisataBean implements Serializable {
                @SerializedName("value") private String value;

                public String getValue() {
                    return value;
                }
                public void setValue(String value) {
                    this.value = value;
                }

            }

            public GroomingPariwisataBean getGroomingPariwisataBean() { return groomingPariwisataBean; }

            public void setGroomingPariwisataBean(GroomingPariwisataBean groomingPariwisataBean) { this.groomingPariwisataBean = groomingPariwisataBean; }

            public static class GroomingPariwisataBean implements Serializable {
                @SerializedName("value") private String value;

                public String getValue() {
                    return value;
                }
                public void setValue(String value) {
                    this.value = value;
                }

            }

            public MannerPariwisataBean getMannerPariwisataBean() { return mannerPariwisataBean; }

            public void setMannerPariwisataBean(MannerPariwisataBean mannerPariwisataBean) { this.mannerPariwisataBean = mannerPariwisataBean; }

            public static class MannerPariwisataBean implements Serializable {
                @SerializedName("value") private String value;

                public String getValue() {
                    return value;
                }
                public void setValue(String value) {
                    this.value = value;
                }

            }

            public InterviewKesenianBean getInterviewKesenianBean() { return interviewKesenianBean; }

            public void setInterviewKesenianBean(InterviewKesenianBean interviewKesenianBean) { this.interviewKesenianBean = interviewKesenianBean; }

            public static class InterviewKesenianBean implements Serializable {
                @SerializedName("value") private String value;

                public String getValue() {
                    return value;
                }
                public void setValue(String value) {
                    this.value = value;
                }

            }

            public GroomingKesenianBean getGroomingKesenianBean() { return groomingKesenianBean; }

            public void setGroomingKesenianBean(GroomingKesenianBean groomingKesenianBean) { this.groomingKesenianBean = groomingKesenianBean; }

            public static class GroomingKesenianBean implements Serializable {
                @SerializedName("value") private String value;

                public String getValue() {
                    return value;
                }
                public void setValue(String value) {
                    this.value = value;
                }

            }

            public MannerKesenianBean getMannerKesenianBean() { return mannerKesenianBean; }

            public void setMannerKesenianBean(MannerKesenianBean mannerKesenianBean) { this.mannerKesenianBean = mannerKesenianBean; }

            public static class MannerKesenianBean implements Serializable {
                @SerializedName("value") private String value;

                public String getValue() {
                    return value;
                }
                public void setValue(String value) {
                    this.value = value;
                }

            }

            public InterviewPengetahuanBean getInterviewPengetahuanBean() { return interviewPengetahuanBean; }

            public void setInterviewPengetahuanBean(InterviewPengetahuanBean interviewPengetahuanBean) { this.interviewPengetahuanBean = interviewPengetahuanBean; }

            public static class InterviewPengetahuanBean implements Serializable {
                @SerializedName("value") private String value;

                public String getValue() {
                    return value;
                }
                public void setValue(String value) {
                    this.value = value;
                }

            }

            public GroomingPengetahuanBean getGroomingPengetahuanBean() { return groomingPengetahuanBean; }

            public void setGroomingPengetahuanBean(GroomingPengetahuanBean groomingPengetahuanBean) { this.groomingPengetahuanBean = groomingPengetahuanBean; }

            public static class GroomingPengetahuanBean implements Serializable {
                @SerializedName("value") private String value;

                public String getValue() {
                    return value;
                }
                public void setValue(String value) {
                    this.value = value;
                }

            }

            public MannerPengetahuanBean getMannerPengetahuanBean() { return mannerPengetahuanBean; }

            public void setMannerPengetahuanBean(MannerPengetahuanBean mannerPengetahuanBean) { this.mannerPengetahuanBean = mannerPengetahuanBean; }

            public static class MannerPengetahuanBean implements Serializable {
                @SerializedName("value") private String value;

                public String getValue() {
                    return value;
                }
                public void setValue(String value) {
                    this.value = value;
                }

            }

            public InterviewAgamaBean getInterviewAgamaBean() { return interviewAgamaBean; }

            public void setInterviewAgamaBean(InterviewAgamaBean interviewAgamaBean) { this.interviewAgamaBean = interviewAgamaBean; }

            public static class InterviewAgamaBean implements Serializable {
                @SerializedName("value") private String value;

                public String getValue() {
                    return value;
                }
                public void setValue(String value) {
                    this.value = value;
                }

            }

            public GroomingAgamaBean getGroomingAgamaBean() { return groomingAgamaBean; }

            public void setGroomingAgamaBean(GroomingAgamaBean groomingAgamaBean) { this.groomingAgamaBean = groomingAgamaBean; }

            public static class GroomingAgamaBean implements Serializable {
                @SerializedName("value") private String value;

                public String getValue() {
                    return value;
                }
                public void setValue(String value) {
                    this.value = value;
                }

            }

            public MannerAgamaBean getMannerAgamaBean() { return mannerAgamaBean; }

            public void setMannerAgamaBean(MannerAgamaBean mannerAgamaBean) { this.mannerAgamaBean = mannerAgamaBean; }

            public static class MannerAgamaBean implements Serializable {
                @SerializedName("value") private String value;

                public String getValue() {
                    return value;
                }
                public void setValue(String value) {
                    this.value = value;
                }

            }

            public InterviewBasingBean getInterviewBasingBean() { return interviewBasingBean; }

            public void setInterviewBasingBean(InterviewBasingBean interviewBasingBean) { this.interviewBasingBean = interviewBasingBean; }

            public static class InterviewBasingBean implements Serializable {
                @SerializedName("value") private String value;

                public String getValue() {
                    return value;
                }
                public void setValue(String value) {
                    this.value = value;
                }

            }

            public GroomingBasingBean getGroomingBasingBean() { return groomingBasingBean; }

            public void setGroomingBasingBean(GroomingBasingBean groomingBasingBean) { this.groomingBasingBean = groomingBasingBean; }

            public static class GroomingBasingBean implements Serializable {
                @SerializedName("value") private String value;

                public String getValue() {
                    return value;
                }
                public void setValue(String value) {
                    this.value = value;
                }

            }

            public MannerBasingBean getMannerBasingBean() { return mannerBasingBean; }

            public void setMannerBasingBean(MannerBasingBean mannerBasingBean) { this.mannerBasingBean = mannerBasingBean; }

            public static class MannerBasingBean implements Serializable {
                @SerializedName("value") private String value;

                public String getValue() {
                    return value;
                }
                public void setValue(String value) {
                    this.value = value;
                }

            }


        }

    }

}
