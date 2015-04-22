package com.tzyangtang.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.tzyangtang.rest.model.Patient;
import com.tzyangtang.rest.model.Status;
import com.tzyangtang.rest.services.PatientService;

@Controller
@RequestMapping("/rest")
public class RestController {
	@Autowired
	PatientService patientService;

	@RequestMapping(value = "/patients", method = RequestMethod.GET)
	public @ResponseBody
	List<Patient> getPatients() {

		List<Patient> patients = null;
		try {
			patients = patientService.getPatients();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return patients;
	}

	@RequestMapping(value = "/patients", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public Status savePatient(@RequestBody Patient patient) {
		try {
			patientService.savePatient(patient);
			return new Status(1, "Patient updated Successfully !");
		} catch (Exception e) {
			// e.printStackTrace();
			return new Status(0, e.toString());
		}
	}

	@RequestMapping(value = "/patient/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Status deletePatient(@PathVariable String id) {
		try {
			patientService.deletePatient(id);
			return new Status(1, "Patient deleted Successfully!");
		} catch (Exception e) {
			// e.printStackTrace();
			return new Status(0, e.toString());
		}
	}

	@RequestMapping(value = "/patient/{id}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Patient getPatient(@PathVariable String id) {
		try {
			return patientService.getPatient(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
