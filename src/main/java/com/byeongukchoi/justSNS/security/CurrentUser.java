package com.byeongukchoi.justSNS.security;

import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.lang.annotation.*;

// 런타임 까지 유지
@Retention(RetentionPolicy.RUNTIME)
// 타겟은 파라미터에만 붙이겠다.
@Target(ElementType.PARAMETER)
@Documented // 해당 어노테이션을 javadoc에 포함 시킴
// Principal 을 다이나믹 하게 꺼내기 위해 @CurrentUser 생성
@AuthenticationPrincipal
public @interface CurrentUser {

}
