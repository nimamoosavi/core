package com.nicico.cost.framework.enums.authorize;

import com.nicico.cost.framework.utility.response.Message;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UnauthorizedType implements Message {
    ALL {
        @Override
        public String key() {
            return this.name();
        }
    }
}
