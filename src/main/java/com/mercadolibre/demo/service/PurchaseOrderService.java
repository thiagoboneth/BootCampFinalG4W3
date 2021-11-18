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

	public Optional<PurchaseOrder> findById(Long id) {
		return purchaseOrderRepository.findById(id);
	}

	public Optional<SalesAd> getSalesAd(ItemOfProduct2DTO dto) {
		Optional<SalesAd> salesAd = salesAdRepository.findById(dto.getIdSalesAd());
		return salesAd;
	}

	public Optional<Buyer> getBuyer(PurchaseOrderDTO dto) {
		Optional<Buyer> buyer = buyerRepository.findById(dto.getIdBuyer());
		return buyer;
	}

	public PriceDTO priceList(List<ItemOfProduct> list) throws Exception {
		double value = 0.0;
		for (ItemOfProduct item: list) {
			List<BatchStock> batchStockList = batchStockRepository.batchStockList(item.getSalesAd().getId());
			value += item.getSalesAd().getPrice() * item.getQuantity();
			DecrementQuantity(item,batchStockList);
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
			}
		}
	}

	public List<ItemOfProduct> convertItemOfProduct(List<ItemOfProduct2DTO> dto, PurchaseOrder purchOrder) throws Exception {

		List<ItemOfProduct> listOfProducts = new ArrayList<>();
		for (ItemOfProduct2DTO item : dto) {
			if (getSalesAd(item).isPresent()) {
				ItemOfProduct product = new ItemOfProduct(item.getQuantity(), getSalesAd(item).get(), purchOrder);
				listOfProducts.add(product);
			} else{
				throw new Exception("Existe valores nulos ou vázios em itemOfProduct");
			}
		}
		return listOfProducts;
	}

	public PurchaseOrder convertPurchaseToDTO(PurchaseOrderDTO dto) throws Exception {
		if (getBuyer(dto).isPresent()) {
			PurchaseOrder purchaseOrder = new PurchaseOrder();
			purchaseOrder.setIdBuyer(getBuyer(dto).get());
			List<ItemOfProduct> itemOfProducts = convertItemOfProduct(dto.getItemOfProduct(), purchaseOrder);
			purchaseOrder.setItemOfProduct(itemOfProducts);
			return purchaseOrder;
		} else {
			throw new Exception("Erro no carrinho");
		}
	}
}