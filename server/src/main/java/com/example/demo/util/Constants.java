package com.example.demo.util;

public class Constants {

  public static final String TEST = "/test";
  public static final String LOGIN = "/login";
  public static final String LOGOUT = "/logout";
  public static final String REGISTER = "/register";
  public static final String UPDATE_ROLE = "/update_role";

  public static final String REVIEWS_ENDPOINT = "/reviews";
  public static final String INVOICES_ENDPOINT = "/invoices";
  public static final String COMMANDS_ENDPOINT = "/commands";
  public static final String PRODUCTS_ENDPOINT = "/products";
  public static final String CUSTOMERS_ENDPOINT = "/customers";
  public static final String CATEGORIES_ENDPOINT = "/categories";

  public static final String USER_AUTH = "/user_auth";
  public static final String TEST_ENDPOINT = USER_AUTH + TEST;
  public static final String LOGIN_ENDPOINT = USER_AUTH + LOGIN;
  public static final String LOGOUT_ENDPOINT = USER_AUTH + LOGOUT;
  public static final String REGISTER_ENDPOINT = USER_AUTH + REGISTER;
  public static final String UPDATE_SALE_ENDPOINT = USER_AUTH + UPDATE_ROLE;

  public static final String SWAGGER_UI_PATH_1 = "/swagger-ui/**";
  public static final String SWAGGER_UI_PATH_2 = "/v3/api-docs/**";
  public static final String ApplicationUserInSession =
    "application_user_in_session";
}
