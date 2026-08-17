package com.example;

import java.util.ArrayList;

import javafx.application.Application;
//import java.util.List;

import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;

public class Main extends Application {
    private static int scenenumber = 0;
    private static ArrayList<Scene> scenetargets = new ArrayList<Scene>();
    private static ArrayList<Pages> pagetargets = new ArrayList<Pages>();
    private static ArrayList<Pane> rootargets = new ArrayList<Pane>();
    private static user currentuser;
    private static boolean isadmin;
    private static String currentcategory;
    private static user currentemployee;
    private static entry currententry;
    private static customer currentcustomer;
    private static Stage stageref;

    @Override
    public void start(Stage primaryStage) throws Exception {
        stageref = primaryStage;
        databasehandler.initset();
        databasehandler.userslist();

        final Stage finalstage = primaryStage;

        Pane root = new Pane();
        root.getStyleClass().add("root");
        rootargets.add(root);

        Pane root2 = new Pane();
        root2.getStyleClass().add("root");
        rootargets.add(root2);

        Pane root3 = new Pane();
        root3.getStyleClass().add("root");
        rootargets.add(root3);

        Pane root4 = new Pane();
        root4.getStyleClass().add("root");
        rootargets.add(root4);

        Pane root5 = new Pane();
        root5.getStyleClass().add("root");
        rootargets.add(root5);

        Pane root6 = new Pane();
        root6.getStyleClass().add("root");
        rootargets.add(root6);

        Pane root7 = new Pane();
        root7.getStyleClass().add("root");
        rootargets.add(root7);

        Pane root8 = new Pane();
        root8.getStyleClass().add("root");
        rootargets.add(root8);

        Pane root9 = new Pane();
        root9.getStyleClass().add("root");
        rootargets.add(root9);

        Pane root10 = new Pane();
        root10.getStyleClass().add("root");
        rootargets.add(root10);

        Pane root11 = new Pane();
        root11.getStyleClass().add("root");
        rootargets.add(root11);

        Pane root12 = new Pane();
        root12.getStyleClass().add("root");
        rootargets.add(root12);

        Pane root13 = new Pane();
        root13.getStyleClass().add("root");
        rootargets.add(root13);

        Pane root14 = new Pane();
        root14.getStyleClass().add("root");
        rootargets.add(root14);

        Pane root15 = new Pane();
        root15.getStyleClass().add("root");
        rootargets.add(root15);

        Pane root16 = new Pane();
        root16.getStyleClass().add("root");
        rootargets.add(root16);

        Pane root17 = new Pane();
        root17.getStyleClass().add("root");
        rootargets.add(root17);

        final loginPage loginvar = new loginPage();
        pagetargets.add(loginvar);
        final Scene loginscene = loginvar.getScene(rootargets.get(0));
        scenetargets.add(loginscene);

        standardoptions standardoptionsvar = new standardoptions();
        pagetargets.add(standardoptionsvar);
        Scene standardoptionScene = standardoptionsvar.getScene(rootargets.get(1));
        scenetargets.add(standardoptionScene);

        rentcategories rentcategoriesvar = new rentcategories();
        pagetargets.add(rentcategoriesvar);
        Scene rentcategoriesScene = rentcategoriesvar.getScene(rootargets.get(2));
        scenetargets.add(rentcategoriesScene);

        itemreturn itemreturnvar = new itemreturn();
        pagetargets.add(itemreturnvar);
        Scene itemreturnScene = itemreturnvar.getScene(rootargets.get(3));
        scenetargets.add(itemreturnScene);

        customermenu customermenuvar = new customermenu();
        pagetargets.add(customermenuvar);
        Scene customermenuScene = customermenuvar.getScene(rootargets.get(4));
        scenetargets.add(customermenuScene);

        categoryitems categoryitemsvar = new categoryitems();
        pagetargets.add(categoryitemsvar);
        Scene categoryitemsScene = categoryitemsvar.getScene(rootargets.get(5));
        scenetargets.add(categoryitemsScene);

        addcustomer addcustomervar = new addcustomer();
        pagetargets.add(addcustomervar);
        Scene addcustomerScene = addcustomervar.getScene(rootargets.get(6));
        scenetargets.add(addcustomerScene);

        viewcustomers viewcustomersvar = new viewcustomers();
        pagetargets.add(viewcustomersvar);
        Scene viewcustomersScene = viewcustomersvar.getScene(rootargets.get(7));
        scenetargets.add(viewcustomersScene);
        /* space for itemdetail customerdetail */
        adminoptions adminoptionsvar = new adminoptions();
        pagetargets.add(adminoptionsvar);
        Scene adminoptionsScene = adminoptionsvar.getScene(rootargets.get(8));
        scenetargets.add(adminoptionsScene);

        newentries newentriesvar = new newentries();
        pagetargets.add(newentriesvar);
        Scene newentriesScene = newentriesvar.getScene(rootargets.get(9));
        scenetargets.add(newentriesScene);

        viewentries viewentriesvar = new viewentries();
        pagetargets.add(viewentriesvar);
        Scene viewentriesScene = viewentriesvar.getScene(rootargets.get(10));
        scenetargets.add(viewentriesScene);

        newemployee newemployeevar = new newemployee();
        pagetargets.add(newemployeevar);
        Scene newemployeeScene = newemployeevar.getScene(rootargets.get(11));
        scenetargets.add(newemployeeScene);

        viewemployees viewemployeesvar = new viewemployees();
        pagetargets.add(viewemployeesvar);
        Scene viewemployeesScene = viewemployeesvar.getScene(rootargets.get(12));
        scenetargets.add(viewemployeesScene);

        editemployee editemployeevar = new editemployee();
        pagetargets.add(editemployeevar);
        Scene editemployeeScene = editemployeevar.getScene(rootargets.get(13));
        scenetargets.add(editemployeeScene);

        editentry editentryvar = new editentry();
        pagetargets.add(editentryvar);
        Scene editentryScene = editentryvar.getScene(rootargets.get(14));
        scenetargets.add(editentryScene);

        editcustomer editcustomervar = new editcustomer();
        pagetargets.add(editcustomervar);
        Scene editcustomerScene = editcustomervar.getScene(rootargets.get(15));
        scenetargets.add(editcustomerScene);

        itemdetail itemdetailvar = new itemdetail();
        pagetargets.add(itemdetailvar);
        Scene itemdetailScene = itemdetailvar.getScene(rootargets.get(16));
        scenetargets.add(itemdetailScene);

        EventHandler<MouseEvent> pageswitcher = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (scenetargets.get(scenenumber) != finalstage.getScene()) {
                    if (finalstage.getScene() == loginscene) {

                        if (isadmin && loginvar.getonadmin() == true) {
                            scenenumber = 8;
                        } else if (!isadmin && loginvar.getonadmin() == true) {
                            scenenumber = 0;
                            return;
                        } else {
                            scenenumber = 1;
                        }
                    }

                    if (scenenumber == 5) {
                        pagetargets.set(scenenumber, new categoryitems());
                        rootargets.set(scenenumber, new Pane());
                        rootargets.get(scenenumber).getStyleClass().add("root");
                        scenetargets.set(scenenumber,
                                pagetargets.get(scenenumber).getScene(rootargets.set(scenenumber, new Pane())));
                    }

                    if (scenenumber == 7) {
                        if (databasehandler.getchange("customers") == 1) {
                            pagetargets.set(scenenumber, new viewcustomers());
                            rootargets.set(scenenumber, new Pane());
                            rootargets.get(scenenumber).getStyleClass().add("root");
                            scenetargets.set(scenenumber,
                                    pagetargets.get(scenenumber).getScene(rootargets.set(scenenumber, new Pane())));
                        }
                    }
                    if (scenenumber == 10) {
                        if (databasehandler.getchange("entries") == 1) {
                            pagetargets.set(scenenumber, new viewentries());
                            rootargets.set(scenenumber, new Pane());
                            rootargets.get(scenenumber).getStyleClass().add("root");
                            scenetargets.set(scenenumber,
                                    pagetargets.get(scenenumber).getScene(rootargets.set(scenenumber, new Pane())));
                        }
                    }
                    if (scenenumber == 12) {
                        if (databasehandler.getchange("users") == 1) {
                            pagetargets.set(scenenumber, new viewemployees());
                            rootargets.set(scenenumber, new Pane());
                            rootargets.get(scenenumber).getStyleClass().add("root");
                            scenetargets.set(scenenumber,
                                    pagetargets.get(scenenumber).getScene(rootargets.set(scenenumber, new Pane())));
                        }
                    }
                    if (scenenumber == 13) {
                        pagetargets.set(scenenumber, new editemployee());
                        rootargets.set(scenenumber, new Pane());
                        rootargets.get(scenenumber).getStyleClass().add("root");
                        scenetargets.set(scenenumber,
                                pagetargets.get(scenenumber).getScene(rootargets.set(scenenumber, new Pane())));
                    }
                    if (scenenumber == 14) {
                        pagetargets.set(scenenumber, new editentry());
                        rootargets.set(scenenumber, new Pane());
                        rootargets.get(scenenumber).getStyleClass().add("root");
                        scenetargets.set(scenenumber,
                                pagetargets.get(scenenumber).getScene(rootargets.set(scenenumber, new Pane())));
                    }
                    if (scenenumber == 15) {
                        pagetargets.set(scenenumber, new editcustomer());
                        rootargets.set(scenenumber, new Pane());
                        rootargets.get(scenenumber).getStyleClass().add("root");
                        scenetargets.set(scenenumber,
                                pagetargets.get(scenenumber).getScene(rootargets.set(scenenumber, new Pane())));
                    }
                    if (scenenumber == 16) {
                        pagetargets.set(scenenumber, new itemdetail());
                        rootargets.set(scenenumber, new Pane());
                        rootargets.get(scenenumber).getStyleClass().add("root");
                        scenetargets.set(scenenumber,
                                pagetargets.get(scenenumber).getScene(rootargets.set(scenenumber, new Pane())));
                    }

                    if (scenenumber != 0) {
                        pagetargets.get(scenenumber).triggeradminable(isadmin);

                    }
                    pagetargets.get(scenenumber).onpagentercheck();
                    finalstage.setScene(scenetargets.get(scenenumber));

                    finalstage.show();
                }
            }
        };

        EventHandler<KeyEvent> keypageswitcher = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    if (scenetargets.get(scenenumber) != finalstage.getScene()) {
                        if (finalstage.getScene() == loginscene) {
                            if (isadmin && loginvar.getonadmin() == true) {
                                scenenumber = 8;
                            } else if (!isadmin && loginvar.getonadmin() == true) {
                                scenenumber = 0;
                                return;
                            } else {
                                scenenumber = 1;
                            }
                        }

                        if (scenenumber == 5) {
                            pagetargets.set(scenenumber, new categoryitems());
                            rootargets.set(scenenumber, new Pane());
                            rootargets.get(scenenumber).getStyleClass().add("root");
                            scenetargets.set(scenenumber,
                                    pagetargets.get(scenenumber).getScene(rootargets.set(scenenumber, new Pane())));
                        }

                        if (scenenumber == 7) {
                            if (databasehandler.getchange("customers") == 1) {
                                pagetargets.set(scenenumber, new viewcustomers());
                                rootargets.set(scenenumber, new Pane());
                                rootargets.get(scenenumber).getStyleClass().add("root");
                                scenetargets.set(scenenumber,
                                        pagetargets.get(scenenumber).getScene(rootargets.set(scenenumber, new Pane())));
                            }
                        }
                        if (scenenumber == 10) {
                            if (databasehandler.getchange("entries") == 1) {
                                pagetargets.set(scenenumber, new viewentries());
                                rootargets.set(scenenumber, new Pane());
                                rootargets.get(scenenumber).getStyleClass().add("root");
                                scenetargets.set(scenenumber,
                                        pagetargets.get(scenenumber).getScene(rootargets.set(scenenumber, new Pane())));
                            }
                        }
                        if (scenenumber == 12) {
                            if (databasehandler.getchange("users") == 1) {
                                pagetargets.set(scenenumber, new viewemployees());
                                rootargets.set(scenenumber, new Pane());
                                rootargets.get(scenenumber).getStyleClass().add("root");
                                scenetargets.set(scenenumber,
                                        pagetargets.get(scenenumber).getScene(rootargets.set(scenenumber, new Pane())));
                            }
                        }
                        if (scenenumber == 13) {
                            pagetargets.set(scenenumber, new editemployee());
                            rootargets.set(scenenumber, new Pane());
                            rootargets.get(scenenumber).getStyleClass().add("root");
                            scenetargets.set(scenenumber,
                                    pagetargets.get(scenenumber).getScene(rootargets.set(scenenumber, new Pane())));
                        }
                        if (scenenumber == 14) {
                            pagetargets.set(scenenumber, new editentry());
                            rootargets.set(scenenumber, new Pane());
                            rootargets.get(scenenumber).getStyleClass().add("root");
                            scenetargets.set(scenenumber,
                                    pagetargets.get(scenenumber).getScene(rootargets.set(scenenumber, new Pane())));
                        }
                        if (scenenumber == 15) {
                            pagetargets.set(scenenumber, new editcustomer());
                            rootargets.set(scenenumber, new Pane());
                            rootargets.get(scenenumber).getStyleClass().add("root");
                            scenetargets.set(scenenumber,
                                    pagetargets.get(scenenumber).getScene(rootargets.set(scenenumber, new Pane())));
                        }
                        if (scenenumber == 16) {
                            pagetargets.set(scenenumber, new itemdetail());
                            rootargets.set(scenenumber, new Pane());
                            rootargets.get(scenenumber).getStyleClass().add("root");
                            scenetargets.set(scenenumber,
                                    pagetargets.get(scenenumber).getScene(rootargets.set(scenenumber, new Pane())));
                        }

                        if (scenenumber != 0) {
                            pagetargets.get(scenenumber).triggeradminable(isadmin);

                        }
                        pagetargets.get(scenenumber).onpagentercheck();
                        finalstage.setScene(scenetargets.get(scenenumber));

                        finalstage.show();
                    }
                }
            }
        };

        primaryStage.addEventHandler(MouseEvent.MOUSE_CLICKED, pageswitcher);
        primaryStage.addEventHandler(KeyEvent.KEY_PRESSED, keypageswitcher);

        primaryStage.setTitle("Hello World");
        loginvar.onpagentercheck();
        primaryStage.setScene(loginscene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static void switchtarget(int changeScene) {
        scenenumber = changeScene;
    }

    public static int getScenenumber() {
        return scenenumber;
    }

    public static String getcategory() {
        return currentcategory;
    }

    public static void changecategory(String categorychange) {
        currentcategory = categorychange;
    }

    public static void alteradmin(user account) {
        currentuser = account;
        if (currentuser == null || currentuser.getadmin() == 0) {
            isadmin = false;
        } else if (currentuser.getadmin() == 1) {
            isadmin = true;
        }
        pagetargets.set(12, new viewemployees());
        rootargets.set(12, new Pane());
        rootargets.get(12).getStyleClass().add("root");
        scenetargets.set(12, pagetargets.get(12).getScene(rootargets.set(12, new Pane())));
    }

    public static void setcurrentemployee(user newemployee) {
        currentemployee = newemployee;
    }

    public static user getcurrentemployee() {
        return currentemployee;
    }

    public static void setcurrententry(entry newentry) {
        currententry = newentry;

    }

    public static entry getcurrententry() {
        return currententry;
    }

    public static void setcurrentcustomer(customer newcustomer) {
        currentcustomer = newcustomer;

    }

    public static customer getcurrentcustomer() {
        return currentcustomer;
    }

    public static void setcurrentuser(user newemployee) {
        currentuser = newemployee;
    }

    public static user getcurrentuser() {
        return currentuser;
    }
    public static Stage getstageref() {
        return stageref;
    }

}