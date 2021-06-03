package com.tecsup.petclinic.web;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import com.tecsup.petclinic.domain.Owner;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class OwnerControllerTest {
private static final ObjectMapper om = new ObjectMapper();
    
	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testGetOwners() throws Exception {

		//int NRO_RECORD = 73;
		int ID_FIRST_RECORD = 1;

		this.mockMvc.perform(get("/owners"))
					.andExpect(status().isOk())
					.andExpect(content()
					.contentType(MediaType.APPLICATION_JSON_UTF8))
		//		    .andExpect(jsonPath("$", hasSize(NRO_RECORD)))
					.andExpect(jsonPath("$[0].id", is(ID_FIRST_RECORD)));
	}
	
	@Test
	public void testFindOwnerOK() throws Exception {

		String FIRST_NAME_OWNER = "George";
		String LAST_NAME_OWNER = "Franklin";
		String ADDRESS = "110 W. Liberty St.";
		String CITY = "Madison";
		String TELEPHONE = "6085551023";
				

		mockMvc.perform(get("/owners/1"))  // Object must be BASIL 
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				//.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id", is(1)))
				.andExpect(jsonPath("$.firstName", is(FIRST_NAME_OWNER)))
				.andExpect(jsonPath("$.lastName", is(LAST_NAME_OWNER)))
				.andExpect(jsonPath("$.address", is(ADDRESS)))
				.andExpect(jsonPath("$.city", is(CITY)))
				.andExpect(jsonPath("$.telephone", is(TELEPHONE)));		
	}

	@Test
	public void testFindOwnerKO() throws Exception {

		mockMvc.perform(get("/owners/666"))
				.andExpect(status().isNotFound());

	}

    @Test
    public void testCreateOwner() throws Exception {
		
    	String FIRST_NAME_OWNER = "Emanuel";
		String LAST_NAME_OWNER = "Ore";
		String ADDRESS = "Sta anita Mz T Lt 8";
		String CITY = "Lima";
		String TELEPHONE = "4596253";
		
		
		
		Owner newOwner = new Owner(FIRST_NAME_OWNER, LAST_NAME_OWNER, ADDRESS, CITY, TELEPHONE);
	
	    mockMvc.perform(post("/owners")
	            .content(om.writeValueAsString(newOwner))
	            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isCreated())
	            //.andExpect(jsonPath("$.id", is(1)))
	            .andExpect(jsonPath("$.firstName", is(FIRST_NAME_OWNER)))
				.andExpect(jsonPath("$.lastName", is(LAST_NAME_OWNER)))
				.andExpect(jsonPath("$.address", is(ADDRESS)))
				.andExpect(jsonPath("$.city", is(CITY)))
				.andExpect(jsonPath("$.telephone", is(TELEPHONE)));	    
	}

    @Test
    public void testDeleteOwner() throws Exception {

    	String FIRST_NAME_OWNER = "Edith";
		String LAST_NAME_OWNER = "Soledad";
		String ADDRESS = "370 W. Liberty St.";
		String CITY = "Lima";
		String TELEPHONE = "45896485";
		
		Owner newOwner = new Owner(FIRST_NAME_OWNER, LAST_NAME_OWNER, ADDRESS, CITY, TELEPHONE);
		
		ResultActions mvcActions = mockMvc.perform(post("/owners")
	            .content(om.writeValueAsString(newOwner))
	            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isCreated());
	            
		String response = mvcActions.andReturn().getResponse().getContentAsString();

		Integer id = JsonPath.parse(response).read("$.id");

        mockMvc.perform(delete("/owners/" + id ))
                /*.andDo(print())*/
                .andExpect(status().isOk());
    }

}