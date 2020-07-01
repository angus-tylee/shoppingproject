/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import static javax.swing.UIManager.get;
import org.jooby.Jooby;
import org.jooby.Results;

/**
 *
 * @author angustylee
 */
public class AssetModule extends Jooby {
    
     public AssetModule() {
        assets("/*.html");
        assets("/css/*.css");
        assets("/js/*.js");
        assets("/images/*.png");
        assets("/images/*.jpg");
        // make index.html the default page
        assets("/", "home.html");
        // prevent 404 errors due to browsers requesting favicons
        get("/favicon.ico", () -> Results.noContent());
     }
}
