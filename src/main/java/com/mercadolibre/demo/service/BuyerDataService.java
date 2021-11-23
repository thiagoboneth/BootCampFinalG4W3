package com.mercadolibre.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.mercadolibre.demo.dto.BuyerDataDTO;
import com.mercadolibre.demo.dto.BuyerDataSaveDTO;
import com.mercadolibre.demo.dto.ViaCepDTO;
import com.mercadolibre.demo.model.Buyer;
import com.mercadolibre.demo.model.BuyerData;
import com.mercadolibre.demo.repository.BuyerDataRepository;
import com.mercadolibre.demo.repository.BuyerRepository;

@Service
public class BuyerDataService {

	private BuyerDataRepository addressRepository;
	private BuyerRepository buyerRepository;

	@Autowired
	public BuyerDataService(BuyerDataRepository addressRepository, BuyerRepository buyerRepository) {
		this.addressRepository = addressRepository;
		this.buyerRepository = buyerRepository;
	}


	public BuyerData save(BuyerDataSaveDTO dto) throws Exception {
		if (dto.getIdBuyer() != null) {
			BuyerDataDTO buyerDataDTO = this.findCep(dto.getCep());
			buyerDataDTO.setIdBuyer(dto.getIdBuyer());
			buyerDataDTO.setReferencia(dto.getReferencia());
			buyerDataDTO.setEmail(dto.getEmail());
			buyerDataDTO.setTelephone(dto.getTelephone());
			buyerDataDTO.setCellphone(dto.getCellphone());
			BuyerData buyerData = convertBuyerData(buyerDataDTO);
			return addressRepository.save(buyerData);
		} else {
			throw new Exception("Id do Buyer não está cadastrado");
		}
	}

	public BuyerDataDTO findCep(String cep) {
		RestTemplate restTemplate = new RestTemplate();
		String URL = "https://viacep.com.br/ws/{cep}/json/";
		return restTemplate.getForObject(URL, BuyerDataDTO.class, cep);
	}

	public ViaCepDTO listCep(String cep) throws Exception {
		if(cep != null){
			BuyerDataDTO dto = this.findCep(cep);
			return this.convertCepDTO(dto);
		}else{
			throw new Exception("Cep invalido");
		}
	}

	public Optional<Buyer> getBuyer(BuyerDataDTO dto) throws Exception {
			Optional<Buyer> buyer = buyerRepository.findById(dto.getIdBuyer());
			return buyer;
	}
		
	private ViaCepDTO convertCepDTO(BuyerDataDTO dto) throws Exception {
		if(dto.getCep() != null) {
			ViaCepDTO viaCep = new ViaCepDTO();
			viaCep.setCep(dto.getCep());
			viaCep.setLogradouro(dto.getLogradouro());
			viaCep.setComplemento(dto.getComplemento());
			viaCep.setBairro(dto.getBairro());
			viaCep.setLocalidade(dto.getLocalidade());
			viaCep.setUf(dto.getUf());
			return viaCep;
		} else {
			throw new Exception("Cep inválido");
		}
	}
	
	public BuyerData convertBuyerData(BuyerDataDTO dto) throws Exception {
		if (getBuyer(dto).isPresent()) {
			BuyerData data = new BuyerData();
			data.setBuyer(getBuyer(dto).get());
			data.setCep(dto.getCep());
			data.setLogradouro(dto.getLogradouro());
			data.setComplemento(dto.getComplemento());
			data.setReferencia(dto.getReferencia());
			data.setBairro(dto.getBairro());
			data.setLocalidade(dto.getLocalidade());
			data.setUf(dto.getUf());
			data.setEmail(dto.getEmail());
			data.setTelephone(dto.getTelephone());
			data.setCellphone(dto.getCellphone());
			return data;
		} else {
			throw new Exception("Id do Buyer não está cadastrado");
		}
	}
}
