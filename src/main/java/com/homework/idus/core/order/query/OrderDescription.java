package com.homework.idus.core.order.query;

import com.homework.idus.axiom.query.Pager;

public interface OrderDescription extends Pager {

    int getPage();
    int getSize();

}
