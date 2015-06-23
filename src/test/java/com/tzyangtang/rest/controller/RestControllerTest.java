package com.tzyangtang.rest.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.mockito.Mockito.*;

import java.sql.SQLException;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.tzyangtang.rest.model.Patient;
import com.tzyangtang.rest.services.PatientService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({ "file:src/test/resources/applicationContext.xml" })
public class RestControllerTest {

	@Autowired
	private WebApplicationContext wac;

	@Autowired
	private PatientService patientServiceMock;

	private MockMvc mockMvc;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		Mockito.reset(patientServiceMock);
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	public void testGetPatientsMethod() throws Exception {

		Patient fake = new Patient();
		fake.setFirstName("john1");
		fake.setLastName("doe");
		fake.setId("8888");

		when(patientServiceMock.getPatients()).thenReturn(Arrays.asList(fake));

		mockMvc.perform(get("/rest/patients").accept(MediaType.ALL))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].firstName").value("john1"));
	}

	@Test
	public void testGlobalExceptionHandlerReturn400() throws Exception {

		when(patientServiceMock.getPatients()).thenThrow(
				new SQLException("I am a fake SQL exception"));
		mockMvc.perform(get("/rest/patients")).andExpect(
				status().isBadRequest());
	}

	@Test
	public void testSavePatientMethod() throws Exception {
		Patient fake = new Patient();
		fake.setFirstName("john1");
		fake.setLastName("doe");
		fake.setId("8888");

		mockMvc.perform(
				post("/rest/patients").contentType(
						TestUtil.APPLICATION_JSON_UTF8).content(
						TestUtil.convertObjectToJsonBytes(fake)))
				.andDo(print()).andExpect(status().isCreated());
	}
}
