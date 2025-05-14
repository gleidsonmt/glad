package io.github.gleidsonmt.glad;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  17/03/2025
 */
public class User{

    private StringProperty name = new SimpleStringProperty();
    private StringProperty userName = new SimpleStringProperty();
    private StringProperty legend = new SimpleStringProperty();
    private ObjectProperty<Image> avatar = new SimpleObjectProperty<>();

    public User(Image avatar, String name, String legend) {
        this.avatar.set(avatar);
        this.name.set(name);
        this.legend.set(legend);
    }

    public Image getAvatar() {
        return avatar.get();
    }

    public ObjectProperty<Image> avatarProperty() {
        return avatar;
    }

    public void setAvatar(Image avatar) {
        this.avatar.set(avatar);
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

    public String getUserName() {
        return userName.get();
    }

    public StringProperty userNameProperty() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName.set(userName);
    }
};
