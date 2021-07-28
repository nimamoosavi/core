package com.webold.framework.utility;

import com.webold.framework.domain.dto.BaseDTO;
import com.webold.framework.utility.view.Message;

/**
 * @author nima
 * @apiNote this class used for get all Data From Properties Resources
 */
public interface ResourceUtility {

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
     * @param message is the Parent of  From Resource {@link Message}
     * @return the value Of Value
     */
    String getResourceText(Message message);

    /**
     * @param resourceText is the Key From Resource
     * @return the Object Of Value
     */
    BaseDTO<String> getResourcesData(String resourceText);


}
