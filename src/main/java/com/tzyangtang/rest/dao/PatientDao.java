package com.tzyangtang.rest.dao;

import java.util.List;

import com.tzyangtang.rest.model.Patient;

public interface PatientDao {
	public List<Patient> getPatients() throws Exception;

	public void savePatient(Patient patient) throws Exception;

	public void deletePatient(String id) throws Exception;

	public Patient getPatient(String id) throws Exception;
}
