package exercise.mapper;

import exercise.dto.ProductCreateDTO;
import exercise.dto.ProductDTO;
import exercise.dto.ProductUpdateDTO;
import exercise.model.Product;
import org.mapstruct.*;

// BEGIN
@Mapper(
        uses = {ReferenceMapper.class, JsonNullableMapper.class},
        componentModel = MappingConstants.ComponentModel.SPRING,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public abstract class ProductMapper {
    @Mapping(target = "categoryId", source = "category.id")
    @Mapping(target = "categoryName", source = "category.name")
    public abstract ProductDTO map(Product product);

    @Mapping(target = "category", source = "categoryId")
    public abstract Product map(ProductCreateDTO productCreateDTO);

    @Mapping(target = "category", source = "categoryId")
    public abstract void update(ProductUpdateDTO productUpdateDTO, @MappingTarget Product product);
}
// END
