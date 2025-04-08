package io.github.gleidsonmt.glad;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  17/03/2025
 */
public class User{

    private StringProperty name = new SimpleStringProperty();
    private StringProperty legend = new SimpleStringProperty();

    public User(String name, String legend) {
        this.name.set(name);
        this.legend.set(legend);
    }

    public String getLegend() {
        return legend.get();
    }

    public StringProperty legendProperty() {
        return legend;
    }

    public void setLegend(String legend) {
        this.legend.set(legend);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }
};
