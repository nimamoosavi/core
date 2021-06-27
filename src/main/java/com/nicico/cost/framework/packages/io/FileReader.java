package com.nicico.cost.framework.packages.io;

import com.nicico.cost.framework.domain.dto.BaseDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * @param <R> type of excel service response
 * @author seyyed
 * @version 0.8
 */
public interface FileReader<R> {
    BaseDTO<R> readFile(MultipartFile multipartFile);
    IOResponse readFile(InputStream in);
}
