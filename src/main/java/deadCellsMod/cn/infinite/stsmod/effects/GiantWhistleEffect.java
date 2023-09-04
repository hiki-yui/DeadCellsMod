package deadCellsMod.cn.infinite.stsmod.effects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;


public class GiantWhistleEffect extends AbstractGameEffect {
    private float x;
    private float y;
    private AbstractCreature owner;
    private Texture img = ImageMaster.loadImage("img/effect/Giant Whistle.png");

    public GiantWhistleEffect(AbstractCreature owner, float x, float y) {
        this.duration = 2.0F;
        this.x = x;
        this.y = y;
        this.owner = owner;
    }


    public void render(SpriteBatch spriteBatch) {
        spriteBatch.setBlendFunction(770, 771);
        spriteBatch.setColor(Color.WHITE);
        spriteBatch.draw(this.img, this.x - 66.0F, this.y - 806.0F, 66.0F, 806.0F, 132.0F, 806.0F, Settings.scale * 1.5F, Settings.scale * 1.5F, this.rotation, 0, 0, 132, 806, false, false);
    }


    public void update() {
        if (this.duration >= 1.7F) {
            this.y += Interpolation.linear.apply(0.0F, 806.0F, 0.03F);
            CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.HIGH, ScreenShake.ShakeDur.SHORT, false);
            this.isDone = false;
        } else if (this.duration >= 1.0F) {
            this.isDone = false;
        } else if (this.duration >= 0.0F) {
            this.y -= Interpolation.linear.apply(0.0F, 806.0F, 0.03F);
            this.isDone = false;
        } else {
            this.isDone = true;
        }
        this.duration -= Gdx.graphics.getDeltaTime();
    }

    public void dispose() {
    }
}