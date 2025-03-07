
## Understanding
### Initializing
```java
    ..
public void Start(Stage stage) {
    
    StackPane body = new StackPane(new Label("Hello World!"));
    Container container = new Container(body);
    Root root = new Root(container);
    stage.setScene(new Scene(root, 800, 600));
    stage.show();
    
}
```
### Root
#### A classe base com chamadas para a api

## The api
### Behavior
### With behavior you can use execute the common actions in dashboards.
```java
    // Open drawer
    root.behavior().openDrawer();
    // Open aside
    root.behavior().openAside();
```
### Wrapper
### The wrapper is a foreground usually gray that appears to focus.
### Flow
### The Class to add node floatings.
### Container
### The container(BorderPane) class is used to set a layout.
#### Classe de layout


## Theme
## Controls
## Alerts
## Dialogs
## Responsive Grid

