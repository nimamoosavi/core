package com.webold.framework.enums.message;

import com.webold.framework.utility.view.Message;

public enum MessageEnum implements Message {

    SUCCESS {
        @Override
        public String key() {
            return this.name();
        }
    },
    CREATED {
        @Override
        public String key() {
            return this.name();
        }
    };
}
