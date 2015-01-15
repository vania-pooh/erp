package org.meridor.erp.command;

import io.airlift.airline.Command;

import java.net.URL;
import java.net.URLClassLoader;
import java.util.jar.Manifest;

@Command(name = "version", description = "Show application version")
public class Version extends BaseCommand {

    @Override
    public void runUnsafe() throws Exception {
        URL url = ((URLClassLoader) getClass().getClassLoader()).findResource("META-INF/MANIFEST.MF");
        Manifest manifest = new Manifest(url.openStream());
        String specificationVersion = manifest.getMainAttributes().getValue("Specification-Version");
        if (specificationVersion != null) {
            LOG.info(specificationVersion);
        } else {
            LOG.error("Failed to load version from MANIFEST.MF.");
        }
    }

}
