package com.nicico.cost.framework.utility.response;

import com.nicico.cost.framework.domain.dto.BaseDTO;
import com.nicico.cost.framework.utility.request.Message;

/**
 * @author nima
 * @apiNote this class used for get all Data From Properties Resources
 */
public interface ApplicationResource {

    /**
     * @param resourceText is the Key From Resource
     * @return the Object Of Value
     */
    BaseDTO<String> getResourceData(String resourceText);

    /**
     * @param resourceText is the Key From Resource
     * @return the value Of Value
     */
    String getResourceText(String resourceText);

    /**
     * @param message is the Parent of  From Resource {@link com.nicico.cost.framework.utility.request.Message}
     * @return the value Of Value
     */
    String getResourceText(Message message);

    /**
     * @param resourceText is the Key From Resource
     * @return the Object Of Value
     */
    BaseDTO<String> getResourcesData(String resourceText);


}
