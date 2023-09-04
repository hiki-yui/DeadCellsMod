package deadCellsMod.cn.infinite.stsmod.effects;

import basemod.interfaces.ScreenPostProcessor;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class FocusProcessor implements ScreenPostProcessor {

    public AbstractMonster target;
    private float x;
    private float y;
    private float vX;
    private float vY;
    private float duration;
    private float scale;
    public FocusProcessor(AbstractMonster target){
        this.target = target;
        this.duration = 1.0F;
        this.scale = 1.0F;

        this.x = 0.0F;
        this.y = 0.0F;
        this.vX = (Settings.M_W - target.hb.x) * 2.5F - Settings.M_W / 5.0F;
        this.vY = (Settings.M_H - target.hb.cY) * 2.5F - Settings.M_H / 1.5F;
    }

    @Override
    public void postProcess(SpriteBatch spriteBatch, TextureRegion textureRegion, OrthographicCamera orthographicCamera) {
        spriteBatch.setColor(Color.WHITE);
        spriteBatch.setBlendFunction(GL20.GL_ONE, GL20.GL_ZERO);

        this.duration -= Gdx.graphics.getDeltaTime();
        if (this.duration > 0.0F) {
            this.scale = (1.0F - this.duration) * 0.8F + 1.2F;
            this.x -= this.vX * Gdx.graphics.getDeltaTime();
            this.y -= this.vY * Gdx.graphics.getDeltaTime();
        } else {
            this.scale = 2.0F;
        }
        int width = (int)(Settings.M_W * this.scale);
        int height = (int)(Settings.M_H * this.scale);
        CardCrawlGame.viewport.setScreenBounds((int)x, (int)y, width, height);
        CardCrawlGame.viewport.apply();

        spriteBatch.draw(textureRegion, 0, 0);
    }
}
