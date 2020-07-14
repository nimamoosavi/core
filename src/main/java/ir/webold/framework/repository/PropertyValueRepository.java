package ir.webold.framework.repository;

import ir.webold.framework.domain.entity.PropertyValue;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PropertyValueRepository extends GeneralRepository<PropertyValue, Long> {
    Optional<List<PropertyValue>> findAllByParentCode(String parentCode);

    Optional<PropertyValue> findByCode(String code);

    Optional<List<PropertyValue>> findPropertyValuesByParentCodeIsNull();
}
