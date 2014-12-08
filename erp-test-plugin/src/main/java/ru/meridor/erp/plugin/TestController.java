package ru.meridor.erp.plugin;

import javafx.scene.input.MouseEvent;
import ru.meridor.erp.annotation.Controller;

@Controller
public class TestController {

    public void testMethod(MouseEvent mouseEvent){
        System.out.println("Good job!");
    }

}
