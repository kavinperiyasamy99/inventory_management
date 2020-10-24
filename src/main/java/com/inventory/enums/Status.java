package com.inventory.enums;

import com.inventory.constants.MessageCodes;
import com.inventory.util.CommonUtils;

public enum Status {
    Active(1), InActive(0);

    int status;

    Status(int status) {
        this.status = status;
    }

    public int getValue() {
        return status;
    }

    public static Status getStatus(int status) {
        Status status1 = null; // Default
        for (Status item : Status.values()) {
            if (item.getValue() == status) {
                status1 = item;
                break;
            }
        }
        if (CommonUtils.checkIsNull(status1)) {
            throw new IllegalArgumentException(MessageCodes.INVALID_STATUS);
        }
        return status1;

    }
}
