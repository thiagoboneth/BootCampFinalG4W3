package com.mercadolibre.demo.service;

import com.mercadolibre.demo.dto.ItemOfProduct2DTO;
import com.mercadolibre.demo.dto.PurchaseOrderDTO;
import com.mercadolibre.demo.dto.response.PriceDTO;
import com.mercadolibre.demo.model.*;
import com.mercadolibre.demo.repository.BatchStockRepository;
import com.mercadolibre.demo.repository.BuyerRepository;
import com.mercadolibre.demo.repository.PurchaseOrderRepository;
import com.mercadolibre.demo.repository.SalesAdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PurchaseOrderService {

	private PurchaseOrderRepository purchaseOrderRepository;
	private BuyerRepository buyerRepository;
	private SalesAdRepository salesAdRepository;
	private BatchStockRepository batchStockRepository;

	@Autowired
	public PurchaseOrderService(PurchaseOrderRepository purchaseOrderRepository, BuyerRepository buyerRepository,
			SalesAdRepository salesAdRepository, BatchStockRepository batchStockRepository) {
		this.purchaseOrderRepository = purchaseOrderRepository;
		this.buyerRepository = buyerRepository;
		this.salesAdRepository = salesAdRepository;
		this.batchStockRepository = batchStockRepository;
	}


	public PurchaseOrder save(PurchaseOrderDTO dto) throws Exception {
		PurchaseOrder purchaseOrder = convertPurchaseToDTO(dto);
		return purchaseOrderRepository.save(purchaseOrder);
	}

	public List<PurchaseOrder> list() {
		return purchaseOrderRepository.findAll();
	}

	public Optional<PurchaseOrder> findById(Long id) {
		return purchaseOrderRepository.findById(id);
	}

	public PurchaseOrder update(PurchaseOrderDTO dto, Long id) throws Exception {
		Optional<PurchaseOrder> existPurchaseOrder = findById(id);
		if (existPurchaseOrder.isPresent()) {
			PurchaseOrder purchaseOrder = convertPurchaseToDTO(dto);
			purchaseOrder.setId(id);
			return purchaseOrderRepository.saveAndFlush(purchaseOrder);
		} else {
			throw new Exception("Id não cadastrado");
		}
	}

	public Optional<SalesAd> getSalesAd(ItemOfProduct2DTO dto) {
		Optional<SalesAd> salesAd = salesAdRepository.findById(dto.getIdSalesAd());
		return salesAd;
	}

	public Optional<Buyer> getBuyer(PurchaseOrderDTO dto) {
		Optional<Buyer> buyer = buyerRepository.findById(dto.getIdBuyer());
		return buyer;
	}


	public List<ItemOfProduct> convertItemOfProduct(List<ItemOfProduct2DTO> dto, PurchaseOrder purchOrder) throws Exception {

		List<ItemOfProduct> listOfProducts = new ArrayList<>();
		for (ItemOfProduct2DTO item : dto) {
			try {
				if (getSalesAd(item).isPresent()) {
					ItemOfProduct product = new ItemOfProduct(item.getQuantity(), getSalesAd(item).get(), purchOrder);
					listOfProducts.add(product);
				}
			} catch (Exception e) {
				throw new Exception("Existe valores nulos ou vázios em itemOfProduct");
			}
		}
		return listOfProducts;
	}

	public PriceDTO priceList(List<ItemOfProduct> list) throws Exception {
		Long product = 0L;
		double value = 0.0;
		for (ItemOfProduct item: list) {
			List<BatchStock> batchStockList = batchStockRepository.batchStockList(item.getSalesAd().getId());
			value += item.getSalesAd().getPrice() * item.getQuantity();
			if(batchStockList != null) {
				DecrementQuantity(item,batchStockList);
			}
		}
		PriceDTO priceDTO = new PriceDTO();
		priceDTO.setTotalPrice(value);
		return priceDTO;
	}

	public void DecrementQuantity(ItemOfProduct item, List<BatchStock> batchStockList) throws Exception {
		for (BatchStock batchStock : batchStockList) {
			if (item.getQuantity() <= batchStock.getCurrentQuantity()) {
				batchStock.setCurrentQuantity(batchStock.getCurrentQuantity() - item.getQuantity());
				batchStockRepository.saveAndFlush(batchStock);
				return;
			}
		}
	}

	public PurchaseOrder convertPurchaseToDTO(PurchaseOrderDTO dto) throws Exception {
		PurchaseOrder purchaseOrder = new PurchaseOrder();
		Optional<Buyer> buyer = buyerRepository.findById(dto.getIdBuyer());
		purchaseOrder.setIdBuyer(buyer.get());
		if (getBuyer(dto).isPresent()) {
			List<ItemOfProduct> itemOfProducts = convertItemOfProduct(dto.getItemOfProduct(), purchaseOrder);
			purchaseOrder.setItemOfProduct(itemOfProducts);
			return purchaseOrder;
		}
		throw new Exception("Erro no carrinho");
	}
}