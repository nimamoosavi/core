package ir.webold.framework.mapper;

import ir.webold.framework.domain.entity.PropertyValue;
import ir.webold.framework.domain.viewmodel.propertyvalue.request.PropertyValueReqVM;
import ir.webold.framework.domain.viewmodel.propertyvalue.response.PropertyValueResVM;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;


@Mapper(componentModel = "spring")
@Component
public interface PropertyValueMapper extends GeneralMapper<PropertyValue, PropertyValueReqVM, PropertyValueResVM> {
}
