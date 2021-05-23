package com.nicico.cost.framework.utility;

import com.nicico.cost.framework.domain.dto.BaseDTO;

public interface ApplicationResource {

    BaseDTO<String> getResourceData(String resourceText);

    String getResourceText(String resourceText);

    BaseDTO<String> getResourcesData(String resourceText);


}
