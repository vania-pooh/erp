package org.meridor.erp.it;

import javafx.scene.Node;
import javafx.scene.control.Button;
import org.junit.Test;

import java.util.Optional;

import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertThat;
import static org.meridor.arabica.Matchers.isPresent;

public class IntegrationTest extends ErpTest {

    @Test
    public void testButtonIsPresent() {
        Optional<Node> node = lookup("#anotherButton");
        assertThat(node, isPresent());
        assertThat(node.get(), instanceOf(Button.class));
    }

}
