package com.cni.dao;

import com.cni.dao.entity.CompStateMapping;

import java.util.List;

public interface StateMappingDao {

    CompStateMapping findByMyState(String myState);

    List<CompStateMapping> findByConstStatesAndCompName(String constStates, String compName);

    List<CompStateMapping> findByCompName(String compName);

    List<CompStateMapping> findByRegexNotEmptyByCompName(String compName);

    void insertCompStateMappingDocumentConstStates(String whereMyState, String constState);

    void deleteCompStateMappingDocumentConstStates(String whereMyState, String constState);

    void insertCompStateMappingDocument(CompStateMapping document);

    void updateCompStateMappingDocument(CompStateMapping document);

    void deleteCompStateMappingDocument(CompStateMapping document);

}