/*
 * This is free and unencumbered software released into the public domain.
 *
 * Anyone is free to copy, modify, publish, use, compile, sell, or
 * distribute this software, either in source code form or as a compiled
 * binary, for any purpose, commercial or non-commercial, and by any
 * means.
 *
 * In jurisdictions that recognize copyright laws, the author or authors
 * of this software dedicate any and all copyright interest in the
 * software to the public domain. We make this dedication for the benefit
 * of the public at large and to the detriment of our heirs and
 * successors. We intend this dedication to be an overt act of
 * relinquishment in perpetuity of all present and future rights to this
 * software under copyright law.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS BE LIABLE FOR ANY CLAIM, DAMAGES OR
 * OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 *
 * For more information, please refer to <http://unlicense.org/>
 */
package jxtn.core.unix;

import java.lang.reflect.Field;

public final class NativePAM extends JNIBase {

    public static final int PAM_SUCCESS = 0; /* Successful function return */
    public static final int PAM_OPEN_ERR = 1; /* dlopen() failure when dynamically loading a service module */
    public static final int PAM_SYMBOL_ERR = 2; /* Symbol not found */
    public static final int PAM_SERVICE_ERR = 3; /* Error in service module */
    public static final int PAM_SYSTEM_ERR = 4; /* System error */
    public static final int PAM_BUF_ERR = 5; /* Memory buffer error */
    public static final int PAM_PERM_DENIED = 6; /* Permission denied */
    public static final int PAM_AUTH_ERR = 7; /* Authentication failure */
    public static final int PAM_CRED_INSUFFICIENT = 8; /* Can not access authentication data
                                                          due to insufficient credentials */
    public static final int PAM_AUTHINFO_UNAVAIL = 9; /* Underlying authentication service can not retrieve
                                                         authentication information  */
    public static final int PAM_USER_UNKNOWN = 10; /* User not known to the underlying authenticaiton module */
    public static final int PAM_MAXTRIES = 11; /* An authentication service has maintained a retry count which has been
                                                  reached.  No further retries should be attempted */
    public static final int PAM_NEW_AUTHTOK_REQD = 12; /* New authentication token required. This is normally returned
                                                          if the machine security policies require that the password
                                                          should be changed beccause the password is NULL or it has
                                                          aged */
    public static final int PAM_ACCT_EXPIRED = 13; /* User account has expired */
    public static final int PAM_SESSION_ERR = 14; /* Can not make/remove an entry for the specified session */
    public static final int PAM_CRED_UNAVAIL = 15; /* Underlying authentication service can not retrieve user
                                                      credentials unavailable */
    public static final int PAM_CRED_EXPIRED = 16; /* User credentials expired */
    public static final int PAM_CRED_ERR = 17; /* Failure setting user credentials */
    public static final int PAM_NO_MODULE_DATA = 18; /* No module specific data is present */
    public static final int PAM_CONV_ERR = 19; /* Conversation error */
    public static final int PAM_AUTHTOK_ERR = 20; /* Authentication token manipulation error */
    public static final int PAM_AUTHTOK_RECOVERY_ERR = 21; /* Authentication information cannot be recovered */
    public static final int PAM_AUTHTOK_LOCK_BUSY = 22; /* Authentication token lock busy */
    public static final int PAM_AUTHTOK_DISABLE_AGING = 23; /* Authentication token aging disabled */
    public static final int PAM_TRY_AGAIN = 24; /* Preliminary check by password service */
    public static final int PAM_IGNORE = 25; /* Ignore underlying account module regardless of whether the control flag
                                                is required, optional, or sufficient */
    public static final int PAM_ABORT = 26; /* Critical error (?module fail now request) */
    public static final int PAM_AUTHTOK_EXPIRED = 27; /* user's authentication token has expired */
    public static final int PAM_MODULE_UNKNOWN = 28; /* module is not known */

    public static final int PAM_BAD_ITEM = 29; /* Bad item passed to pam_*_item() */
    public static final int PAM_CONV_AGAIN = 30; /* conversation function is event driven and data is not available yet
                                                  */
    public static final int PAM_INCOMPLETE = 31; /* please call this function again to complete authentication stack.
                                                    Before calling again, verify that conversation is completed */

    public static final int _PAM_RETURN_VALUES = 32; /* this is the number of return values */

    public static final String[] retToName;

    static {
        retToName = new String[256];
        for (Field field : NativePAM.class.getDeclaredFields()) {
            if (field.getType() != Integer.TYPE || !field.getName().startsWith("PAM_")) {
                continue;
            }
            int ret;
            try {
                ret = (int) field.get(null);
            } catch (ReflectiveOperationException e) {
                throw new RuntimeException(e);
            }
            retToName[ret] = field.getName();
        }
    }

    public static int authenticate(String service, String username, String password) {
        return authenticate(tName(service), tName(username), tName(password));
    }

    private static native int authenticate(byte[] service, byte[] username, byte[] password);

    public static String retName(int retCode) {
        if (retCode < 0 || retCode >= retToName.length) {
            return Integer.toString(retCode);
        }
        String name = retToName[retCode];
        return name == null ? Integer.toString(retCode) : name;
    }

    private NativePAM() {
    }
}
