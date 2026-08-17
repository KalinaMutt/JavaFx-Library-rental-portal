package com.example;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.stage.FileChooser;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.event.EventHandler;

import java.io.File;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.Node;

import org.apache.commons.lang3.StringUtils;

public class newentries extends Pages {
    private File file;

    public Scene getScene(Pane root) {
        thisscenenumber = 9;
        thisscenetarget = 8;
        root.setMinSize(600, 600);
        root.setManaged(false);

        Scene newentriesScene = new Scene(root, 600, 600);
        newentriesScene.getStylesheets().add("css/newentries.css");

        Pane background = createbackgrounds(root, thisscenenumber, thisscenetarget, true);

        Label customertitle = new Label("New Item");
        customertitle.setPrefSize(400, 50);
        customertitle.getStyleClass().add("addtitle");
        background.getChildren().add(customertitle);

        Label labelname = new Label("Name");
        labelname.setPrefSize(100, 50);
        labelname.getStyleClass().add("addinglabel");
        labelname.setTranslateY(90);
        background.getChildren().add(labelname);

        TextField texfieldname = new TextField();
        texfieldname.setPrefSize(350, 50);
        texfieldname.getStyleClass().add("addfield");
        texfieldname.setTranslateY(90);
        addtoofocuslist(texfieldname);
        background.getChildren().add(texfieldname);

        Label labeltype = new Label("Type");
        labeltype.setPrefSize(100, 50);
        labeltype.getStyleClass().add("addinglabel");
        labeltype.setTranslateY(160);
        background.getChildren().add(labeltype);

        Pane buttonspane = new Pane();
        buttonspane.setPrefSize(350, 50);
        buttonspane.setTranslateY(160);
        buttonspane.setTranslateX(200);
        buttonspane.getStyleClass().add("buttonspane");

        Pane Vinylfiller = new Pane();
        Vinylfiller.setPrefSize(70, 50);
        Vinylfiller.setTranslateX(35);
        Vinylfiller.getStyleClass().add("selectorfiller");
        addtoofocuslist(Vinylfiller);

        Label Vinyl = new Label("Vinyl");
        Vinyl.setPrefSize(70, 50);
        Vinyl.getStyleClass().add("Selectorlabel");
        Vinylfiller.getChildren().add(Vinyl);

        Pane DVDfiller = new Pane();
        DVDfiller.setPrefSize(70, 50);
        DVDfiller.setTranslateX(140);
        DVDfiller.getStyleClass().add("selectorfiller");
        addtoofocuslist(DVDfiller);

        Label DVD = new Label("DVD");
        DVD.setPrefSize(70, 50);
        DVD.getStyleClass().add("Selectorlabel");
        DVDfiller.getChildren().add(DVD);

        Pane Toolfiller = new Pane();
        Toolfiller.setPrefSize(70, 50);
        Toolfiller.setTranslateX(245);
        Toolfiller.getStyleClass().add("selectorfiller");
        addtoofocuslist(Toolfiller);

        Label Tool = new Label("Tool");
        Tool.setPrefSize(70, 50);
        Tool.getStyleClass().add("Selectorlabel");
        Toolfiller.getChildren().add(Tool);

        buttonspane.getChildren().add(Vinylfiller);
        buttonspane.getChildren().add(DVDfiller);
        buttonspane.getChildren().add(Toolfiller);
        background.getChildren().add(buttonspane);

        typeassignment(Vinylfiller, DVDfiller, Toolfiller, null);
        typeassignment(DVDfiller, Vinylfiller, Toolfiller, null);
        typeassignment(Toolfiller, Vinylfiller, DVDfiller, null);

        Label labeledition = new Label("Edition");
        labeledition.setPrefSize(100, 50);
        labeledition.getStyleClass().add("addinglabel");
        labeledition.setTranslateY(230);
        background.getChildren().add(labeledition);

        TextField textfieldedition = new TextField("Default");
        textfieldedition.setPrefSize(350, 50);
        textfieldedition.getStyleClass().add("addfield");
        textfieldedition.setTranslateY(230);
        addtoofocuslist(textfieldedition);
        background.getChildren().add(textfieldedition);

        Label labelamount = new Label("Amount");
        labelamount.setPrefSize(100, 50);
        labelamount.getStyleClass().add("addinglabel");
        labelamount.setTranslateY(300);
        background.getChildren().add(labelamount);

        Pane incrementerPane = new Pane();
        incrementerPane.setPrefSize(200, 50);
        incrementerPane.setTranslateX(250);
        incrementerPane.setTranslateY(300);

        Pane leftpane = new Pane();
        leftpane.setTranslateX(-50);
        leftpane.setPrefSize(30, 50);
        leftpane.getStyleClass().add("leftarrow");
        addtoofocuslist(leftpane);
        incrementerPane.getChildren().add(leftpane);

        Polygon leftarrow = new Polygon();
        leftarrow.getStyleClass().add("arrow");
        leftarrow.getPoints().addAll(new Double[] {
                0.0, 25.0,
                30.0, 0.0,
                30.0, 50.0
        });
        leftarrow.setFill(Color.BLACK);
        leftpane.getChildren().add(leftarrow);

        Label incrementLabel = new Label("1");
        incrementLabel.setPrefSize(40, 50);
        incrementLabel.getStyleClass().add("incrementer");
        incrementerPane.getChildren().add(incrementLabel);

        amountchanger(leftpane, incrementLabel);
        // maybe wrap in pane for hover and tab properties

        Pane rightpane = new Pane();
        rightpane.setTranslateX(60);
        rightpane.setPrefSize(30, 50);
        rightpane.getStyleClass().add("rightarrow");
        addtoofocuslist(rightpane);
        incrementerPane.getChildren().add(rightpane);
        amountchanger(rightpane, incrementLabel);

        Polygon rightarrow = new Polygon();
        rightarrow.getStyleClass().add("arrow");
        rightarrow.getPoints().addAll(new Double[] {
                60.0, 25.0,
                30.0, 0.0,
                30.0, 50.0
        });
        rightarrow.setFill(Color.BLACK);
        rightarrow.setTranslateX(-30);

        rightpane.getChildren().add(rightarrow);

        background.getChildren().add(incrementerPane);

        Label labelphoto = new Label("Photo");
        labelphoto.setPrefSize(80, 50);
        labelphoto.getStyleClass().add("addinglabell");
        labelphoto.setTranslateY(300);
        background.getChildren().add(labelphoto);

        Label Labelclick = new Label();
        Labelclick.setPrefSize(100, 50);
        Labelclick.getStyleClass().add("addfieldl");
        Labelclick.setTranslateY(300);
        addtoofocuslist(Labelclick);
        background.getChildren().add(Labelclick);
        getfilenew(Labelclick, this);

        Label feeLabel = new Label("Late fee");
        feeLabel.setPrefSize(100, 50);
        feeLabel.setTranslateY(370);
        feeLabel.getStyleClass().add("addinglabel");
        background.getChildren().add(feeLabel);

        Label poundlabel = new Label("£");
        poundlabel.setPrefSize(50, 50);
        poundlabel.setTranslateY(370);
        poundlabel.getStyleClass().add("poundlabel");
        background.getChildren().add(poundlabel);

        TextField poundfield = new TextField("0.0");
        poundfield.setPrefSize(50, 50);
        poundfield.setTranslateY(370);
        poundfield.getStyleClass().add("poundfield");
        background.getChildren().add(poundfield);
        addtoofocuslist(poundfield);

        Pane rentbuttonpane = new Pane();
        rentbuttonpane.setPrefSize(280, 50);
        rentbuttonpane.setTranslateY(370);
        rentbuttonpane.setTranslateX(270);
        rentbuttonpane.getStyleClass().add("rentbuttonpane");

        Pane Dailyfiller = new Pane();
        Dailyfiller.setPrefSize(62, 50);
        Dailyfiller.setTranslateX(31);
        Dailyfiller.getStyleClass().add("selectorfiller");
        addtoofocuslist(Dailyfiller);

        Label Daily = new Label("Daily");
        Daily.setPrefSize(62, 50);
        Daily.getStyleClass().add("Selectorlabel");
        Dailyfiller.getChildren().add(Daily);

        Pane Weeklyfiller = new Pane();
        Weeklyfiller.setPrefSize(62, 50);
        Weeklyfiller.setTranslateX(124);
        Weeklyfiller.getStyleClass().add("selectorfiller");
        addtoofocuslist(Weeklyfiller);

        Label Weekly = new Label("Weekly");
        Weekly.setPrefSize(62, 50);
        Weekly.getStyleClass().add("Selectorlabel");
        Weeklyfiller.getChildren().add(Weekly);

        Pane Monthlyfiller = new Pane();
        Monthlyfiller.setPrefSize(62, 50);
        Monthlyfiller.setTranslateX(217);
        Monthlyfiller.getStyleClass().add("selectorfiller");
        addtoofocuslist(Monthlyfiller);

        Label Monthly = new Label("Monthly");
        Monthly.setPrefSize(62, 50);
        Monthly.getStyleClass().add("Selectorlabel");
        Monthlyfiller.getChildren().add(Monthly);

        rentbuttonpane.getChildren().add(Dailyfiller);
        rentbuttonpane.getChildren().add(Weeklyfiller);
        rentbuttonpane.getChildren().add(Monthlyfiller);
        background.getChildren().add(rentbuttonpane);

        Label labelduration = new Label("Rent period");
        labelduration.setPrefSize(100, 50);
        labelduration.getStyleClass().add("addinglabel");
        labelduration.setTranslateY(440);
        background.getChildren().add(labelduration);

        TextField textfieldduration = new TextField();
        textfieldduration.setPrefSize(200, 50);
        textfieldduration.getStyleClass().add("addfield");
        textfieldduration.setTranslateY(440);
        addtoofocuslist(textfieldduration);
        background.getChildren().add(textfieldduration);

        Label labelperiod = new Label("");
        labelperiod.setPrefSize(80, 50);
        labelperiod.getStyleClass().add("labelperiod");
        labelperiod.setTranslateY(440);
        labelperiod.setTranslateX(450);
        background.getChildren().add(labelperiod);

        typeassignment(Dailyfiller, Weeklyfiller, Monthlyfiller, labelperiod);
        typeassignment(Weeklyfiller, Monthlyfiller, Dailyfiller, labelperiod);
        typeassignment(Monthlyfiller, Weeklyfiller, Dailyfiller, labelperiod);

        Pane addpane = new Pane();
        addpane.setPrefSize(200, 30);
        addpane.getStyleClass().add("addpane");
        addtoofocuslist(addpane);
        background.getChildren().add(addpane);
        entrydetails(addpane, texfieldname, buttonspane, textfieldedition, incrementLabel, poundfield, rentbuttonpane,
                textfieldduration, this, Labelclick);

        Label addLabel = new Label("Add item(s)");
        addLabel.setPrefSize(200, 30);
        addLabel.getStyleClass().add("addlabel");
        addpane.getChildren().add(addLabel);

        enabletabbing();

        return newentriesScene;
    }

