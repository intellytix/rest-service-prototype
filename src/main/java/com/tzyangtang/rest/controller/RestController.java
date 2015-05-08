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
	List<Patient> getPatients() throws Exception {
		return patientService.getPatients();
	}

	@RequestMapping(value = "/patients", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public void savePatient(@RequestBody Patient patient) throws Exception {
		System.out.println(patient.getFirstName());
		patientService.savePatient(patient);
	}

	@RequestMapping(value = "/patient/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void deletePatient(@PathVariable String id) throws Exception {
		patientService.deletePatient(id);
	}

	@RequestMapping(value = "/patient/{id}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Patient getPatient(@PathVariable String id) throws Exception {
		return patientService.getPatient(id);
	}

}
