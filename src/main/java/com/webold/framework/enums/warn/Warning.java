package com.webold.framework.enums.warn;

import com.webold.framework.utility.view.Message;
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
