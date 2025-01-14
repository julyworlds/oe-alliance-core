SUMMARY = "Userspace tools for MMC/SD devices"
HOMEPAGE = "http://git.kernel.org/cgit/linux/kernel/git/cjb/mmc-utils.git/"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://mmc.c;beginline=1;endline=20;md5=fae32792e20f4d27ade1c5a762d16b7d"

SRCBRANCH ?= "master"
SRCREV = "b4fe0c8c0e57a74c01755fa9362703b60d7ee49d"

PV = "0.1vu"

SRC_URI = "git://git.kernel.org/pub/scm/linux/kernel/git/cjb/mmc-utils.git;branch=${SRCBRANCH} \
           file://0001-lsmmc-replace-strncpy-with-memmove-on-overlapping-me.patch \
           "
UPSTREAM_CHECK_COMMITS = "1"

S = "${WORKDIR}/git"

CFLAGS:append:powerpc64 = " -D__SANE_USERSPACE_TYPES__"
CFLAGS:append:mipsarchn64 = " -D__SANE_USERSPACE_TYPES__"
CFLAGS:append:mipsarchn32 = " -D__SANE_USERSPACE_TYPES__"

do_install() {
    install -d ${D}${bindir}
    install -m 0755 mmc ${D}${bindir}
}
