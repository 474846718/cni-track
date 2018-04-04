package com.cni.dao;

import com.cni.dao.entity.CompStateMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class StateMappingDaoImpl implements StateMappingDao {

    private static final Object[] EMPTY_ARRAY = {};

    private final MongoTemplate template;

    @Autowired
    public StateMappingDaoImpl(MongoTemplate template) {
        this.template = template;
    }

    @Override
    public CompStateMapping findByMyState(String myState) {
        Query query = new Query(Criteria.where("myState").is(myState));
        return template.findOne(query, CompStateMapping.class);
    }

    @Override
    public List<CompStateMapping> findByRegexNotEmptyByCompName(String compName) {
        Query query = new Query(Criteria.where("Regex").ne(EMPTY_ARRAY).and("compName").is(compName));
        return template.find(query, CompStateMapping.class);
    }


    @Override
    public List<CompStateMapping> findByConstStatesAndCompName(String constStates, String compName) {
        Query query = new Query(Criteria.where("constStates").is(constStates).and("compName").is(compName));
        return template.find(query, CompStateMapping.class);
    }

    @Override
    public List<CompStateMapping> findByCompName(String compName) {
        Query query = new Query(Criteria.where("compName").is(compName));
        return template.find(query, CompStateMapping.class);
    }

    @Override
    public void insertCompStateMappingDocumentConstStates(String whereMyState, String constState) {
        Query query = new Query(Criteria.where("myState").is(whereMyState));
        Update update = new Update().push("constStates", constState);
        template.updateFirst(query, update, CompStateMapping.class);
    }


    @Override
    public void deleteCompStateMappingDocumentConstStates(String whereMyState, String constState) {
        Query query = new Query(Criteria.where("myState").is(constState));
        Update update = new Update().pull("constStates", constState);
        template.updateFirst(query, update, CompStateMapping.class);
    }

    @Override
    public void insertCompStateMappingDocument(CompStateMapping document) {
        template.insert(document);
    }

    @Override
    public void updateCompStateMappingDocument(CompStateMapping document) {
        Query query = new Query(Criteria.where("compName").is(document.getCompName()).and("myState").is(document.getMyState()));
        Update update = Update.update("constStates", document.getConstStates());
        template.updateFirst(query, update, CompStateMapping.class);
    }

    @Override
    public void deleteCompStateMappingDocument(CompStateMapping document) {
        template.remove(document);
    }

}
