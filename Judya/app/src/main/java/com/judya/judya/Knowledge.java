package com.judya.judya;

import java.util.List;

/**
 * Created by warawat on 11/4/2016.
 */

public class Knowledge {
    /**
     * output : [{"knowledge_id":"2","knowledge_name":"ชื่อ เกร็ดความรู้","knowledge_detail":"เกร็ดความรู้","knowledge_icon":"http://projectandroid.top/Healthy-Dog/uploads/knowledge/_36982.jpg"}]
     * status : true
     */

    private boolean status;
    /**
     * knowledge_id : 2
     * knowledge_name : ชื่อ เกร็ดความรู้
     * knowledge_detail : เกร็ดความรู้
     * knowledge_icon : http://projectandroid.top/Healthy-Dog/uploads/knowledge/_36982.jpg
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
        private String knowledge_id;
        private String knowledge_name;
        private String knowledge_detail;
        private String knowledge_icon;

        public String getKnowledge_id() {
            return knowledge_id;
        }

        public void setKnowledge_id(String knowledge_id) {
            this.knowledge_id = knowledge_id;
        }

        public String getKnowledge_name() {
            return knowledge_name;
        }

        public void setKnowledge_name(String knowledge_name) {
            this.knowledge_name = knowledge_name;
        }

        public String getKnowledge_detail() {
            return knowledge_detail;
        }

        public void setKnowledge_detail(String knowledge_detail) {
            this.knowledge_detail = knowledge_detail;
        }

        public String getKnowledge_icon() {
            return knowledge_icon;
        }

        public void setKnowledge_icon(String knowledge_icon) {
            this.knowledge_icon = knowledge_icon;
        }
    }
}
