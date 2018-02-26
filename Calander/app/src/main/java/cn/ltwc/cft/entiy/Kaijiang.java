package cn.ltwc.cft.entiy;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by admin on 2017/2/22.
 */

public class Kaijiang {
    @SerializedName("kaijiang")
    private List<Back> back;

    public List<Back> getBack() {
        return back;
    }

    public void setBack(List<Back> back) {
        this.back = back;
    }

    public static class Back {
        /**
         * id : 2592995
         * kj_date : 2017-02-21
         * qh_endate : 02-21
         * lot : 04,08,10,12,31,33#10
         * qihaoId : 3467098
         * qh : 17020
         * catId : 11
         */

        private int id;
        @SerializedName("kj_date")
        private String kjDate;
        @SerializedName("qh_endate")
        private String qhEndate;
        private String lot;
        private int qihaoId;
        private String qh;
        private int catId;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getKjDate() {
            return kjDate;
        }

        public void setKjDate(String kjDate) {
            this.kjDate = kjDate;
        }

        public String getQhEndate() {
            return qhEndate;
        }

        public void setQhEndate(String qhEndate) {
            this.qhEndate = qhEndate;
        }

        public String getLot() {
            return lot;
        }

        public void setLot(String lot) {
            this.lot = lot;
        }

        public int getQihaoId() {
            return qihaoId;
        }

        public void setQihaoId(int qihaoId) {
            this.qihaoId = qihaoId;
        }

        public String getQh() {
            return qh;
        }

        public void setQh(String qh) {
            this.qh = qh;
        }

        public int getCatId() {
            return catId;
        }

        public void setCatId(int catId) {
            this.catId = catId;
        }
    }
}
