package com.nicico.cost.framework.packages.audit;

import com.nicico.cost.framework.packages.audit.view.AuditFactory;

/**
 * @version 1.0.1
 * @apiNote you can use AuditFactory class for create AuditVM
 * this implementation has some locations in AuditServiceImpl and you can set Kafka or File {@link com.nicico.cost.audit.enums.Locations}
 * @author nima mousavi
 */
public interface Log {
    /**
     * @param vm request sample view model for Log info
     * @apiNote  method not Throw Exception in application
     **/
    void info(AuditFactory.AuditVM vm);

    /**
     * @param vm request sample view model for Log error
     * @apiNote  method not Throw Exception in application
     **/
    void error(AuditFactory.AuditVM vm);

    /**
     * @param vm request sample view model for Log fatal exception
     * @apiNote  method not Throw Exception in application
     **/
    void fatal(AuditFactory.AuditVM vm);

    /**
     * @param vm request sample view model for Log warning
     * @apiNote  method not Throw Exception in application
     **/
    void warn(AuditFactory.AuditVM vm);
}
