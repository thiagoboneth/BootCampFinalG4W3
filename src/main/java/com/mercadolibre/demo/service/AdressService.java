package com.mercadolibre.demo.service;

import com.mercadolibre.demo.dto.AdressDTO;
import com.mercadolibre.demo.dto.AdressSaveDTO;
import com.mercadolibre.demo.dto.SellerDTO;
import com.mercadolibre.demo.dto.response.AdressBuscaDTO;
import com.mercadolibre.demo.dto.response.AdressRestDTO;
import com.mercadolibre.demo.model.Adress;
import com.mercadolibre.demo.model.Buyer;
import com.mercadolibre.demo.model.Seller;
import com.mercadolibre.demo.repository.AdressRepository;
import com.mercadolibre.demo.repository.BuyerRepository;
import com.mercadolibre.demo.repository.SellerRepository;
import org.apache.tomcat.jni.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class AdressService {

	private AdressRepository addressRepository;
	private BuyerRepository buyerRepository;

	@Autowired
	public AdressService(AdressRepository addressRepository, BuyerRepository buyerRepository) {
		this.addressRepository = addressRepository;
		this.buyerRepository = buyerRepository;
	}


		public AdressDTO buscarCep(String cep) {
			RestTemplate restTemplate = new RestTemplate();
			String URL = "https://viacep.com.br/ws/{cep}/json/";
			return restTemplate.getForObject(URL, AdressDTO.class, cep);
		}

	public AdressBuscaDTO list(String cep) throws Exception {
		if(cep != null){
			AdressDTO adressDTO = this.buscarCep(cep);
			return this.convertAddressBuscaRestDTO(adressDTO);
		}else{
			throw new Exception("Cep invalido");
		}
	}

	public AdressRestDTO save(AdressSaveDTO dto) throws Exception {
		if(dto.getIdBuyer() != null){
		AdressDTO addressDTO = this.buscarCep(dto.getCep());
		addressDTO.setIdbuyer(dto.getIdBuyer());
		addressDTO.setReferencia(dto.getReferencia());
		addressDTO.setFone1(dto.getFone1());
		addressDTO.setFone2(dto.getFone2());
		Adress address = convertAddressDTO(addressDTO);
		addressRepository.save(address);
		return convertAddressRestDTO(address);
		}else{
			throw new Exception("Id do Buyer não cadastrado");
		}
	}

	public Optional<Buyer> getBuyer(AdressDTO dto) {
		Optional<Buyer> buyer = buyerRepository.findById(dto.getIdbuyer());
		return buyer;
	}


	public Adress convertAddressDTO(AdressDTO dto) throws Exception {
		if(dto.getIdbuyer() != null){
			Adress adress = new Adress(dto.getCep(), dto.getLogradouro(),dto.getReferencia(),
					dto.getComplemento(), dto.getBairro(), dto.getLocalidade(), dto.getUf(), dto.getFone1(), dto.getFone2(), getBuyer(dto).get());
			return adress;
		} else {
			throw new Exception("Id do Buyer não cadastrado");
		}
	}


	public AdressRestDTO convertAddressRestDTO(Adress adress) throws Exception {
			if(adress.getIdBuyer() != null){
				AdressRestDTO adressRestDTO = new AdressRestDTO();
				adressRestDTO.setCep(adressRestDTO.getCep());
				adressRestDTO.setLogradouro(adressRestDTO.getLogradouro());
				adressRestDTO.setComplemento(adressRestDTO.getComplemento());
				adressRestDTO.setBairro(adressRestDTO.getBairro());
				adressRestDTO.setLocalidade(adressRestDTO.getLocalidade());
				adressRestDTO.setUf(adressRestDTO.getUf());
				adressRestDTO.setFone1(adressRestDTO.getFone1());
				adressRestDTO.setFone2(adressRestDTO.getFone2());
				adressRestDTO.setIdbuyer(adressRestDTO.getIdbuyer());
				adressRestDTO.setReferencia(adressRestDTO.getReferencia());
				return adressRestDTO;
			}else{
				throw new Exception("Id do Buyer não cadastrado");
			}
	}


	public AdressBuscaDTO convertAddressBuscaRestDTO(AdressDTO adressDTO) throws Exception {
		if(adressDTO.getIdbuyer() != null){
		AdressBuscaDTO adressRestDTO = new AdressBuscaDTO();
		adressRestDTO.setCep(adressDTO.getCep());
		adressRestDTO.setLogradouro(adressDTO.getLogradouro());
		adressRestDTO.setComplemento(adressDTO.getComplemento());
		adressRestDTO.setBairro(adressDTO.getBairro());
		adressRestDTO.setLocalidade(adressDTO.getLocalidade());
		adressRestDTO.setUf(adressDTO.getUf());
		return adressRestDTO;
		}else{
			throw new Exception("Id do Buyer não cadastrado");
		}
	}
}

