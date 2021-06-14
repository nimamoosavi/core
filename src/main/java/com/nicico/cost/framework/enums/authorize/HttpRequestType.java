package com.nicico.cost.framework.enums.authorize;

import com.nicico.cost.framework.utility.view.Message;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum HttpRequestType implements Message {
    NONE {
        @Override
        public String key() {
            return this.name();
        }
    },
    ALL {
        @Override
        public String key() {
            return this.name();
        }
    },
    GET {
        @Override
        public String key() {
            return this.name();
        }
    },
    POST {
        @Override
        public String key() {
            return this.name();
        }
    },
    DELETE {
        @Override
        public String key() {
            return this.name();
        }
    },
    PUT {
        @Override
        public String key() {
            return this.name();
        }
    },
    OPTION {
        @Override
        public String key() {
            return this.name();
        }
    },
}
