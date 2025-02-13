module io.github.gleidsonmt.glad {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.jetbrains.annotations;

    exports io.github.gleidsonmt.glad.base;
    exports io.github.gleidsonmt.glad.theme;
    exports io.github.gleidsonmt.glad.controls.icon;
    exports io.github.gleidsonmt.glad.controls.avatar;

    opens io.github.gleidsonmt.glad.base to javafx.fxml;
    opens io.github.gleidsonmt.glad.base.internal to javafx.fxml;
    exports io.github.gleidsonmt.glad.base.internal;
    exports io.github.gleidsonmt.glad.responsive_grid;
    exports io.github.gleidsonmt.glad.demos;
}