package com.homework.idus.core.user.query;

import com.homework.idus.axiom.query.Pager;
import com.homework.idus.web.v1.admin.search.query.SearchKey;

public interface UserSearchDescription extends Pager {

    SearchKey getSearchKey();

    String getSearchValue();

    int getPage();

    int getSize();


}
