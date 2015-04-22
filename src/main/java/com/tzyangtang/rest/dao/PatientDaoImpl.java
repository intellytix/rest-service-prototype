package com.tzyangtang.rest.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.tzyangtang.rest.model.Patient;

public class PatientDaoImpl implements PatientDao {

	@Autowired
	SessionFactory sessionFactory;

	public List<Patient> getPatients() throws Exception {
		Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(Patient.class).setMaxResults(10).list();
	}

	@Override
	public void savePatient(Patient patient) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(patient);
	}

	@Override
	public void deletePatient(String id) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		Object o = session.load(Patient.class, id);
		session.delete(o);
	}

	@Override
	public Patient getPatient(String id) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		return (Patient) session.get(Patient.class, id);
	}
}
