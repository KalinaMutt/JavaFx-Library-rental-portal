package com.example;

import java.io.File;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.Node;

import javafx.stage.FileChooser;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.event.EventHandler;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.commons.lang3.StringUtils;

public class editentry extends Pages {

    private File file = null;

    public Scene getScene(Pane root) {
        thisscenenumber = 14;
        thisscenetarget = 10;
        root.setMinSize(600, 600);
        root.setManaged(false);

        Scene editentriescene = new Scene(root, 600, 600);
        editentriescene.getStylesheets().add("css/editentries.css");

        Pane background = createbackgrounds(root, thisscenenumber, thisscenetarget, true);
        entry currententry = Main.getcurrententry();
        if (currententry != null) {
            Label entrytitle = new Label(Integer.toString(currententry.getid()));
            entrytitle.setPrefSize(300, 30);
            entrytitle.getStyleClass().add("addtitle");
            background.getChildren().add(entrytitle);

            Label labelname = new Label("Name");
            labelname.setPrefSize(100, 50);
            labelname.getStyleClass().add("addinglabel");
            labelname.setTranslateY(70);
            background.getChildren().add(labelname);

            TextField textfieldname = new TextField(currententry.getname());
            textfieldname.setPrefSize(350, 50);
            textfieldname.getStyleClass().add("addfield");
            textfieldname.setTranslateY(70);
            addtoofocuslist(textfieldname);
            background.getChildren().add(textfieldname);

            Label labeltype = new Label("Type");
            labeltype.setPrefSize(100, 50);
            labeltype.getStyleClass().add("addinglabel");
            labeltype.setTranslateY(140);
            background.getChildren().add(labeltype);

            Pane buttonspane = new Pane();
            buttonspane.setPrefSize(350, 50);
            buttonspane.setTranslateY(140);
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

            if (currententry.gettype().equals("Vinyl")) {
                Vinylfiller.getStyleClass().add("selectedstatus");
            } else if (currententry.gettype().equals("DVD")) {
                DVDfiller.getStyleClass().add("selectedstatus");
            } else if (currententry.gettype().equals("Tool")) {
                Toolfiller.getStyleClass().add("selectedstatus");
            }

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
            labeledition.setTranslateY(210);
            background.getChildren().add(labeledition);

            TextField textfieldedition = new TextField(currententry.getedition());
            textfieldedition.setPrefSize(350, 50);
            textfieldedition.getStyleClass().add("addfield");
            textfieldedition.setTranslateY(210);
            addtoofocuslist(textfieldedition);
            background.getChildren().add(textfieldedition);

            Label labelphoto = new Label("Photo");
            labelphoto.setPrefSize(100, 40);
            labelphoto.getStyleClass().add("addinglabel");
            labelphoto.setTranslateY(280);
            background.getChildren().add(labelphoto);

            Label Labelclick = new Label(currententry.getpath());
            Labelclick.setPrefSize(350, 40);
            Labelclick.getStyleClass().add("addfield");
            Labelclick.setTranslateY(280);
            addtoofocuslist(Labelclick);
            background.getChildren().add(Labelclick);
            getfilenew(Labelclick, this);

            Label labelfee = new Label("Late fee");
            labelfee.setPrefSize(100, 40);
            labelfee.getStyleClass().add("addinglabel");
            labelfee.setTranslateY(340);
            background.getChildren().add(labelfee);

            Label poundlabel = new Label("£");
            poundlabel.setPrefSize(50, 40);
            poundlabel.setTranslateY(340);
            poundlabel.getStyleClass().add("poundlabel");
            background.getChildren().add(poundlabel);

            TextField poundfield = new TextField(Double.toString(currententry.getfee()));
            poundfield.setPrefSize(50, 40);
            poundfield.setTranslateY(340);
            poundfield.getStyleClass().add("poundfield");
            background.getChildren().add(poundfield);
            addtoofocuslist(poundfield);

            Pane rentbuttonpane = new Pane();
            rentbuttonpane.setPrefSize(280, 40);
            rentbuttonpane.setTranslateY(340);
            rentbuttonpane.setTranslateX(270);
            rentbuttonpane.getStyleClass().add("rentbuttonpane");

            Pane Dailyfiller = new Pane();
            Dailyfiller.setPrefSize(62, 40);
            Dailyfiller.setTranslateX(31);
            Dailyfiller.getStyleClass().add("selectorfiller");
            addtoofocuslist(Dailyfiller);

            Label Daily = new Label("Daily");
            Daily.setPrefSize(62, 40);
            Daily.getStyleClass().add("Selectorlabel");
            Dailyfiller.getChildren().add(Daily);

            Pane Weeklyfiller = new Pane();
            Weeklyfiller.setPrefSize(62, 40);
            Weeklyfiller.setTranslateX(124);
            Weeklyfiller.getStyleClass().add("selectorfiller");
            addtoofocuslist(Weeklyfiller);

            Label Weekly = new Label("Weekly");
            Weekly.setPrefSize(62, 40);
            Weekly.getStyleClass().add("Selectorlabel");
            Weeklyfiller.getChildren().add(Weekly);

            Pane Monthlyfiller = new Pane();
            Monthlyfiller.setPrefSize(62, 40);
            Monthlyfiller.setTranslateX(217);
            Monthlyfiller.getStyleClass().add("selectorfiller");
            addtoofocuslist(Monthlyfiller);

            Label Monthly = new Label("Monthly");
            Monthly.setPrefSize(62, 40);
            Monthly.getStyleClass().add("Selectorlabel");
            Monthlyfiller.getChildren().add(Monthly);

            rentbuttonpane.getChildren().add(Dailyfiller);
            rentbuttonpane.getChildren().add(Weeklyfiller);
            rentbuttonpane.getChildren().add(Monthlyfiller);
            background.getChildren().add(rentbuttonpane);

            Label labelduration = new Label("Rent period");
            labelduration.setPrefSize(100, 40);
            labelduration.getStyleClass().add("addinglabel");
            labelduration.setTranslateY(390);
            background.getChildren().add(labelduration);

            TextField textfieldduration = new TextField(Integer.toString(currententry.getperiod()));
            textfieldduration.setPrefSize(200, 40);
            textfieldduration.getStyleClass().add("addfield");
            textfieldduration.setTranslateY(390);
            addtoofocuslist(textfieldduration);
            background.getChildren().add(textfieldduration);

            Label labelperiod = new Label("");
            labelperiod.setPrefSize(80, 40);
            labelperiod.getStyleClass().add("labelperiod");
            labelperiod.setTranslateY(390);
            labelperiod.setTranslateX(450);
            background.getChildren().add(labelperiod);

            typeassignment(Dailyfiller, Weeklyfiller, Monthlyfiller, labelperiod);
            typeassignment(Weeklyfiller, Monthlyfiller, Dailyfiller, labelperiod);
            typeassignment(Monthlyfiller, Weeklyfiller, Dailyfiller, labelperiod);

            if (currententry.getduration().equals("Daily")) {
                Dailyfiller.getStyleClass().add("selectedstatus");
                labelperiod.setText("Days");
            }
            if (currententry.getduration().equals("Weekly")) {
                Weeklyfiller.getStyleClass().add("selectedstatus");
                labelperiod.setText("Weeks");
            }
            if (currententry.getduration().equals("Monthly")) {
                Monthlyfiller.getStyleClass().add("selectedstatus");
                labelperiod.setText("Months");
            }

            Pane changepane = new Pane();
            changepane.setPrefSize(200, 40);
            changepane.getStyleClass().add("changepane");
            addtoofocuslist(changepane);
            background.getChildren().add(changepane);
            entrychange(changepane, currententry.getid(), textfieldname, buttonspane, textfieldedition, poundfield,
                    rentbuttonpane, textfieldduration, this, Labelclick);

            Label changelabel = new Label("Confirm changes");
            changelabel.setPrefSize(200, 40);
            changelabel.getStyleClass().add("changelabel");
            changepane.getChildren().add(changelabel);

            Pane printpane = new Pane();
            printpane.setPrefSize(200, 40);
            printpane.getStyleClass().add("printpane");
            addtoofocuslist(printpane);
            printitem(printpane, currententry);
            background.getChildren().add(printpane);

            Label printlabel = new Label("Print to file");
            printlabel.setPrefSize(200, 40);
            printlabel.getStyleClass().add("printlabel");
            printpane.getChildren().add(printlabel);

            Pane deletepane = new Pane();
            deletepane.setPrefSize(500, 50);
            deletepane.getStyleClass().add("deletepane");
            addtoofocuslist(deletepane);
            entryremove(deletepane, currententry, 10);
            background.getChildren().add(deletepane);

            Label deletelabel = new Label("Delete entry");
            deletelabel.setPrefSize(500, 50);
            deletelabel.getStyleClass().add("deletelabel");
            deletepane.getChildren().add(deletelabel);

        }
        enabletabbing();
        return editentriescene;
    }

