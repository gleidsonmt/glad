module io.github.gleidsonmt.glad {

    requires  javafx.graphics;
    requires  javafx.controls;

    requires org.jetbrains.annotations;
    requires java.desktop;
    requires javafx.base;

//    requires org.scenicview.scenicview;
//    requires fr.brouillard.oss.cssfx;

    opens io.github.gleidsonmt.glad.base to javafx.fxml;
    opens io.github.gleidsonmt.glad.base.internal to javafx.fxml;

    exports io.github.gleidsonmt.glad.base.internal;
    exports io.github.gleidsonmt.glad.base.responsive;
    exports io.github.gleidsonmt.glad.demos;

    exports io.github.gleidsonmt.glad.dialog;
    exports io.github.gleidsonmt.glad.dialog.alert;

    exports io.github.gleidsonmt.glad;

    exports io.github.gleidsonmt.glad.base;
    exports io.github.gleidsonmt.glad.theme;

    exports io.github.gleidsonmt.glad.controls;
    exports io.github.gleidsonmt.glad.controls.icon;
    exports io.github.gleidsonmt.glad.controls.avatar;
    exports io.github.gleidsonmt.glad.controls.toggle_switch;
    exports io.github.gleidsonmt.glad.controls.text_box;
    exports io.github.gleidsonmt.glad.controls.skin;
    exports io.github.gleidsonmt.glad.controls.badge;
    exports io.github.gleidsonmt.glad.controls.button;
    exports io.github.gleidsonmt.glad.drawer;

    exports io.github.gleidsonmt.glad.controls.enums;
    exports io.github.gleidsonmt.glad.charts;
    exports io.github.gleidsonmt.glad.base.responsive.sizer;
    exports io.github.gleidsonmt.glad.errors;
}