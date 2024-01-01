package com.synonys.main;

import com.synonys.entity.Synonyms;
import com.synonys.entity.User;
import com.synonys.exception.CommonException;
import com.synonys.repository.SynonymsRepo;
import com.synonys.repository.UserRepo;
import com.synonys.util.CommonUtils;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = SpringApplicationTests.class)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-integrationtest.properties")
public class ChargeDataTests {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private SynonymsRepo synonymsRepo;
    @Autowired
    private UserRepo userRepo;


    // write test cases here
    @Test
    public void givenEmployees_whenGetEmployees_thenStatus200()
            throws Exception {
        Synonyms synonyms = new Synonyms();

     /*   User user = userRepo.findByEmailIdAndUniqueId("kk8415906@gmail.com", "1234");
        if (CommonUtils.isNull(user)) {
            throw new CommonException(HttpStatus.NOT_FOUND, "user not found");
        }
        synonyms.setSessionId(CommonUtils.generateSessionId());
        synonyms.setStartTime(LocalDateTime.now());
        synonyms.setEndTime(LocalDateTime.now().plusMinutes(30));
        synonyms.setVehicleId("SDF121212WWQQ");
        synonyms.setCost(BigDecimal.valueOf(200L));
        synonyms.setUser(user);
        synonymsRepo.save(synonyms);*/

        mvc.perform(MockMvcRequestBuilders
                        .post("/api/v0/chargeData")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}