package com.nicico.cost.framework.enums.exception;

import com.nicico.cost.framework.utility.request.Message;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public enum ExceptionEnum implements Message {
    NOTFOUND {
        @Override
        public String key() {
            return this.name();
        }
    },
    SERVER_DENY {
        @Override
        public String key() {
            return this.name();
        }
    },
    NOT_DEVELOP {
        @Override
        public String key() {
            return this.name();
        }
    },
    INTERNAL_SERVER {
        @Override
        public String key() {
            return this.name();
        }
    },
    NOT_SAVE {
        @Override
        public String key() {
            return this.name();
        }
    },
    NOT_UPDATE {
        @Override
        public String key() {
            return this.name();
        }
    },
    NOT_DELETE {
        @Override
        public String key() {
            return this.name();
        }
    },
    ENVIRONMENT_NOT_FOUND {
        @Override
        public String key() {
            return this.name();
        }
    },
    ACCESS_DENIED {
        @Override
        public String key() {
            return this.name();
        }
    };

}
