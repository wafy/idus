package com.homework.idus.web.v1.admin.order.query;

import com.homework.idus.core.order.query.Order;
import com.homework.idus.core.order.query.OrderPageSearcher;
import com.homework.idus.web.v1.admin.UnauthorizedException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
    @ApiOperation(value = "관리자 주문 목록조회", notes = "단일회원 주문정보를 조회 합니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "정상응답")
    })
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
