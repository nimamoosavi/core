package app.ladderproject.core.enums.message;

import app.ladderproject.core.utility.view.Message;

public enum Messages implements Message {

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
