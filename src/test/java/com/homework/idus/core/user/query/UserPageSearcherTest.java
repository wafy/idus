package com.homework.idus.core.user.query;

import com.homework.idus.core.user.Gender;
import com.homework.idus.core.user.TestSupplier;
import com.homework.idus.core.user.command.User;
import com.homework.idus.web.v1.admin.search.query.SearchKey;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

@SpringBootTest
@DisplayName("UserPageSearcher")
class UserPageSearcherTest extends TestSupplier {

    @BeforeEach
    void prepareData() {
        getOrderDeleteAll();
        getUserDeleteAll();
    }

    @Nested
    @DisplayName("findAll 메서드")
    class Describe_findAll_method {

        int totalCount = 10;

        @BeforeEach
        void prepareData() {
            for (int i = 0; i < totalCount; i++) {
                User user = User.builder()
                        .name(i+ "홍길동")
                        .nickname("별명" + i)
                        .email(i + "test@test.com")
                        .password("1234a12341233!A")
                        .mobilePhoneNo("010-2345-111" + i)
                        .gender(Gender.MAN)
                        .build();

                getUserCreator().create(user);
            }
        }

        @Nested
        @DisplayName("회원 전체 조회 요청이면")
        class Context_findAll {

            @Test
            @DisplayName("등록된 회원 수 전체를 리턴한다")
            void it_returns_total_user() {
                UserSearchDescription command = new UserSearchDescription() {

                    @Override
                    public SearchKey getSearchKey() {
                        return null;
                    }

                    @Override
                    public String getSearchValue() {
                        return null;
                    }

                    @Override
                    public int getPage() {
                        return 1;
                    }

                    @Override
                    public int getSize() {
                        return 10;
                    }
                };

                Page<User> savedUser = getUserPageSearcher().findAll(command);
                Assertions.assertEquals(totalCount, savedUser.getTotalElements());
            }
        }

        @Nested
        @DisplayName("사용자 이름 홍길동1로 검색 요청이면")
        class Context_request_name {

            @Test
            @DisplayName("1홍길동의 row를 한개 반환한다")
            void it_returns_name() {
                String searchName = "1홍길동";
                UserSearchDescription command = new UserSearchDescription() {
                    @Override
                    public SearchKey getSearchKey() {
                        return SearchKey.NAME;
                    }

                    @Override
                    public String getSearchValue() {
                        return searchName;
                    }

                    @Override
                    public int getPage() {
                        return 0;
                    }

                    @Override
                    public int getSize() {
                        return 10;
                    }
                };


                Page<User> savedUser = getUserPageSearcher().findAll(command);
                Assertions.assertEquals(totalCount, savedUser.getSize());
                Assertions.assertEquals(searchName, savedUser.getContent().get(0).getName());
            }
        }

        @Nested
        @DisplayName("이메일 1test@test.com의 조회 요청이면")
        class Context_request_email {

            @Test
            @DisplayName("test@test.com row한개를 리턴한다")
            void it_returns_email() {
                String searchEmail = "1test@test.com";
                UserSearchDescription command = new UserSearchDescription() {
                    @Override
                    public SearchKey getSearchKey() {
                        return SearchKey.EMAIL;
                    }

                    @Override
                    public String getSearchValue() {
                        return searchEmail;
                    }

                    @Override
                    public int getPage() {
                        return 0;
                    }

                    @Override
                    public int getSize() {
                        return 10;
                    }
                };


                Page<User> savedUser = getUserPageSearcher().findAll(command);
                Assertions.assertEquals(totalCount, savedUser.getSize());
                Assertions.assertEquals(searchEmail, savedUser.getContent().get(0).getEmail());
            }
        }
    }
}