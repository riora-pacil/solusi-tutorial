package apap.tutorial.shapee.repository;

import apap.tutorial.shapee.model.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductDb extends JpaRepository<ProductModel, Long> {
    Optional<ProductModel> findById(Long id);
    List<ProductModel> findByStoreModelId(Long storeId);
}
