/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.Station;
import Entities.User;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import Utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import Gui.Home;
import Services.ServiceCategorie;
import Services.ServiceEvent;
import Services.ServiceStation;
import com.codename1.components.FloatingActionButton;
import com.codename1.components.InteractionDialog;
import com.codename1.components.ToastBar;
import com.codename1.googlemaps.MapContainer;
import com.codename1.googlemaps.MapContainer.MapObject;
import com.codename1.io.Util;
import com.codename1.l10n.ParseException;
import com.codename1.maps.BoundingBox;
import com.codename1.maps.Coord;
import com.codename1.maps.MapListener;
import com.codename1.ui.Component;
import static com.codename1.ui.ComponentSelector.$;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.geom.Rectangle;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import javafx.scene.control.Separator;

/**
 *
 * @author bhk
 */
public class MapForm extends Form {

    private MapForm current;
    private static final String HTML_API_KEY = "AIzaSyAOJFyxY9EnuYejfhhnfQiIl5wtE4KXPiM";
    private EncodedImage enc;
    Resources theme;
    MapObject sydney;
    boolean tapDisabled = false;
    private ArrayList<Station> stations;

    public MapForm(String username) {
        current = this;
        Home t = new Home(username);
        t.addMenu(this, username);
        try {
            enc = EncodedImage.create("/loading.png");
        } catch (IOException ex) {
            System.out.println(ex);
        }

        this.setLayout(new BorderLayout());
        final MapContainer cnt = new MapContainer(HTML_API_KEY);
        //final MapContainer cnt = new MapContainer();
        cnt.setCameraPosition(new Coord(36.8692036, 10.342833899999999));//this breaks the code //because the Google map is not loaded yet
        cnt.addMapListener(new MapListener() {

            @Override
            public void mapPositionUpdated(Component source, int zoom, Coord center) {
                System.out.println("Map position updated: zoom=" + zoom + ", Center=" + center);
            }

        });

        cnt.addLongPressListener(e -> {
            System.out.println("Long press");
            ToastBar.showMessage("Received longPress at " + e.getX() + ", " + e.getY(), FontImage.MATERIAL_3D_ROTATION);
        });
        cnt.addTapListener(e -> {
            ToastBar.showMessage("Received tap at " + e.getX() + ", " + e.getY(), FontImage.MATERIAL_3D_ROTATION);
        });

        int maxZoom = cnt.getMaxZoom();
        System.out.println("Max zoom is " + maxZoom);
        Button btnMoveCamera = new Button("Move Camera");
        btnMoveCamera.addActionListener(e -> {
            cnt.setCameraPosition(new Coord(36.8692036, 10.342833899999999));
        });
        Style s = new Style();
        s.setFgColor(0xff0000);
        s.setBgTransparency(0);
        FontImage markerImg = FontImage.createMaterial(FontImage.MATERIAL_PLACE, s, 3);

        //Add Marker
        /* Button btnAddMarker = new Button("Add Marker");
        btnAddMarker.addActionListener(e->{
           
            cnt.setCameraPosition(new Coord(41.889, -87.622));
            cnt.addMarker(EncodedImage.createFromImage(markerImg, false), cnt.getCameraPosition(), "Hi marker", "Optional long description", new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    System.out.println("Bounding box is "+cnt.getBoundingBox());
                    ToastBar.showMessage("You clicked the marker", FontImage.MATERIAL_PLACE);
                }
            });
            
        });*/
        ServiceStation as = new ServiceStation();
        stations = as.getStation();
        for (Station a : stations) {
            System.out.println(a);
            cnt.setCameraPosition(new Coord(a.getLatitude(), a.getLongitude()));
            cnt.addMarker(EncodedImage.createFromImage(markerImg, false), cnt.getCameraPosition(), String.valueOf(a.getNomStation()), "Optional long description", new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    new InfoForm(username,a.getId()+"").show();
                }
            });
        }
        //Add Path between two coords
        /*Button btnAddPath = new Button("Add Path");
        btnAddPath.addActionListener(e->{
            
            cnt.addPath(
                    cnt.getCameraPosition(),
                    new Coord(-33.866, 151.195), // Sydney
                    new Coord(-18.142, 178.431),  // Fiji
                    new Coord(21.291, -157.821),  // Hawaii
                    new Coord(37.423, -122.091)  // Mountain View
            );
        });
         */
        //test coordinates
        /*Button testCoordPositions = $(new Button("Test Coords"))
                .addActionListener(e->{
                    Coord topLeft = cnt.getCoordAtPosition(0, 0);
                    System.out.println("Top Left is "+topLeft+" -> "+cnt.getScreenCoordinate(topLeft) +" Should be (0,0)");
                    Coord bottomRight = cnt.getCoordAtPosition(cnt.getWidth(), cnt.getHeight());
                    System.out.println("Bottom right is "+bottomRight+" -> "+cnt.getScreenCoordinate(bottomRight) + " Should be "+cnt.getWidth()+", "+cnt.getHeight());
                    Coord bottomLeft = cnt.getCoordAtPosition(0, cnt.getHeight());
                    System.out.println("Bottom Left is "+bottomLeft+" -> "+cnt.getScreenCoordinate(bottomLeft) + " Should be 0, "+cnt.getHeight());
                    Coord topRight = cnt.getCoordAtPosition(cnt.getWidth(), 0);
                    System.out.println("Top right is "+topRight + " -> "+cnt.getScreenCoordinate(topRight)+ " Should be "+cnt.getWidth()+", 0");
                    Coord center = cnt.getCoordAtPosition(cnt.getWidth()/2, cnt.getHeight()/2);
                    System.out.println("Center is "+center+" -> "+cnt.getScreenCoordinate(center)+", should be "+(cnt.getWidth()/2)+", "+(cnt.getHeight()/2));
                    EncodedImage encImg = EncodedImage.createFromImage(markerImg, false);
                    cnt.addMarker(encImg, topLeft,"Top Left", "Top Left", null);
                    cnt.addMarker(encImg, topRight, "Top Right", "Top Right", null);
                    cnt.addMarker(encImg, bottomRight, "Bottom Right", "Bottom Right", null);
                    cnt.addMarker(encImg, bottomLeft, "Bottom Left", "Bottom Left", null);
                    cnt.addMarker(encImg, center, "Center", "Center", null);
                    
                    
                })
                .asComponent(Button.class);
         */

        //toggle peut etre nestaamalha lel les détails mta3 station
        /*
        Button toggleTopMargin = $(new Button("Toggle Margin"))
                .addActionListener(e->{
                    int marginTop = $(cnt).getStyle().getMarginTop();
                    if (marginTop < Display.getInstance().getDisplayHeight() / 3) {
                        $(cnt).selectAllStyles().setMargin(Display.getInstance().getDisplayHeight() / 3, 0, 0, 0);
                    } else {
                        $(cnt).selectAllStyles().setMargin(0,0,0,0);
                    }
                    $(cnt).getComponentForm().revalidate();
                })
                .asComponent(Button.class);*/
 MapObject mo = cnt.addMarker(EncodedImage.createFromImage(markerImg, false), new Coord(-33.866, 151.195), "test", "test",e->{
            System.out.println("Marker clicked");
            cnt.removeMapObject(sydney);
        });
        sydney = mo;
        System.out.println("MO is "+mo);
        mo = cnt.addMarker(EncodedImage.createFromImage(markerImg, false), new Coord(-18.142, 178.431), "test", "test",e->{
            System.out.println("Marker clicked");
        });
        System.out.println("MO is "+mo);
        cnt.addTapListener(e->{
            if (tapDisabled) {
                return;
            }
            tapDisabled = true;
            TextField enterName = new TextField();
            Container wrapper = BoxLayout.encloseY(new Label("Name:"), enterName);
            InteractionDialog dlg = new InteractionDialog("Add Marker");
            dlg.getContentPane().add(wrapper);
            enterName.setDoneListener(e2->{
                String txt = enterName.getText();
                cnt.addMarker(EncodedImage.createFromImage(markerImg, false), cnt.getCoordAtPosition(e.getX(), e.getY()), enterName.getText(), "", e3->{
                    ToastBar.showMessage("You clicked "+txt, FontImage.MATERIAL_PLACE);
                });
                dlg.dispose();
                tapDisabled = false;
            });
            dlg.showPopupDialog(new Rectangle(e.getX(), e.getY(), 10, 10));
            enterName.startEditingAsync();
        });
         
        Button showNextForm = $(new Button("Next Form"))
                .addActionListener(e -> {
                    Form form = new Form("Hello World");
                    Button b1 = $(new Button("B1"))
                            .addActionListener(e2 -> {
                            })
                            .asComponent(Button.class);

                    Button back = $(new Button("Back"))
                            .addActionListener(e2 -> {
                                this.showBack();
                            })
                            .asComponent(Button.class);
                    form.add(b1);
                })
                .asComponent(Button.class);

        FloatingActionButton nextForm = FloatingActionButton.createFAB(FontImage.MATERIAL_ACCESS_ALARM);
        nextForm.addActionListener(e -> {
            Form form = new Form("Hello World");
            Button b1 = $(new Button("B1"))
                    .addActionListener(e2 -> {
                        ToastBar.showMessage("B1 was pressed", FontImage.MATERIAL_3D_ROTATION);
                    })
                    .asComponent(Button.class);

            Button back = $(new Button("Back"))
                    .addActionListener(e2 -> {
                        this.showBack();
                    })
                    .asComponent(Button.class);
            form.add(b1).add(back);
            form.show();
        });

 
        //boutonet nzid'hom houni
        Container root = LayeredLayout.encloseIn(
                BorderLayout.center(nextForm.bindFabToContainer(cnt)),
                BorderLayout.south(
                        FlowLayout.encloseBottom()
                )
        );

        this.add(BorderLayout.CENTER, root);

    }

}
