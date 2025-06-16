package exercise.specification;

import exercise.dto.ProductParamsDTO;
import exercise.model.Product;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

// BEGIN
@Component
public class ProductSpecification {
    public Specification<Product> build(ProductParamsDTO params) {
        return withCategoryId(params.getCategoryId())
                .and(withTitleCont(params.getTitleCont()))
                .and(withPriceGt(params.getPriceGt()))
                .and(withPriceLt(params.getPriceLt()))
                .and(withRatingGt(params.getRatingGt()));
    }

    private Specification<Product> withCategoryId(Long categoryId) {
        return (root, query, cb) -> {
            if (categoryId == null) {
                return cb.conjunction();
            }
            return cb.equal(root.get("category").get("id"), categoryId);
        };
    }

    private Specification<Product> withTitleCont(String titleCont) {
        return (root, query, cb) -> {
            if (titleCont == null) {
                return cb.conjunction();
            }
            return cb.like(root.get("title"), "%" + titleCont + "%");
        };
    }

    private Specification<Product> withPriceLt(Integer priceLt) {
        return (root, query, cb) -> {
            if (priceLt == null) {
                return cb.conjunction();
            }
            return cb.lessThan(root.get("price"), priceLt);
        };
    }

    private Specification<Product> withPriceGt(Integer priceGt) {
        return (root, query, cb) -> {
            if (priceGt == null) {
                return cb.conjunction();
            }
            return cb.greaterThan(root.get("price"), priceGt);
        };
    }

    private Specification<Product> withRatingGt(Double ratingGt) {
        return (root, query, cb) -> {
            if (ratingGt == null) {
                return cb.conjunction();
            }
            return cb.greaterThan(root.get("rating"), ratingGt);
        };
    }
}
// END
