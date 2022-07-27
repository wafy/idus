package com.homework.idus.web.v1.admin.order.query;

import com.homework.idus.core.order.query.OrderDescription;
import io.swagger.annotations.ApiParam;
import lombok.Getter;

@Getter
public class OrderRequest implements OrderDescription {
    @ApiParam(value = "페이지번호", defaultValue = "1", example = "1")
    private int page;
    private int size;

    public OrderRequest(int page, int size) {
        this.page = page;
        this.size = size;
    }


    @Override
    public int getPage() {
        return page;
    }

    @Override
    public int getSize() {
        return size;
    }
}
