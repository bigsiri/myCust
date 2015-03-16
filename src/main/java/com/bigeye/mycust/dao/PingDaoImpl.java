package com.bigeye.mycust.dao;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

@Repository
public final class PingDaoImpl implements PingDao {

    protected EntityManager entityManager = null;
    
    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    @Override
    public Date getCurrentDate() {
    	final Query query = entityManager.createNativeQuery("select NOW()");
    	final Object obj = query.getSingleResult();
    	if (obj instanceof Date) {
    		return (Date) obj;
    	}
    	return null;
    }
}
