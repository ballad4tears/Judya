package com.judya.judya;

import java.util.List;

/**
 * Created by warawat on 10/31/2016.
 */

public class List_Data {
    /**
     * output : [{"healthy_id":"82","category_id":"21","category_name":"อาการส่วนศีรษะ","healthy_name":"ชื่อ","healthy_detail":"อาการ","healthy_Cause":"สาเหตุ","healthy_treatment":"การรักษา","healthy_taking_care":"การดูแลตัวเอง","healthy_another":"อื่น ๆ","date":"2016-10-31","image_product":["http://projectandroid.top/Healthy-Dog/uploads/healthy/_14528404_1220376904679008_537828394_n.jpg"]},{"healthy_id":"84","category_id":"22","category_name":"อาการส่วนลำตัว","healthy_name":"ชื่อ","healthy_detail":"อาการ","healthy_Cause":"สาเหตุ","healthy_treatment":"การรักษา","healthy_taking_care":"การดูแลตัวเอง","healthy_another":"อื่น ๆ","date":"2016-10-31","image_product":["http://projectandroid.top/Healthy-Dog/uploads/healthy/_14518440_1220377178012314_2018323694_n.jpg"]},{"healthy_id":"85","category_id":"29","category_name":"โรค","healthy_name":"ชื่อ","healthy_detail":"อาการ","healthy_Cause":"สาเหตุ","healthy_treatment":"การรักษา","healthy_taking_care":"การดูแลตัวเอง","healthy_another":"อื่น ๆ","date":"2016-10-31","image_product":["http://projectandroid.top/Healthy-Dog/uploads/healthy/_14518570_1220376941345671_1788771358_n.jpg"]}]
     * status : true
     */

    private boolean status;
    /**
     * healthy_id : 82
     * category_id : 21
     * category_name : อาการส่วนศีรษะ
     * healthy_name : ชื่อ
     * healthy_detail : อาการ
     * healthy_Cause : สาเหตุ
     * healthy_treatment : การรักษา
     * healthy_taking_care : การดูแลตัวเอง
     * healthy_another : อื่น ๆ
     * date : 2016-10-31
     * image_product : ["http://projectandroid.top/Healthy-Dog/uploads/healthy/_14528404_1220376904679008_537828394_n.jpg"]
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
        private String healthy_id;
        private String category_id;
        private String category_name;
        private String healthy_name;
        private String healthy_detail;
        private String healthy_Cause;
        private String healthy_treatment;
        private String healthy_taking_care;
        private String healthy_another;
        private String date;
        private List<String> image_product;

        public String getHealthy_id() {
            return healthy_id;
        }

        public void setHealthy_id(String healthy_id) {
            this.healthy_id = healthy_id;
        }

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

        public String getHealthy_name() {
            return healthy_name;
        }

        public void setHealthy_name(String healthy_name) {
            this.healthy_name = healthy_name;
        }

        public String getHealthy_detail() {
            return healthy_detail;
        }

        public void setHealthy_detail(String healthy_detail) {
            this.healthy_detail = healthy_detail;
        }

        public String getHealthy_Cause() {
            return healthy_Cause;
        }

        public void setHealthy_Cause(String healthy_Cause) {
            this.healthy_Cause = healthy_Cause;
        }

        public String getHealthy_treatment() {
            return healthy_treatment;
        }

        public void setHealthy_treatment(String healthy_treatment) {
            this.healthy_treatment = healthy_treatment;
        }

        public String getHealthy_taking_care() {
            return healthy_taking_care;
        }

        public void setHealthy_taking_care(String healthy_taking_care) {
            this.healthy_taking_care = healthy_taking_care;
        }

        public String getHealthy_another() {
            return healthy_another;
        }

        public void setHealthy_another(String healthy_another) {
            this.healthy_another = healthy_another;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public List<String> getImage_product() {
            return image_product;
        }

        public void setImage_product(List<String> image_product) {
            this.image_product = image_product;
        }
    }
}
