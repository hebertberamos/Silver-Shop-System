package com.web.hevepratas.servicies;

import com.web.hevepratas.dtos.SaleItemDTO;
import com.web.hevepratas.entities.Sale;
import com.web.hevepratas.entities.SaleItem;
import com.web.hevepratas.entities.User;
import com.web.hevepratas.exceptions.ResourceNotFoundException;
import com.web.hevepratas.repositories.SaleRepository;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SaleService {

    private final SaleRepository repository;
    private final ProductService productService;


    public String sale(Authentication auth, List<SaleItemDTO> items) {
        Sale sale = new Sale();
        List<SaleItem> saleItems = new ArrayList<>();
        User customer = null;
        String retResponse = null;

        try{
            customer = (User) auth.getPrincipal();

            for(SaleItemDTO item : items) {
                SaleItem saleItem = new SaleItem();
                saleItem.setProduct(productService.returnProductById(item.getProductId()));
                saleItem.setQuantity(item.getQuantity());
                saleItem.setSale(sale);

                saleItems.add(saleItem);
            }

            sale.setCustomer(customer);
            sale.setItems(saleItems);
            sale.totalAmount(saleItems);

            //TODO: include the process to do the sale before save the sale object to the database
            repository.save(sale);
            retResponse = "Compra realizada com sucesso.";

            productService.decreaseProductQuantity(saleItems, customer);
        }
        catch (ResourceNotFoundException e) {
            //TODO: Exception when something in before process is not found.
            throw new ResourceNotFoundException(e.getMessage());
        }
        catch (Exception e) {
            //TODO: Create exception treatment
            System.out.println("Errozinho pra tu tratar: " + e);
        }

        return retResponse;
    }
}