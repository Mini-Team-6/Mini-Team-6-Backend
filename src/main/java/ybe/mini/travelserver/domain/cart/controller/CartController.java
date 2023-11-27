package ybe.mini.travelserver.domain.cart.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ybe.mini.travelserver.domain.cart.dto.request.CartCreateRequest;
import ybe.mini.travelserver.domain.cart.dto.response.CartCreateResponse;
import ybe.mini.travelserver.domain.cart.dto.response.CartDeleteResponse;
import ybe.mini.travelserver.domain.cart.dto.response.CartGetResponse;
import ybe.mini.travelserver.domain.cart.service.CartService;
import ybe.mini.travelserver.global.common.ResponseDto;
import ybe.mini.travelserver.global.security.PrincipalDetails;

import java.util.List;

@RestController
@RequestMapping("/carts")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    // 장바구니 전체 조회
    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping
    public ResponseDto<List<CartGetResponse>> getAllCart(
            @AuthenticationPrincipal PrincipalDetails principalDetails) {
        List<CartGetResponse> cartGetResponse =
                cartService.getMyCarts(principalDetails.getMemberId());
        return new ResponseDto<>(HttpStatus.OK.value(), cartGetResponse);
    }

    // 장바구니 생성
    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping
    public ResponseDto<CartCreateResponse> createCart(
            @AuthenticationPrincipal PrincipalDetails principalDetails,
            @RequestBody CartCreateRequest cartCreateRequest) {
        CartCreateResponse cartCreateResponse =
                cartService.createCart(principalDetails.getMemberId(), cartCreateRequest);
        return new ResponseDto<>(HttpStatus.CREATED.value(), cartCreateResponse);
    }


    // 장바구니 삭제
    @PreAuthorize("hasRole('ROLE_USER')")
    @DeleteMapping("/{cartId}")
    public ResponseDto<CartDeleteResponse> deleteCart(
            @AuthenticationPrincipal PrincipalDetails principalDetails,
            @PathVariable Long cartId) {
        CartDeleteResponse cartDeleteResponse = cartService.deleteCart(cartId);
        return new ResponseDto<>(HttpStatus.OK.value(), cartDeleteResponse);
    }
}