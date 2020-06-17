/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import com.codename1.ui.Font;
import com.codename1.ui.Label;

/**
 *
 * @author abdel
 */
public class Statics {
     public static final String BASE_URL="http://127.0.0.1:8000/";
        //public static final String IMAGE_URL="http://localhost/abdouu/web/uploads/images/";
    
     public static void setLabelStyle(Label l){
        l.getUnselectedStyle().setFgColor(-16777216);
        l.getUnselectedStyle().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_ITALIC, Font.SIZE_MEDIUM));
    }
     
}
