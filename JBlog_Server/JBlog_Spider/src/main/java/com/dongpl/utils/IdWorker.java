package com.dongpl.utils;

import lombok.Data;

@Data
public class IdWorker {

    private Integer id;

    private Integer nextId;

    public IdWorker(Integer id, Integer nextId) {
        this.id = id;
        this.nextId = nextId;
    }

    public Integer nextId() {
        return this.nextId;
    }

}
