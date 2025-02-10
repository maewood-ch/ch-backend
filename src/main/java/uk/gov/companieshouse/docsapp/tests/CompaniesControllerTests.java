//package uk.gov.companieshouse.docsapp.tests;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import uk.gov.companieshouse.docsapp.rest.RestResultt;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
//@AutoConfigureMockMvc
//public class HomeControllerTest {
////
////    @Autowired
////    private MockMvc mockMvc;
////
////    @Autowired
////    private ObjectMapper mapper;
////
////    @Test
////    void testListCompanies_noParams() throws Exception {
////        MvcResult mockResult = this.mockMvc.perform(get("/companies"))
////                .andDo(print())
////                .andExpect(status().isOk())
////                .andReturn();
////
////        // Deserialize the response to a List of Company objects
////        RestResult restResult = mapper.readValue(mockResult.getResponse().getContentAsString(), RestResult.class);
////
////        // You can add assertions specific to the "data" returned in the response
////        assertThat(restResult.getData()).isInstanceOf(List.class);
////        assertThat(((List<?>) restResult.getData()).size()).isGreaterThan(0);
////    }
////
////    @Test
////    void testListCompanies_withNamePattern() throws Exception {
////        String namePattern = "Company";
////
////        MvcResult mockResult = this.mockMvc.perform(get("/companies")
////                        .param("namePattern", namePattern))
////                .andDo(print())
////                .andExpect(status().isOk())
////                .andReturn();
////
////        RestResult restResult = mapper.readValue(mockResult.getResponse().getContentAsString(), RestResult.class);
////
////        assertThat(restResult.getData()).isInstanceOf(List.class);
////        assertThat(((List<?>) restResult.getData()).size()).isGreaterThan(0);
////        assertThat(((List<?>) restResult.getData()).get(0).toString()).contains(namePattern);
////    }
////
////    @Test
////    void testListCompanies_withActiveState() throws Exception {
////        Boolean activeState = true;
////
////        MvcResult mockResult = this.mockMvc.perform(get("/companies")
////                        .param("activeState", activeState.toString()))
////                .andDo(print())
////                .andExpect(status().isOk())
////                .andReturn();
////
////        RestResult restResult = mapper.readValue(mockResult.getResponse().getContentAsString(), RestResult.class);
////
////        assertThat(restResult.getData()).isInstanceOf(List.class);
////        assertThat(((List<?>) restResult.getData()).size()).isGreaterThan(0);
////        // Assuming the activeState is correctly filtered based on the activeState parameter
////        assertThat(((List<?>) restResult.getData()).get(0).toString()).contains("activeState=" + activeState);
////    }
////
////    @Test
////    void testListCompanies_withYearOfIncorporation() throws Exception {
////        Integer yearOfIncorporation = 2020;
////
////        MvcResult mockResult = this.mockMvc.perform(get("/companies")
////                        .param("yearOfIncorporation", yearOfIncorporation.toString()))
////                .andDo(print())
////                .andExpect(status().isOk())
////                .andReturn();
////
////        RestResult restResult = mapper.readValue(mockResult.getResponse().getContentAsString(), RestResult.class);
////
////        assertThat(restResult.getData()).isInstanceOf(List.class);
////        assertThat(((List<?>) restResult.getData()).size()).isGreaterThan(0);
////        // Assuming yearOfIncorporation is part of the response and filtered correctly
////        assertThat(((List<?>) restResult.getData()).get(0).toString()).contains("yearOfIncorporation=" + yearOfIncorporation);
////    }
////
////    @Test
////    void testListCompanies_withSorting() throws Exception {
////        CompanyRegistry.Sort sortBy = CompanyRegistry.Sort.NAME;
////
////        MvcResult mockResult = this.mockMvc.perform(get("/companies")
////                        .param("sortBy", sortBy.toString()))
////                .andDo(print())
////                .andExpect(status().isOk())
////                .andReturn();
////
////        RestResult restResult = mapper.readValue(mockResult.getResponse().getContentAsString(), RestResult.class);
////
////        assertThat(restResult.getData()).isInstanceOf(List.class);
////        assertThat(((List<?>) restResult.getData()).size()).isGreaterThan(0);
////        // Assuming sorting is applied correctly
////        assertThat(((List<?>) restResult.getData()).get(0).toString()).contains("companyName");
////    }
//}
