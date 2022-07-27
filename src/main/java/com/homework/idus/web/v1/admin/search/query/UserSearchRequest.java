package com.homework.idus.web.v1.admin.search.query;

import com.homework.idus.core.user.query.UserSearchDescription;

public class UserSearchRequest implements UserSearchDescription {

    private SearchKey searchKey;
    private String searchValue;


    public UserSearchRequest(SearchKey searchKey, String searchValue) {
        this.searchKey = searchKey;
        this.searchValue = searchValue;
    }


    public SearchKey getSearchKey() {
        return searchKey == null ? SearchKey.NULL_KEY : searchKey;
    }


    public String getSearchValue() {
        return searchValue;
    }
}
