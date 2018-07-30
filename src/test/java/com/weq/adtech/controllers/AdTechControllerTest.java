package com.weq.adtech.controllers;

import com.weq.adtech.models.DeliveryModel;
import com.weq.adtech.services.AdTechService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(AdTechController.class)
public class AdTechControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private AdTechService adTechService;

	@Test
	public void givenDeliveryModel_whenAddDeliveryIsCalled_thenReturnSuccess() throws Exception{
		DeliveryModel deliveryModel = new DeliveryModel();
		deliveryModel.setAdvertisementId(12345L);
		deliveryModel.setDeliveryId("12345");
		deliveryModel.setBrowser("test-browser");
		deliveryModel.setOs("test-os");
		deliveryModel.setTime(new Date());

		when(this.adTechService.saveAdDelivery(deliveryModel)).thenReturn(true);

		mockMvc.perform(post("/ads/delivery").contentType("application/json;charset=UTF-8").content(deliveryModel.toString())
				.accept(MediaType.parseMediaType("application/json;charset=UTF-8"))).andExpect(status().isOk());
	}
}
