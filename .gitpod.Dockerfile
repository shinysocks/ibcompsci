FROM gitpod/workspace-full-vnc

USER gitpod

# Install Eclipse deps
RUN sudo apt-get update \
    && sudo apt-get install -y \
        default-jre \
        libxtst-dev

# Download Eclipse GUI installer
RUN wget -c "http://eclipse.mirror.rafal.ca/oomph/epp/2019-12/R/eclipse-inst-linux64.tar.gz" \
    -O - | tar -xpz -C $HOME