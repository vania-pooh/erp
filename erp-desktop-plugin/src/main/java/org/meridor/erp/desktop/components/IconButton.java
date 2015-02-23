package org.meridor.erp.desktop.components;

import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconName;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.text.Text;

public class IconButton extends Button {

    private FontAwesomeIconName icon;
    
    public void setIcon(FontAwesomeIconName icon) {
        this.icon = icon;
        Text label = GlyphsDude.createIcon(icon, "48.0");
        setStyle("-fx-font-size: 20.0");
        setGraphic(label);
        setContentDisplay(ContentDisplay.TOP);
    }

    public FontAwesomeIconName getIcon() {
        return icon;
    }
}
