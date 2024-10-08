module Projekt {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;
    requires org.jsoup;
    requires json.simple;
    requires javafx.web;

    opens GUI to javafx.fxml;
    exports GUI;
}