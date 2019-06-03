package com.forest.tank;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ResourceManagers {

    public static BufferedImage tankL, tankU, tankR, tankD;
    public static BufferedImage bulletL,bulletU,bulletR,bulletD;
    public static BufferedImage[] explodes = new BufferedImage[16];

    static {
        try {
            tankL = ImageIO.read(ResourceManagers.class.getClassLoader().getResourceAsStream("images/tankL.gif"));
            tankU = ImageIO.read(ResourceManagers.class.getClassLoader().getResourceAsStream("images/tankU.gif"));
            tankR = ImageIO.read(ResourceManagers.class.getClassLoader().getResourceAsStream("images/tankR.gif"));
            tankD = ImageIO.read(ResourceManagers.class.getClassLoader().getResourceAsStream("images/tankD.gif"));
            bulletL = ImageIO.read(ResourceManagers.class.getClassLoader().getResourceAsStream("images/bulletL.gif"));
            bulletU = ImageIO.read(ResourceManagers.class.getClassLoader().getResourceAsStream("images/bulletU.gif"));
            bulletR = ImageIO.read(ResourceManagers.class.getClassLoader().getResourceAsStream("images/bulletR.gif"));
            bulletD = ImageIO.read(ResourceManagers.class.getClassLoader().getResourceAsStream("images/bulletD.gif"));
            for(int i=0; i<16; i++)
                explodes[i] = ImageIO.read(ResourceManagers.class.getClassLoader().getResourceAsStream("images/e" + (i+1) + ".gif"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
