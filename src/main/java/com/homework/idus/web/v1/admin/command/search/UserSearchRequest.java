package com.homework.idus.web.v1.admin.command.search;

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
