package com.nicico.cost.framework.packages.io;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Map;

/**
 * ExcelResponse viewModel of excel data import
 * @author seyyed
 * @version 0.8
 */
@Data
@Accessors(chain = true)
public class IOResponse {
    List<Object> data;
    List<Map<String, String>> errLines;
}
