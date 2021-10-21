package com.example.schoolmangement.pages;

import lombok.Data;
import org.springframework.data.domain.Sort;

@Data
public class Pages {
    public int pageStart;
    public int pageSize = 20;
    public Sort.Direction sortDirection = Sort.Direction.ASC;
    public String sortBy = "lastName";

    public int setPageStart(int pageStart) {
        return this.pageStart = pageStart;
    }

    public int getPageSize() {
        return pageSize;
    }
}
