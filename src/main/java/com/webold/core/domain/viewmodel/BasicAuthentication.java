package com.webold.core.domain.viewmodel;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BasicAuthentication {
    String userName;
    String passWord;
}
