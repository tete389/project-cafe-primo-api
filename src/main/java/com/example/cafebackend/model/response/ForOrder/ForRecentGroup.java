package com.example.cafebackend.model.response.ForOrder;

import java.util.List;

import lombok.Data;

@Data
public class ForRecentGroup {
    private List<ForRecentProduct> recentProduct;

    private List<ForRecentMaterail> recentMaterail;

    private List<ForRecentOption> recentOption;
}
