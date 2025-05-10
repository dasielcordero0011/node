package com.santander.processinstancevisualizer.domain.model;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaginatedListResponse {
    private int totalItems;
    private int totalPages;
    private int currentPage;
    private List<ProcessInstance> items;
}
