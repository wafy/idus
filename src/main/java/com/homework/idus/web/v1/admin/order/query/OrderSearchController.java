package com.homework.idus.web.v1.admin.order.query;

import com.homework.idus.core.exception.UserNotFountException;
import com.homework.idus.core.order.query.Order;
import com.homework.idus.core.order.query.OrderPageSearcher;
import com.homework.idus.web.v1.admin.UnauthorizedException;
import com.homework.idus.web.v1.admin.search.query.UserSearchResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;


@Api(tags = {"4. 주문목록조회"})
@RestController
@RequestMapping("/v1/admin/order")
@RequiredArgsConstructor
public class OrderSearchController {
    private final OrderPageSearcher orderPageSearcher;

    @GetMapping("/{userNo}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "관리자 주문 목록조회", description = "단일회원 주문정보를 조회 합니다.")
    @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "회원 조회 성공",
                    content = @Content(schema = @Schema(implementation = UserSearchResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "토큰 정보 없이 조회",
                    content = @Content(schema = @Schema(implementation = UnauthorizedException.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "존재하지 않는 회원번호로 조회",
                    content = @Content(schema = @Schema(implementation = UserNotFountException.class)))})
    public Page<OrderResponse> findAll(@PathVariable Long userNo,
                                       @RequestBody OrderRequest request,
                                       @AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails == null) {
            throw new UnauthorizedException("토큰정보가 누락되었습니다.");
        }

        Page<Order> orders = orderPageSearcher.findAll(userNo, request);
        return orders.map(OrderResponse::new);
    }
}
