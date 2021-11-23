package com.mercadolibre.demo.service;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.mercadolibre.demo.dto.BuyerDataDTO;
import com.mercadolibre.demo.dto.BuyerDataSaveDTO;
import com.mercadolibre.demo.dto.ViaCepDTO;
import com.mercadolibre.demo.model.Buyer;
import com.mercadolibre.demo.repository.BuyerDataRepository;
import com.mercadolibre.demo.repository.BuyerRepository;

import static org.junit.Assert.*;

public class BuyerDataServiceTest {

	BuyerDataRepository mockDataRepository = Mockito.mock(BuyerDataRepository.class);
	BuyerRepository mockBuyerRepository = Mockito.mock(BuyerRepository.class);

	BuyerDataService buyerDataService = new BuyerDataService(mockDataRepository, mockBuyerRepository);

	@Test
	void testFindCepNoSuccess() {

		Buyer buyer = new Buyer();
		buyer.setIdBuyer(1L);
		buyer.setName("Ayrton");
		buyer.setLastName("Senna");

		ViaCepDTO dto = new ViaCepDTO();
		dto.setCep(null);

		Mockito.when(mockBuyerRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.of(buyer));
		Throwable exceptionThatWasThrown = assertThrows(Exception.class, () -> {
			buyerDataService.findCep(dto.getCep());
		});

		assertTrue(exceptionThatWasThrown.getMessage(), true);
	}

	@Test
	void testFindCepWithSuccess() {

		String CEP = "95670-000";

		buyerDataService.findCep(CEP);

		assertNotNull(CEP);
	}

	@Test
	void testGetBuyerWithSuccess() throws Exception {

		Buyer buyer = new Buyer();
		buyer.setIdBuyer(1L);
		buyer.setName("Ayrton");
		buyer.setLastName("Senna");

		BuyerDataDTO buyerDataDTO = new BuyerDataDTO();
		buyerDataDTO.setIdBuyer(buyer.getIdBuyer());

		Mockito.when(mockBuyerRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.of(buyer));
		buyerDataService.getBuyer(buyerDataDTO);

		assertNotNull(buyerDataDTO.getIdBuyer());

	}

	@Test
	void testListCepNoSuccess() {

		ViaCepDTO dto = new ViaCepDTO() ;
		dto.setCep(null);

		Throwable exceptionThatWasThrown = assertThrows(Exception.class, () -> {
			buyerDataService.listCep(dto.getCep());
		});

		assertTrue(exceptionThatWasThrown.getMessage(), true);
	}

	@Test
	void testListCepWithSuccess() throws Exception {

		ViaCepDTO dto = new ViaCepDTO() ;
		dto.setCep("04028-000");
		dto.setLogradouro("Avenida Ibirapuera - até 1760 - lado par");
		dto.setComplemento("");
		dto.setBairro("Indianópolis");
		dto.setLocalidade("São Paulo");
		dto.setUf("SP");

		buyerDataService.listCep(dto.getCep());

		assertNotNull(dto.getCep());
		assertNotNull(dto.getLogradouro());
		assertNotNull(dto.getComplemento());
		assertNotNull(dto.getBairro());
		assertNotNull(dto.getLocalidade());
		assertNotNull(dto.getUf());
	}


	@Test
	void testSaveNoSuccess() throws Exception {

		Buyer buyer = new Buyer();
		buyer.setIdBuyer(1L);
		buyer.setName("Ayrton");
		buyer.setLastName("Senna");


		BuyerDataSaveDTO dto = new BuyerDataSaveDTO();
		dto.setIdBuyer(null);

		Mockito.when(mockBuyerRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.of(buyer));

		Throwable exceptionThatWasThrown = assertThrows(Exception.class, () -> {
			buyerDataService.save(dto);
		});

		assertTrue(exceptionThatWasThrown.getMessage(), true);


	}

	@Test
	void testSaveWithSuccess() throws Exception {

		Buyer buyer = new Buyer();
		buyer.setIdBuyer(1L);
		buyer.setName("Ayrton");
		buyer.setLastName("Senna");


		BuyerDataSaveDTO dto = new BuyerDataSaveDTO();
		dto.setIdBuyer(buyer.getIdBuyer());
		dto.setCep("59180-000");
		dto.setReferencia("Ao lado da Padaria Dona Maria");
		dto.setEmail("senna@foobar.com");
		dto.setTelephone("(11)0000-0001");
		dto.setCellphone("(11)9 90000-0001");

		Mockito.when(mockBuyerRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.of(buyer));

		buyerDataService.save(dto);
		
		assertNotNull(dto.getCep());
		assertNotNull(dto.getReferencia());
		assertNotNull(dto.getEmail());
		assertNotNull(dto.getTelephone());
		assertNotNull(dto.getCellphone());
	}










}
