package com.synonys.response;

import java.io.Serializable;
import java.util.List;

public class GenericPaginationResponse<T> implements Serializable {

    private Long recordCount;
    private List<T> data;

    public GenericPaginationResponse(){}

    public Long getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(Long recordCount) {
        this.recordCount = recordCount;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public GenericPaginationResponse(long recordCount, List<T> response) {
        this.data = response;
        this.recordCount = recordCount;
    }
}
