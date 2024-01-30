package utils;

import controller.PostController;
import controller.UserController;
import http.request.HttpRequest;
import http.response.HttpResponse;

import java.util.Map;
import java.util.function.Function;


/**
 * method + path 에 맞는 validator(유효성 검증 메소드)와 controller 맵핑
 */

public enum ValidatorControllerMapper {
    SIGNUP("POST /user/create", Validator::validateSignUpInfo, UserController::signup),
    LOGIN("POST /user/login", Validator::validateLoginInfo, UserController::login),
    LOGOUT("GET /user/logout", null, UserController::logout),
    UPLOAD("POST /post/create", Validator::validatePostInfo, PostController::upload),
    ;

    private String path;
    private Function<HttpRequest, Boolean> validator;
    private Function<HttpRequest, HttpResponse> controller;

    ValidatorControllerMapper(String path, Function<HttpRequest, Boolean> validator,
                              Function<HttpRequest, HttpResponse> controller) {
        this.path = path;
        this.validator = validator;
        this.controller = controller;
    }

    public Function<HttpRequest, Boolean> getValidator() {
        return validator;
    }

    public Function<HttpRequest, HttpResponse> getController() {
        return controller;
    }

    public static ValidatorControllerMapper getValidatorAndControllerByPath(String path) {
        for (ValidatorControllerMapper vc : values()) {
            if (vc.path.equals(path)) {
                return vc;
            }
        }
        return null;
    }
}
