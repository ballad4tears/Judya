package com.judya.judya;

import java.util.List;

/**
 * Created by warawat on 10/31/2016.
 */

public class List_Data_Cat {
    /**
     * output : [{"category_id":"21","category_name":"อาการส่วนศีรษะ","category_detail":"อาการส่วนศีรษะ - หู ตา จมูก ปาก","category_icon":"http://projectandroid.top/Healthy-Dog/uploads/category/_icn_button1.png"},{"category_id":"22","category_name":"อาการส่วนลำตัว","category_detail":"อาการส่วนลำตัว ท้อง แขน มือ อวัยวะภายใน","category_icon":"http://projectandroid.top/Healthy-Dog/uploads/category/_icn_button2.png"},{"category_id":"23","category_name":"อาการส่วนลำตัวส่วนล่าง","category_detail":"อาการส่วนลำตัวส่วนล่าง - อวัยวะเพศ ขา เท้า","category_icon":"http://projectandroid.top/Healthy-Dog/uploads/category/_icn_button3.png"},{"category_id":"24","category_name":"อาการทั่วไป ","category_detail":"อาการทั่วไป - ไข้หวัด ผิวหนัง ฯลฯ","category_icon":"http://projectandroid.top/Healthy-Dog/uploads/category/_icn_button4.png"},{"category_id":"29","category_name":"โรค","category_detail":"โรค","category_icon":"http://projectandroid.top/Healthy-Dog/uploads/category/"}]
     * status : true
     */

    private boolean status;
    /**
     * category_id : 21
     * category_name : อาการส่วนศีรษะ
     * category_detail : อาการส่วนศีรษะ - หู ตา จมูก ปาก
     * category_icon : http://projectandroid.top/Healthy-Dog/uploads/category/_icn_button1.png
     */

    private List<OutputBean> output;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<OutputBean> getOutput() {
        return output;
    }

    public void setOutput(List<OutputBean> output) {
        this.output = output;
    }

    public static class OutputBean {
        private String category_id;
        private String category_name;
        private String category_detail;
        private String category_icon;

        public String getCategory_id() {
            return category_id;
        }

        public void setCategory_id(String category_id) {
            this.category_id = category_id;
        }

        public String getCategory_name() {
            return category_name;
        }

        public void setCategory_name(String category_name) {
            this.category_name = category_name;
        }

        public String getCategory_detail() {
            return category_detail;
        }

        public void setCategory_detail(String category_detail) {
            this.category_detail = category_detail;
        }

        public String getCategory_icon() {
            return category_icon;
        }

        public void setCategory_icon(String category_icon) {
            this.category_icon = category_icon;
        }
    }
}
