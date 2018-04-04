package com.cni.statemap.neoman;

import com.cni.statemap.MapRow;

import java.util.List;

public  class StateMappingResponseBody {
        private String code;
        private String message;
        private boolean success;
        private List<MapRow> info;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public boolean isSuccess() {
            return success;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }

        public List<MapRow> getInfo() {
            return info;
        }

        public void setInfo(List<MapRow> info) {
            this.info = info;
        }

        @Override
        public String toString() {
            return "StateMappingResponseBody{" +
                    "code='" + code + '\'' +
                    ", message='" + message + '\'' +
                    ", success=" + success +
                    ", info=" + info +
                    '}';
        }

    }