    public File getfile() {
        return this.file;
    }

    public void setfile(File ne) {
        this.file = ne;
    }

    private void getfilenew(final Label button, final newentries fileref) {
        EventHandler<MouseEvent> changer = new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                FileChooser tempchooser = new FileChooser();
                tempchooser.setTitle("choose image");
                tempchooser.getExtensionFilters().add(new ExtensionFilter("Image Files", "**.png", "**.jpg"));
                fileref.setfile(tempchooser.showOpenDialog(Main.getstageref()));
                if (fileref.getfile() != null) {
                    button.setText(fileref.getfile().getName());
                }
            }
        };
        EventHandler<KeyEvent> keychanger = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    FileChooser tempchooser = new FileChooser();
                    tempchooser.setTitle("choose image");
                    tempchooser.getExtensionFilters().add(new ExtensionFilter("Image Files", "**.png", "**.jpg"));
                    fileref.setfile(tempchooser.showOpenDialog(Main.getstageref()));
                    if (fileref.getfile() != null) {
                        button.setText(fileref.getfile().getName());
                    }
                }
            }
        };
        button.addEventHandler(MouseEvent.MOUSE_CLICKED, changer);
        button.addEventHandler(KeyEvent.KEY_PRESSED, keychanger);
    }

    private void entrydetails(Node button, final TextField name, final Pane typepane, final TextField Edition,
            final Label amount, final TextField fee, final Pane timepane, final TextField period,
            final newentries filref, final Label photoc) {
        EventHandler<MouseEvent> changer = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                entryDetailsMethod(name, typepane, Edition, amount, fee, timepane, period, filref, photoc);
            }
        };
        EventHandler<KeyEvent> keychanger = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    entryDetailsMethod(name, typepane, Edition, amount, fee, timepane, period, filref, photoc);
                }
            }
        };
        button.addEventHandler(MouseEvent.MOUSE_CLICKED, changer);
        button.addEventHandler(KeyEvent.KEY_PRESSED, keychanger);
    }

    private void entryDetailsMethod(TextField name, Pane typepane, TextField Edition, Label amount, TextField fee,
            Pane timepane, TextField period, newentries filref, Label photoc) {
        String type = "";
        String duration = "";
        for (Node child : typepane.getChildren()) {
            child.getStyleClass().remove("selectorfiller2");
            child.getStyleClass().add("selectorfiller");
            Pane pchild = (Pane) child;
            for (String style : pchild.getStyleClass()) {
                if (style.matches("selectedstatus")) {
                    Label llabel = (Label) pchild.getChildren().get(0);
                    type = llabel.getText();
                }
            }
        }

        for (Node child : timepane.getChildren()) {
            child.getStyleClass().remove("selectorfiller2");
            child.getStyleClass().add("selectorfiller");
            Pane pchild = (Pane) child;
            for (String style : pchild.getStyleClass()) {
                if (style.matches("selectedstatus")) {
                    Label llabel = (Label) pchild.getChildren().get(0);
                    duration = llabel.getText();
                }
            }
        }
        name.getStyleClass().remove("errorborder");
        period.getStyleClass().remove("errorborder");
        photoc.getStyleClass().remove("errorborder");
        if (StringUtils.isBlank(name.getText())) {
            name.getStyleClass().add("errorborder");
        } else if (type.equals("")) {
            for (Node child : typepane.getChildren()) {
                child.getStyleClass().add("selectorfiller2");
                child.getStyleClass().remove("selectorfiller");
            }
        }

        else if (filref.getfile() == null) {
            photoc.getStyleClass().add("errorborder");
        } else if (duration.equals("")) {
            for (Node child : timepane.getChildren()) {
                child.getStyleClass().add("selectorfiller2");
                child.getStyleClass().remove("selectorfiller");
            }
        } else if (StringUtils.isBlank(period.getText())) {
            period.getStyleClass().add("errorborder");
        } else {
            try {
                Integer.parseInt(period.getText());
            } catch (NumberFormatException e) {
                period.getStyleClass().add("errorborder");
                return;
            }
            for (int i = 0; i < Integer.parseInt(amount.getText()); i++) {
                if (StringUtils.isNotBlank(Edition.getText())) {
                    if (databasehandler.insertentry(name.getText(), type, Edition.getText(),
                            Double.parseDouble(fee.getText()), duration, filref.getfile(), period.getText()) == true) {

                    }
                } else {
                    if (databasehandler.insertentry(name.getText(), type, "Default", Double.parseDouble(fee.getText()),
                            duration, filref.getfile(), period.getText()) == true) {

                    }
                    ;
                }
            }
            name.setText("");
            for (Node child : typepane.getChildren()) {
                Pane pchild = (Pane) child;
                pchild.getStyleClass().remove("selectedstatus");
            }
            for (Node child : timepane.getChildren()) {
                Pane pchild = (Pane) child;
                pchild.getStyleClass().remove("selectedstatus");
            }
            Edition.setText("");
            fee.setText("0.00");

        }

    }

    private void amountchanger(final Pane button, final Label amount) {
        EventHandler<MouseEvent> changer = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                for (String style : button.getStyleClass()) {
                    if (style.equals("leftarrow")) {
                        int value = Integer.parseInt(amount.getText());
                        value += -1;
                        if (value < 1) {
                            value = 1;
                        }
                        amount.setText(Integer.toString(value));
                    }
                    if (style.equals("rightarrow")) {
                        int value = Integer.parseInt(amount.getText());
                        value += 1;
                        amount.setText(Integer.toString(value));
                    }
                }
            }
        };
        EventHandler<KeyEvent> keychanger = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    for (String style : button.getStyleClass()) {
                        if (style.equals("leftarrow")) {
                            int value = Integer.parseInt(amount.getText());
                            value += -1;
                            if (value < 1) {
                                value = 1;
                            }
                            amount.setText(Integer.toString(value));
                        } else if (style.equals("rightarrow")) {
                            int value = Integer.parseInt(amount.getText());
                            value += 1;
                            amount.setText(Integer.toString(value));
                        }
                    }
                }
            }
        };
        button.addEventHandler(MouseEvent.MOUSE_CLICKED, changer);
        button.addEventHandler(KeyEvent.KEY_PRESSED, keychanger);
    }

}
