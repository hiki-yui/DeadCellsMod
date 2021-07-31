package deadCellsMod.cn.infinite.stsmod.utils;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class ImgUtils {

    public static final String MOD_NAME = "deadCells";

    private ImgUtils(){}

    public static void setPowerImg(AbstractPower power){
        String powerName = power.ID.replace(MOD_NAME+":","");
        power.region48 = new TextureAtlas.AtlasRegion(new Texture("img/powers/48/" + powerName + ".png"), 0, 0, 48, 48);
        power.region128 = new TextureAtlas.AtlasRegion(new Texture("img/powers/128/" + powerName + ".png"), 0, 0, 128, 128);
    }
}
