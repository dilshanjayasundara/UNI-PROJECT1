module com.food_company.task_4 {
    requires javafx.controls;
    requires javafx.fxml;
            
        requires org.controlsfx.controls;
            requires com.dlsc.formsfx;
                        
    opens com.food_company.task_4 to javafx.fxml;
    exports com.food_company.task_4;
}