package com.homework.idus.web.v1.admin.search.query;

import com.homework.idus.core.user.query.UserSearchDescription;
import io.swagger.annotations.ApiParam;

public class UserSearchRequest implements UserSearchDescription {

    @ApiParam(value = "검색구분", example = "NAME", allowableValues = "이름: NAME, 이메일: EMAIL ")
    private SearchKey searchKey;

    @ApiParam(value = "검색값", example = "구름이")
    private String searchValue;

    @ApiParam(value = "페이지번호", example = "1")
    private int page;

    @ApiParam(value = "한페이지 보여질 갯수", example = "10")
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
