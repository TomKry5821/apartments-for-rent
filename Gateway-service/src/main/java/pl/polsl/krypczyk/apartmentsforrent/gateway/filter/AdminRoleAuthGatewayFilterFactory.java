package pl.polsl.krypczyk.apartmentsforrent.gateway.filter;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import pl.polsl.krypczyk.apartmentsforrent.gateway.authorization.AuthorizationService;
import pl.polsl.krypczyk.apartmentsforrent.gateway.role.RolesDTO;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
public class AdminRoleAuthGatewayFilterFactory extends AbstractGatewayFilterFactory<AdminRoleAuthGatewayFilterFactory.Config> {

    private final RouterValidator routerValidator = new RouterValidator();
    @Autowired
    private AuthorizationService authorizationService;

    @Data
    public static class Config {
    }

    public AdminRoleAuthGatewayFilterFactory() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            var request = exchange.getRequest();
            var response = exchange.getResponse();
            if(!isResourceSecured(request)){
                return chain.filter(exchange);
            }
            if (!isAuthorizationKeyPresent(request)) {
                return setUnauthorizedResponse(response);
            }
            String requestToken = request.getHeaders().get("Authorization").get(0);
            if(!TokenUtils.isTokenCorrect(requestToken)){
                return setUnauthorizedResponse(response);
            }
            RolesDTO userRoles = authorizationService.authorizeByToken(UUID.fromString(requestToken));
            if(!userRoles.getRoles().contains("ROLE_ADMIN")){
                return setUnauthorizedResponse(response);
            }
            return chain.filter(exchange);
        };
    }

    private Boolean isResourceSecured(ServerHttpRequest serverHttpRequest){
        return routerValidator.isSecured(serverHttpRequest);
    }

    private Boolean isAuthorizationKeyPresent(ServerHttpRequest serverHttpRequest){
        return serverHttpRequest.getHeaders().containsKey("Authorization");
    }

    private Mono<Void> setUnauthorizedResponse(ServerHttpResponse serverHttpResponse){
        serverHttpResponse.setStatusCode(HttpStatus.UNAUTHORIZED);
        return serverHttpResponse.setComplete();
    }

}