    private File getfile() {
        return this.file;
    }

    private void setfile(File ne) {
        this.file = ne;
    }

    private void getfilenew(final Label button, final editentry fileref) {
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

    private void entryremove(final Node button, final entry info, final int scenenumber) {
        EventHandler<MouseEvent> changer = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Pane tempPane = (Pane) button;
                Label tempLabel = (Label) tempPane.getChildren().get(0);
                if (tempLabel.getText().equals("Delete entry")) {
                    tempLabel.setText("Confirm?");

                } else if (tempLabel.getText().equals("Confirm?")) {
                    int temper = databasehandler.removeentry(info.getid());
                    if (temper == -1) {
                        Main.switchtarget(scenenumber);
                    } else if (temper != -2) {
                        tempLabel.setText("Rented/Reserved by id: " + temper);
                    }
                }
            }
        };
        EventHandler<KeyEvent> keychanger = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    Pane tempPane = (Pane) button;
                    Label tempLabel = (Label) tempPane.getChildren().get(0);
                    if (tempLabel.getText().equals("Delete entry")) {
                        tempLabel.setText("Confirm?");

                    } else if (tempLabel.getText().equals("Confirm?")) {
                        int temper = databasehandler.removeentry(info.getid());
                        if (temper == -1) {
                            Main.switchtarget(scenenumber);
                        } else if (temper != -2) {
                            tempLabel.setText("Rented/Reserved by id: " + temper);
                        }
                    }
                }
            }
        };
        button.addEventHandler(MouseEvent.MOUSE_CLICKED, changer);
        button.addEventHandler(KeyEvent.KEY_PRESSED, keychanger);
    }

    private void entrychange(Node button, final int id, final TextField name, final Pane typepane,
            final TextField Edition, final TextField fee, final Pane timepane, final TextField period,
            final editentry filref, final Label photoc) {
        EventHandler<MouseEvent> changer = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                entryChangeMethod(id, name, typepane, Edition, fee, timepane, period, filref, photoc);
            }
        };
        EventHandler<KeyEvent> keychanger = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    entryChangeMethod(id, name, typepane, Edition, fee, timepane, period, filref, photoc);
                }
            }
        };
        button.addEventHandler(MouseEvent.MOUSE_CLICKED, changer);
        button.addEventHandler(KeyEvent.KEY_PRESSED, keychanger);
    }

    private void entryChangeMethod(int id, TextField name, Pane typepane, TextField Edition, TextField fee,
            Pane timepane, TextField period, editentry filref, Label photoc) {
        String type = "";
        String duration = "";
        String temped = "Default";
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

        else if (duration.equals("")) {
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
            if (StringUtils.isBlank(Edition.getText())) {
                temped = "Default";
            } else {
                temped = Edition.getText();
            }
            if (databasehandler.changeentry(id, name.getText(), type, Edition.getText(),
                    Double.parseDouble(fee.getText()), duration, period.getText()) == true) {
                if (filref.getfile() != null) {
                    if (databasehandler.changefile(filref.getfile(), id, name.getText(), temped, type)) {

                    }
                }
                Main.switchtarget(10);
            }

        }

    }

    private void printitem(final Node button, final entry ent) {
        EventHandler<MouseEvent> getfield = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (printItemMethod(ent)) {
                    Pane tempPane=(Pane) button;
                    Label tempLabel=(Label) tempPane.getChildren().get(0);
                    tempLabel.setText("Printed to " +ent.getid()+".txt");
                }
            }
        };

        EventHandler<KeyEvent> keygetfield = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    if (printItemMethod(ent)) {
                        Pane tempPane=(Pane) button;
                        Label tempLabel=(Label) tempPane.getChildren().get(0);
                        tempLabel.setText("Printed to " +ent.getid()+".txt");
                    }
                }
            }
        };
        button.addEventHandler(MouseEvent.MOUSE_CLICKED, getfield);
        button.addEventHandler(KeyEvent.KEY_PRESSED, keygetfield);
    }

    private boolean printItemMethod(entry ent) {
        try {
            new File("..\\..\\items").mkdir();
            File tempfile = new File("..\\..\\items/" + ent.getid() + ".txt");
            tempfile.createNewFile();

            String periodtext = "";
            if (ent.getduration().equals("Daily")) {
                periodtext = "Days";
            } else if (ent.getduration().equals("Weekly")) {
                periodtext = "Weeks";
            } else if (ent.getduration().equals("Monthly")) {
                periodtext = "Months";
            }
            FileWriter writer = new FileWriter("..\\..\\items/" + ent.getid() + ".txt");
            writer.write("id: " + ent.getid() + "\n" + "name: " + ent.getname() + "\n" + "type: " + ent.gettype() + "\n"
                    + "edition: " + ent.getedition() + "\n" +
                    "fee: " + ent.getfee() + " " + ent.getduration() + "\n" + "renter: " + ent.getrentedby() + "\n"
                    + "reserver: " + ent.getreservedby() + "\n" +
                    "startdate: " + ent.getstarttime() + "\n" + "enddate: " + ent.getendtime() + "\n" + "rental period:"
                    + ent.getperiod() + " " + periodtext + "\n Total stock: " + databasehandler.getcopies(ent.gettype(), 
                    ent.getname(), ent.getedition(),true) + "\n Copies available to rent: " +databasehandler.getcopies(
                        ent.gettype(), ent.getname(), ent.getedition(),false) );
            writer.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
