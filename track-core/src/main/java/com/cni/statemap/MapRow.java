package com.cni.statemap;

import java.io.Serializable;

/**
 * MapConfigHolder
 * 一条状态映射配置信息的实体类
 */
public class MapRow implements Cloneable, Serializable {

            private long statusMatchingId;
            private long statusConfigId;
            private String matchingStatus;
            private String replaceStatus;
            private int matchingType;
            private String channelName;
            private String statusName;
            private boolean flagException;
            private boolean flagDisplay;
            private boolean flagFinish;
            private String flowType;

            public void setFlowType(String flowType) {
                this.flowType = flowType;
            }

            public String getFlowType() {
                return flowType;
            }

            public void setStatusMatchingId(long statusMatchingId) {
                this.statusMatchingId = statusMatchingId;
            }
            public long getStatusMatchingId() {
                return statusMatchingId;
            }

            public long getStatusConfigId() {
                return statusConfigId;
            }

            public void setStatusConfigId(long statusConfigId) {
                this.statusConfigId = statusConfigId;
            }

            public String getMatchingStatus() {
                return matchingStatus;
            }

            public void setMatchingStatus(String matchingStatus) {
                this.matchingStatus = matchingStatus;
            }

            public int getMatchingType() {
                return matchingType;
            }

            public void setMatchingType(int matchingType) {
                this.matchingType = matchingType;
            }

            public String getChannelName() {
                return channelName;
            }

            public void setChannelName(String channelName) {
                this.channelName = channelName;
            }

            public String getStatusName() {
                return statusName;
            }

            public void setStatusName(String statusName) {
                this.statusName = statusName;
            }

            public boolean isFlagException() {
                return flagException;
            }

            public void setFlagException(boolean flagException) {
                this.flagException = flagException;
            }

            public boolean isFlagDisplay() {
                return flagDisplay;
            }

            public void setFlagDisplay(boolean flagDisplay) {
                this.flagDisplay = flagDisplay;
            }

            public boolean isFlagFinish() {
                return flagFinish;
            }

            public void setFlagFinish(boolean flagFinish) {
                this.flagFinish = flagFinish;
            }

            public void setReplaceStatus(String replaceStatus) {
                this.replaceStatus = replaceStatus;
            }

            public String getReplaceStatus() {
                return replaceStatus;
            }

            @Override
            public MapRow clone() throws CloneNotSupportedException {
                return (MapRow) super.clone();
            }

            @Override
            public String toString() {
                return "MapRow{" +
                        "statusMatchingId=" + statusMatchingId +
                        ", statusConfigId=" + statusConfigId +
                        ", matchingStatus='" + matchingStatus + '\'' +
                        ", replaceStatus='" + replaceStatus + '\'' +
                        ", matchingType=" + matchingType +
                        ", channelName='" + channelName + '\'' +
                        ", statusName='" + statusName + '\'' +
                        ", flagException=" + flagException +
                        ", flagDisplay=" + flagDisplay +
                        ", flagFinish=" + flagFinish +
                        '}';
            }
        }
