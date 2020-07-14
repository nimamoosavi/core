package ir.webold.framework.service;

import ir.webold.framework.domain.dto.BaseDTO;
import ir.webold.framework.domain.entity.PropertyValue;
import ir.webold.framework.domain.viewmodel.propertyvalue.request.PropertyValueReqVM;
import ir.webold.framework.domain.viewmodel.propertyvalue.response.PropertyValueResVM;
import ir.webold.framework.enums.ExceptionEnum;
import ir.webold.framework.repository.PropertyValueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyValueService extends GeneralService<PropertyValue, PropertyValueReqVM, PropertyValueResVM, Long> {
    @Autowired(required = false)
    PropertyValueRepository propertyValueRepository;


    public BaseDTO<List<PropertyValueResVM>> getByParentCode(String parentCode) {
        List<PropertyValue> allByParentCode = propertyValueRepository.findAllByParentCode(parentCode).orElseThrow(
                () -> applicationException.createApplicationException(ExceptionEnum.NOTFOUNDUSER, HttpStatus.NOT_FOUND)
        );
        List<PropertyValueResVM> propertyValueViewModels = propertyValueMapper.toResponseModel(allByParentCode);
        return successListResponse(propertyValueViewModels);
    }


    public BaseDTO<PropertyValueResVM> getByCode(String code) {
        PropertyValue propertyValue = propertyValueRepository.findByCode(code).orElseThrow(
                () -> applicationException.createApplicationException(ExceptionEnum.NOTFOUNDUSER, HttpStatus.NOT_FOUND)
        );
        PropertyValueResVM propertyValueViewModel = propertyValueMapper.toResponseModel(propertyValue);
        return successResponse(propertyValueViewModel);

    }

    public BaseDTO<List<PropertyValueResVM>> getAllParent() {
        List<PropertyValue> propertyValues = propertyValueRepository.findPropertyValuesByParentCodeIsNull().orElseThrow(
                () -> applicationException.createApplicationException(ExceptionEnum.NOTFOUNDUSER, HttpStatus.NOT_FOUND)
        );
        List<PropertyValueResVM> propertyValueViewModels = propertyValueMapper.toResponseModel(propertyValues);
        return successListResponse(propertyValueViewModels);

    }

}
