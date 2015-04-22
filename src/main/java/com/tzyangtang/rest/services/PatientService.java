package com.tzyangtang.rest.services;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.tzyangtang.rest.model.Patient;

public interface PatientService {
	@Transactional(readOnly = true)
	public List<Patient> getPatients() throws Exception;

	@Transactional
	public void savePatient(Patient patient) throws Exception;

	@Transactional
	public void deletePatient(String id) throws Exception;

	@Transactional(readOnly = true)
	public Patient getPatient(String id) throws Exception;
}
