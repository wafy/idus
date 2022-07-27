package com.homework.idus.core.user.query;

import com.homework.idus.web.v1.admin.command.search.SearchKey;

public interface UserSearchDescription {

    SearchKey getSearchKey();

    String getSearchValue();
}
