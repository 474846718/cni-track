package com.cni.dao;

import com.cni.dao.entity.ArchiveWaybill;

import java.util.List;


public interface ArchiveWaybillDao {

    /**
     * 通过参数找出归档表已经存在的单号
     */
    void upsertLatestInfoNodeDate(List<ArchiveWaybill> overOrderBills);
}

