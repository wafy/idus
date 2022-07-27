package com.homework.idus.core.user.query;

import com.homework.idus.web.v1.admin.search.query.SearchKey;

public interface UserSearchDescription {

    SearchKey getSearchKey();

    String getSearchValue();
}
