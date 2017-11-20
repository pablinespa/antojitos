package ucb.bo.edu.antojitosapp;

/**
 * Created by ASUS on 31/10/2017.
 */

public class ConstantsRestApi {

    public static final String VERSION = "/api/";
    public static final String URL = "https://openlibrary.org";
    public static final String URL_ANTOJITOS = "http://antojitos.xanthops.com/api/v1/";

    public static final String URL_BASE =  URL+VERSION;
    public static final String URL_BOOK = "books?jscmd=data&format=json&bibkeys=ISBN:";
    public static final String URL_RESTAURANT = "get_restaurant?restaurant_id=";
    public static final String URL_TERM = "get_terms_of_use";
    public static final String URL_ABOUT = "get_privacy_policy";

    public static final String URL_BOOK_LIST = "listbooks";
    public static final String BOOK_TITLE  = "title";
    public static final String BOOK_AUTHORS  = "authors";
    public static final String TERM_DESCRIPTION  = "description";

}
