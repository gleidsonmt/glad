module io.github.gleidsonmt.glad {

    requires  javafx.graphics;
    requires  javafx.controls;

    requires org.jetbrains.annotations;
    requires io.github.gleidsonmt.blockcode;

    requires org.scenicview.scenicview;

    requires io.github.gleidsonmt.presentation;
    requires org.yaml.snakeyaml;
    requires fr.brouillard.oss.cssfx;

    exports io.github.gleidsonmt.glad.base;
    exports io.github.gleidsonmt.glad.theme;
    exports io.github.gleidsonmt.glad.controls.icon;
    exports io.github.gleidsonmt.glad.controls.avatar;



    opens io.github.gleidsonmt.glad.base to javafx.fxml;
    opens io.github.gleidsonmt.glad.base.internal to javafx.fxml;
    exports io.github.gleidsonmt.glad.base.internal;
    exports io.github.gleidsonmt.glad.responsive;
    exports io.github.gleidsonmt.glad.demos;

    exports io.github.gleidsonmt.glad.dialog;
    exports io.github.gleidsonmt.glad.dialog.alert;

    exports io.github.gleidsonmt.glad;
}