package com.homework.idus.web.v1.admin.search.query;

import com.homework.idus.core.user.query.UserSearchDescription;

public class UserSearchRequest implements UserSearchDescription {


    private SearchKey searchKey;


    private String searchValue;


    private int page;


    private int size;


    public UserSearchRequest(SearchKey searchKey,
                             String searchValue,
                             int page,
                             int size) {
        this.searchKey = searchKey;
        this.searchValue = searchValue;
        this.page = page;
        this.size = size;
    }


    public SearchKey getSearchKey() {
        return searchKey == null ? SearchKey.NULL_KEY : searchKey;
    }


    public String getSearchValue() {
        return searchValue;
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
