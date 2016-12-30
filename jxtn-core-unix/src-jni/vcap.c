/*
 * Copyright (c) 2016 SKY LAND UNIVERSAL CORPORATION
 */

#define _GNU_SOURCE     1

#include <arpa/inet.h>
#include <errno.h>
#include <linux/if_ether.h>
#include <linux/if_packet.h>
#include <net/if.h>
#include <signal.h>
#include <stdio.h>
#include <stdlib.h>
#include <sys/ioctl.h>
#include <sys/socket.h>
#include <unistd.h>
#include "internals.h"

#define EXIT_ARGS       2
#define EXIT_NETWORK    3
#define EXIT_RECORD     4
#define EXIT_UNKNOWN    9
#define FRAME_MAXSIZE    (65536)

static int socket_initialize(const char* ifname);
static void socket_set_promiscuous(int sockfd, const char* ifname);
static void socket_set_reuse(int sockfd);
static void socket_set_interface(int sockfd, const char* ifname);
static void socket_set_recv_buf(int sockfd, int size);
static void socket_set_recv_time(int sockfd, time_t tv_sec, suseconds_t tv_usec);

JNIEXPORT jint JNICALL Java_jxtn_core_unix_NetTrafficCapture_start(JNIEnv *env, jclass thisObj,
        jstring ifName) {
    jboolean isCopy = 0;
    const char* ifNameChars = (*env)->GetStringUTFChars(env, ifName, &isCopy);
    int sockfd = socket_initialize(ifNameChars);
    // socket_set_recv_time(sockfd, 100000, 0);
    (*env)->ReleaseStringUTFChars(env, ifName, ifNameChars);
    return ERR(sockfd);
}

JNIEXPORT jbyteArray JNICALL Java_jxtn_core_unix_NetTrafficCapture_receive(JNIEnv *env, jclass thisObj,
        jint sockfd) {
    uint8_t buf[FRAME_MAXSIZE];
    jsize len = (jsize) recvfrom(sockfd, buf, sizeof (buf), 0, NULL, NULL);
    if (len >= 0) {
        jbyteArray retval = (*env)->NewByteArray(env, len);
        (*env)->SetByteArrayRegion(env, retval, 0, len, (jbyte*)buf);
        return retval;
    }
    ERR(-1);
    return NULL;
}

/**
 * Initialize network socket on given interface name for raw packet capture
 *
 * source: https://gist.github.com/austinmarton/2862515
 *
 * @param ifName interface name to initialize
 * @return fd
 */
static int socket_initialize(const char* ifName) {
    int sockfd;
    // open socket
    // ETH_P_IP cannot capture outgoing packets from this machine
    // ETH_P_ALL captures all but packets are in wrong order
    if ((sockfd = socket(PF_PACKET, SOCK_RAW, htons(ETH_P_ALL))) == -1) {
        perror("socket");
        exit (EXIT_NETWORK);
    }
    // configuration
    socket_set_promiscuous(sockfd, ifName);
    socket_set_reuse(sockfd);
    socket_set_interface(sockfd, ifName);
    socket_set_recv_buf(sockfd, 10 * 1048576);
    socket_set_recv_time(sockfd, 0, 100 * 1000);
    //
    return sockfd;
}

static void socket_set_promiscuous(int sockfd, const char* ifName) {
    struct ifreq ifopts;
    strncpy(ifopts.ifr_name, ifName, IFNAMSIZ - 1);
    if (ioctl(sockfd, SIOCGIFFLAGS, &ifopts) == -1) {
        perror("SIOCGIFFLAGS");
        exit (EXIT_NETWORK);
    }
    //
    ifopts.ifr_flags |= IFF_PROMISC;
    if (ioctl(sockfd, SIOCSIFFLAGS, &ifopts) == -1) {
        perror("configure IFF_PROMISC");
        exit (EXIT_NETWORK);
    }
    //
#if 0
    struct packet_mreq mr;
    memset(&mr, 0, sizeof ( mr));
    mr.mr_ifindex = ifopts.ifr_ifindex;
    mr.mr_type = PACKET_MR_PROMISC;
    if (setsockopt(sockfd, SOL_PACKET, PACKET_ADD_MEMBERSHIP, &mr, sizeof (mr)) < 0) {
        perror("configure PACKET_MR_PROMISC");
        exit(EXIT_NETWORK);
    }
#endif
#if 0
    if (setsockopt(sockfd, SOL_PACKET, PACKET_RECV_OUTPUT, NULL, 0) < 0) {
        perror("configure PACKET_RECV_OUTPUT");
        exit(EXIT_NETWORK);
    }
#endif
}

static void socket_set_reuse(int sockfd) {
    int sockopt;
    if (setsockopt(sockfd, SOL_SOCKET, SO_REUSEADDR, &sockopt, sizeof sockopt) == -1) {
        perror("configure SO_REUSEADDR");
        close(sockfd);
        exit (EXIT_NETWORK);
    }
}

static void socket_set_interface(int sockfd, const char* ifName) {
    // Option 1: SO_BINDTODEVICE: not working!!
    if (setsockopt(sockfd, SOL_SOCKET, SO_BINDTODEVICE, ifName, IFNAMSIZ - 1) == -1) {
        perror("configure SO_BINDTODEVICE");
        close(sockfd);
        exit (EXIT_NETWORK);
    }
    // Option 2: bind
    struct sockaddr_ll sock_address;
    memset(&sock_address, 0, sizeof(sock_address));
    sock_address.sll_family = PF_PACKET;
    sock_address.sll_protocol = htons(ETH_P_ALL);
    sock_address.sll_ifindex = SI(if_nametoindex(ifName));
    if (bind(sockfd, (struct sockaddr*) &sock_address, sizeof(sock_address)) < 0) {
        perror("bind");
        close(sockfd);
        exit (EXIT_NETWORK);
    }
}

static void socket_set_recv_buf(int sockfd, int size) {
    if (setsockopt(sockfd, SOL_SOCKET, SO_RCVBUFFORCE, &size, sizeof(size)) == -1) {
        perror("configure SO_RCVBUF");
        close(sockfd);
        exit (EXIT_NETWORK);
    }
}

static void socket_set_recv_time(int sockfd, time_t tv_sec, suseconds_t tv_usec) {
    struct timeval timeout = { tv_sec, tv_usec };
    if (setsockopt(sockfd, SOL_SOCKET, SO_RCVTIMEO, &timeout, sizeof(struct timeval)) == -1) {
        perror("configure SO_RCVTIMEO");
        close(sockfd);
        exit (EXIT_NETWORK);
    }
}
