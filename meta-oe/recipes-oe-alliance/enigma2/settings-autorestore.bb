SUMMARY = "Autorecover settings and install packages at first boot from /media/*/backup"
PACKAGES = "${PN}"
MAINTAINER = "MiLo"
require conf/license/license-gplv2.inc

inherit allarch

PV = "20111105"
PR = "r0"

SRC_URI = "file://shell-scripts/"

# Need to tell bitbake that we have extra files installed
FILES:${PN} = "/etc"

S = "${WORKDIR}"

# Not inheriting from rc-update because the script commits suicide, which
# confuses the pkg scripts.
do_install() {
    install -d ${D}/etc/init.d
    install -d ${D}/etc/rcS.d
    # run-once initialization script
    install -m 755 ${S}/settings-restore.sh ${D}/etc/init.d/settings-restore.sh
    install -m 755 ${S}/settings-restore.old.sh ${D}/etc/init.d/settings-restore.old.sh
    install -m 755 ${S}/autoinstall.sh ${D}/etc/init.d/autoinstall.sh
}

# Safeguard: Don't activate on a running image
pkg_postinst:${PN}() {
    if [ "x$D" != "x" ]
    then
        ln -sf ../init.d/settings-restore.sh $D/etc/rcS.d/S31settingsrestore
        ln -sf ../init.d/autoinstall.sh $D/etc/rcS.d/S99autoinstall
    fi
}
