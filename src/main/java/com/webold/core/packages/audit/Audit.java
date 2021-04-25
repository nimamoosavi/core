package com.webold.core.packages.audit;

import com.webold.core.packages.audit.view.AuditVM;

/**
 * @version 1.0.1
 */
public interface Audit {
    /**
     * @param vm request for Log
     * @apiNote log method not Throw Exception
     **/
    void log(AuditVM vm);
}
