package com.lslm.ordersapi.controllers;

import com.lslm.ordersapi.clients.StockClient;
import com.lslm.ordersapi.entities.ProductStock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class OrderControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    StockClient stockClient;

    @Test
    public void shouldCreateOrderWhenQuantityIsAvailable() throws Exception {
        ProductStock productStock = new ProductStock();
        productStock.setProductId(UUID.fromString("53132797-9371-4c18-b90a-ee1b35863ef5"));
        productStock.setAvailableQuantity(20);

        when(stockClient.getProductStock(UUID.fromString("53132797-9371-4c18-b90a-ee1b35863ef5"))).thenReturn(productStock);

        String requestBody = """
            {
                "productId": "53132797-9371-4c18-b90a-ee1b35863ef5",
                "quantity": 12
            }
        """;

        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/orders")
                .content(requestBody)
                .contentType("application/json")).andExpect(status().isCreated());
    }

    @Test
    public void shouldCreateOrderWhenTheAvailableQuantityIsTheSame() throws Exception {
        ProductStock productStock = new ProductStock();
        productStock.setProductId(UUID.fromString("53132797-9371-4c18-b90a-ee1b35863ef5"));
        productStock.setAvailableQuantity(10);

        when(stockClient.getProductStock(UUID.fromString("53132797-9371-4c18-b90a-ee1b35863ef5"))).thenReturn(productStock);

        String requestBody = """
            {
                "productId": "53132797-9371-4c18-b90a-ee1b35863ef5",
                "quantity": 10
            }
        """;

        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/orders")
                .content(requestBody)
                .contentType("application/json")).andExpect(status().isCreated());
    }

    @Test
    public void shouldNotCreateOrderDueToUnavailableQuantity() throws Exception {
        ProductStock productStock = new ProductStock();
        productStock.setProductId(UUID.fromString("53132797-9371-4c18-b90a-ee1b35863ef5"));
        productStock.setAvailableQuantity(5);

        when(stockClient.getProductStock(UUID.fromString("53132797-9371-4c18-b90a-ee1b35863ef5"))).thenReturn(productStock);

        String requestBody = """
            {
                "productId": "53132797-9371-4c18-b90a-ee1b35863ef5",
                "quantity": 10
            }
        """;

        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/orders")
                .content(requestBody)
                .contentType("application/json")).andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void shouldNotCreateOrderDueToUnavailableProduct() throws Exception {
        when(stockClient.getProductStock(UUID.fromString("53132797-9371-4c18-b90a-ee1b35863ef5"))).thenReturn(null);

        String requestBody = """
            {
                "productId": "53132797-9371-4c18-b90a-ee1b35863ef5",
                "quantity": 10
            }
        """;

        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/orders")
                .content(requestBody)
                .contentType("application/json")).andExpect(status().isUnprocessableEntity());
    }
}
