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
import com.codename1.components.WebBrowser;
import com.codename1.googlemaps.MapContainer;
import com.codename1.googlemaps.MapContainer.MapObject;
import com.codename1.io.Util;
import com.codename1.l10n.ParseException;
import com.codename1.maps.BoundingBox;
import com.codename1.maps.Coord;
import com.codename1.maps.MapListener;
import com.codename1.ui.BrowserComponent;
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
public class InfoForm extends Form {

    private InfoForm current;
    private static final String HTML_API_KEY = "AIzaSyAOJFyxY9EnuYejfhhnfQiIl5wtE4KXPiM";
    private EncodedImage enc;
    Resources theme;
    MapObject sydney;
    boolean tapDisabled = false;
    private ArrayList<Station> stations;

    public InfoForm(String username, String id) {
        current = this;
        Home t = new Home(username);
        t.addMenu(this, username);
        try {
            enc = EncodedImage.create("/loading.png");
        } catch (IOException ex) {
            System.out.println(ex);
        }

        this.setLayout(BoxLayout.y());
        WebBrowser browser = new WebBrowser() {
            @Override
            public void onLoad(String url) {
                // Placed on onLoad because we need to wait for page 
                // to load to interact with it.
                
            }
        };
        // addComponent(BorderLayout.CENTER, browser);
        browser.setURL("http://localhost/baskeltt/web/app_dev.php/infoshow/"+id+"/"+User.userId);
        add(browser);
        getToolbar().addMaterialCommandToRightBar("Retour", FontImage.MATERIAL_ARROW_BACK, e-> new MapForm(username).show());
     
    }

}
