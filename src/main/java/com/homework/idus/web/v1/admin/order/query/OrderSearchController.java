package com.homework.idus.web.v1.admin.order.query;

import com.homework.idus.core.order.query.Order;
import com.homework.idus.core.order.query.OrderPageSearcher;
import com.homework.idus.web.v1.admin.UnauthorizedException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;


@Tag(name = "단일회원 주문목록")
@RestController
@RequestMapping("/v1/admin/order")
@RequiredArgsConstructor
public class OrderSearchController {
    private final OrderPageSearcher orderPageSearcher;

    @GetMapping("/{userNo}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "단일회원 주문조회", description = "단일회원의 주문목록 조회를 담당합니다." )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "주문목록 조회"),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
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
