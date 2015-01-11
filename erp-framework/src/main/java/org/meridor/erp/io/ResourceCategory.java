package org.meridor.erp.io;

import java.nio.file.FileSystems;
import java.nio.file.PathMatcher;

public enum ResourceCategory {
    
    FXML_FILE("glob:**/*.fxml"),
    SQL_MIGRATION("glob:**/db/migration/V*.sql");

    private final String glob;

    ResourceCategory(String glob) {
        this.glob = glob;
    }

    public String getGlob() {
        return glob;
    }
    
    public PathMatcher getPathMatcher() {
        return FileSystems.getDefault().getPathMatcher(glob);
    }
}
