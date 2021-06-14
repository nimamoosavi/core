package com.nicico.cost.framework.enums.warn;

import com.nicico.cost.framework.utility.view.Message;
import lombok.Getter;

@Getter
public enum Warning implements Message {
    DEPRECATED {
        @Override
        public String key() {
            return this.name();
        }
    },
    VERSION {
        @Override
        public String key() {
            return this.name();
        }
    }
}
