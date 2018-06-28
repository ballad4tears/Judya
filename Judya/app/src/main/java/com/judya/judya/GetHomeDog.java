package com.judya.judya;

import java.util.List;

/**
 * Created by warawat on 3/15/2017.
 */

public class GetHomeDog {


    private List<OutputBean> output;

    public List<OutputBean> getOutput() {
        return output;
    }

    public void setOutput(List<OutputBean> output) {
        this.output = output;
    }

    public static class OutputBean {
        /**
         * id : 17
         * type1 : พบเจอ
         * type2 : เพศเมีย
         * type3 : ชิวาว่า
         * type4 : S,(9-10นิ้ว)
         * type5 : โทนสีแดง/สีส้ม/สีเทาแดง/สีน้ำตาลดำ
         * type6 : ลายของสุนัข*
         * type7 : อุปกรณ์ของสุนัข*
         * type8 : รายละเอียดเพิ่มเติม*
         * type9 : รายละเอียดผู้แจ้ง*
         * latitude : 13.805680
         * longitude : 100.648695
         * name : 0
         * registeruser_id :
         * image_name : http://projectandroid.top/HomeDog/uploads/user/_36982.jpg
         * Distination : 2.9
         */

        private String id;
        private String type1;
        private String type2;
        private String type3;
        private String type4;
        private String type5;
        private String type6;
        private String type7;
        private String type8;
        private String type9;
        private String latitude;
        private String longitude;
        private String name;
        private String registeruser_id;
        private String image_name;
        private double Distination;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getType1() {
            return type1;
        }

        public void setType1(String type1) {
            this.type1 = type1;
        }

        public String getType2() {
            return type2;
        }

        public void setType2(String type2) {
            this.type2 = type2;
        }

        public String getType3() {
            return type3;
        }

        public void setType3(String type3) {
            this.type3 = type3;
        }

        public String getType4() {
            return type4;
        }

        public void setType4(String type4) {
            this.type4 = type4;
        }

        public String getType5() {
            return type5;
        }

        public void setType5(String type5) {
            this.type5 = type5;
        }

        public String getType6() {
            return type6;
        }

        public void setType6(String type6) {
            this.type6 = type6;
        }

        public String getType7() {
            return type7;
        }

        public void setType7(String type7) {
            this.type7 = type7;
        }

        public String getType8() {
            return type8;
        }

        public void setType8(String type8) {
            this.type8 = type8;
        }

        public String getType9() {
            return type9;
        }

        public void setType9(String type9) {
            this.type9 = type9;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getRegisteruser_id() {
            return registeruser_id;
        }

        public void setRegisteruser_id(String registeruser_id) {
            this.registeruser_id = registeruser_id;
        }

        public String getImage_name() {
            return image_name;
        }

        public void setImage_name(String image_name) {
            this.image_name = image_name;
        }

        public double getDistination() {
            return Distination;
        }

        public void setDistination(double Distination) {
            this.Distination = Distination;
        }
    }
}
