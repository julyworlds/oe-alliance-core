FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI:append:sh4 = " \
    file://util-linux-random.patch \
    file://util-linux-sh4.patch \
"

SRC_URI:append:cube = " \
    file://util-linux-random.patch \
"

PACKAGES =+ "util-linux-flock"
FILES:util-linux-flock = "${base_sbindir}/flock.${BPN}"

ALTERNATIVE:util-linux-flock = "flock"
ALTERNATIVE_LINK_NAME[flock] = "${base_sbindir}/flock"

# Lower the priorities of util-linux-(u)mount, so that if they happen to
# become installed, they won't replace the working busybox commands.
ALTERNATIVE_PRIORITY[mount] = "10"
ALTERNATIVE_PRIORITY[umount] = "10"

SSTATE_DUPWHITELIST += "${STAGING_DIR_NATIVE}/bin/login"

do_install:append () {
    if [ "${base_sbindir}" != "${sbindir}" ]; then
        mkdir -p ${D}${base_sbindir}
        if [ -f "${D}${bindir}/flock" ]; then
            mv "${D}${bindir}/flock" "${D}${base_sbindir}/flock"
        fi
    fi
}
