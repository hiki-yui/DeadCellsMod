package deadCellsMod.cn.infinite.stsmod.patch;


import basemod.ReflectionHacks;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.modthespire.lib.SpireConfig;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.scenes.TitleBackground;
import com.megacrit.cardcrawl.scenes.TitleCloud;
import deadCellsMod.cn.infinite.stsmod.DeadCellsModInitializer;

import java.util.ArrayList;

public class MainMenuPatch {
    @SpirePatch(cls = "com.megacrit.cardcrawl.scenes.TitleBackground", method = "<ctor>")
    public static class ArtPatch {
        @SpirePostfixPatch
        public static void BackgroundTexturePatch(TitleBackground __instance) {
            if(DeadCellsModInitializer.isMainMenuImg) {
                MainMenuPatch.setMainMenuBG(__instance);
            }
        }
    }

    private static void setMainMenuBG(TitleBackground __instance) {
        Texture newLogoImage = ImageMaster.loadImage("img/ui/mainMenu/MOD.png");
        TextureAtlas newAtlas = new TextureAtlas(com.badlogic.gdx.Gdx.files.internal("img/ui/mainMenu/title.atlas"));
        if (__instance == null) {
            __instance = CardCrawlGame.mainMenuScreen.bg;
        }
        ReflectionHacks.setPrivate(__instance, TitleBackground.class, "sky", newAtlas.findRegion("jpg/sky"));
        ReflectionHacks.setPrivate(__instance, TitleBackground.class, "mg3Bot", newAtlas.findRegion("mg3Bot"));
        ReflectionHacks.setPrivate(__instance, TitleBackground.class, "mg3Top", newAtlas.findRegion("mg3Top"));
        ReflectionHacks.setPrivate(__instance, TitleBackground.class, "topGlow", newAtlas.findRegion("mg3TopGlow1"));
        ReflectionHacks.setPrivate(__instance, TitleBackground.class, "topGlow2", newAtlas.findRegion("mg3TopGlow2"));
        ReflectionHacks.setPrivate(__instance, TitleBackground.class, "botGlow", newAtlas.findRegion("mg3BotGlow"));

        ArrayList<TitleCloud> newTopClouds = new ArrayList();
        ArrayList<TitleCloud> newMidClouds = new ArrayList();

        for (int i = 1; i < 7; i++) {
            newTopClouds.add(new TitleCloud(newAtlas.findRegion("topCloud" + i),
                    MathUtils.random(10.0F, 50.0F) * Settings.scale,
                    MathUtils.random(-1920.0F, 1920.0F) * Settings.scale));
        }

        for (int i = 1; i < 13; i++) {
            newMidClouds.add(new TitleCloud(newAtlas.findRegion("midCloud" + i),
                    MathUtils.random(-50.0F, -10.0F) * Settings.scale,
                    MathUtils.random(-1920.0F, 1920.0F) * Settings.scale));
        }
        ReflectionHacks.setPrivate(__instance, TitleBackground.class, "titleLogoImg", newLogoImage);

        ReflectionHacks.setPrivate(__instance, TitleBackground.class, "topClouds", newTopClouds);

        ReflectionHacks.setPrivate(__instance, TitleBackground.class, "midClouds", newMidClouds);
    }

}
