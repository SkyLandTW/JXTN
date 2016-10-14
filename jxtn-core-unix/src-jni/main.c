#include <security/pam_appl.h>
#include <string.h>
#include <stdio.h>
#include <stdlib.h>

static int pam_conv(int num_msg, const struct pam_message **msg, struct pam_response **resp, void *appdata_ptr);
static int auth_pam(const char* service, const char *username, const char *password);

int main(int argc, const char **argv) {
    if (argc > 1) {
        const char* command = argv[1];
        if (strcmp(command, "pam") == 0 && argc > 3) {
            const char* service = argv[2];
            const char* user = argv[3];
            char* pwd = (char*) malloc(256);
            size_t len = 256;
            if (getline(&pwd, &len, stdin) == -1L) {
                fprintf(stderr, "No password\n");
                return EXIT_FAILURE;
            }
            *strchr(pwd, '\n') = '\0';
            // fprintf(stdout, "auth [%s] [%s]\n", user, line);
            int ret = auth_pam(service, user, pwd);
            free(pwd);
            return ret;
        }
    }
    fprintf(stderr, "Usage 1: libjxtn-core-unix.so pam [SERVICE] [USER]\n");
    return EXIT_FAILURE;
}

static int pam_conv(int num_msg, const struct pam_message** msg, struct pam_response** resp, void* appdata_ptr) {
    struct pam_response* pam_reply = (struct pam_response*) appdata_ptr;
    *resp = pam_reply;
    return PAM_SUCCESS;
}

static int auth_pam(const char* service, const char* username, const char* password) {
    struct pam_response* pam_reply = (struct pam_response*) malloc(sizeof(struct pam_response));
    pam_reply->resp = (char*) strdup(password);
    pam_reply->resp_retcode = 0;
    const struct pam_conv local_conversation = { &pam_conv, pam_reply };
    pam_handle_t *local_auth_handle = NULL; // this gets set by pam_start
    int r_start = pam_start(service, username, &local_conversation, &local_auth_handle);
    if (r_start != PAM_SUCCESS) {
        fprintf(stdout, "pam_start returned: %d\n ", r_start);
        return r_start;
    }
    int r_auth = pam_authenticate(local_auth_handle, 0);
    if (r_auth != PAM_SUCCESS) {
        if (r_auth == PAM_AUTH_ERR)
            fprintf(stdout, "Authentication failure.\n");
        else
            fprintf(stdout, "pam_authenticate returned %d\n", r_auth);
        return r_auth;
    }
    fprintf(stdout, "Authenticated.\n");
    int r_end = pam_end(local_auth_handle, r_auth);
    if (r_end != PAM_SUCCESS) {
        fprintf(stdout, "pam_end returned %d\n", r_end);
        return r_end;
    }
    return PAM_SUCCESS;
}
