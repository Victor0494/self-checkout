package com.checkout.mobile.infra.persistence.product;

import com.checkout.mobile.infra.dto.PopularProductsDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, String> {

    ProductEntity findProductByBarCode(String barCode);

    @Query("SELECT new com.checkout.mobile.infra.dto.PopularProductsDTO(p.name, p.price, p.imgPath) FROM ProductEntity p")
    List<PopularProductsDTO> findAllPopularProduct();
}
