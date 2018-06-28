package com.judya.judya;

import java.util.List;

/**
 * Created by warawat on 3/24/2017.
 */

public class List_User {
    /**
     * output : [{"id":"4","username":"154522241002","password":"1102700222633","name":"นาย","lastname":"กัณตภน ขวัญเซ่ง","address":"วิศวกรรมศาสตาร์","tel":""}]
     * status : true
     */

    private boolean status;
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
        /**
         * id : 4
         * username : 154522241002
         * password : 1102700222633
         * name : นาย
         * lastname : กัณตภน ขวัญเซ่ง
         * address : วิศวกรรมศาสตาร์
         * tel :
         */

        private String id;
        private String username;
        private String password;
        private String name;
        private String lastname;
        private String address;
        private String tel;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLastname() {
            return lastname;
        }

        public void setLastname(String lastname) {
            this.lastname = lastname;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }
    }
}
