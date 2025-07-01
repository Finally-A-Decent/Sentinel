package info.preva1l.sentinel;

/**
 * Created on 18/02/2025
 *
 * @author Preva1l
 */
public final class ApiSanityChecker {
    /**
     * For pete's sake don't touch this, if its causing issues,
     * you have shaded/implemented the api instead of compiling against it!!!!
     */
    static boolean validApiInstance = false;

    /**
     * If the API is in a usable state.
     *
     * @return false if the API has not been started properly.
     */
    public static boolean isValid() {
        return validApiInstance;
    }
}
