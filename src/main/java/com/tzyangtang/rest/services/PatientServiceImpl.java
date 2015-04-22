package com.tzyangtang.rest.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.tzyangtang.rest.dao.PatientDao;
import com.tzyangtang.rest.model.Patient;

public class PatientServiceImpl implements PatientService {

	@Autowired
	PatientDao dataDao;

	public List<Patient> getPatients() throws Exception {
		return dataDao.getPatients();
	}

	@Override
	@Transactional
	public void savePatient(Patient patient) throws Exception {
		dataDao.savePatient(patient);
	}

	@Override
	@Transactional
	public void deletePatient(String id) throws Exception {
		dataDao.deletePatient(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Patient getPatient(String id) throws Exception {
		return dataDao.getPatient(id);
	}
}
