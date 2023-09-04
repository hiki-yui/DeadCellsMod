package deadCellsMod.cn.infinite.stsmod.effects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.RefreshEnergyEffect;
import com.megacrit.cardcrawl.vfx.combat.BlurWaveAdditiveEffect;
import com.megacrit.cardcrawl.vfx.combat.BlurWaveChaoticEffect;
import com.megacrit.cardcrawl.vfx.combat.BlurWaveNormalEffect;
import com.megacrit.cardcrawl.vfx.combat.ShockWaveEffect;

public class GiantHowlEffect extends AbstractGameEffect {
    private AbstractMonster owner;
    private TextureAtlas.AtlasRegion img;
    private float x,y;

    public GiantHowlEffect(AbstractMonster owner) {
        this.owner = owner;
        this.color = Color.WHITE;
        this.img = ImageMaster.WHITE_RING;
        this.x = owner.hb.cX;
        this.y = owner.hb.cY-130.0F;
        this.duration = 1.5F;
    }

    public void update() {
        this.duration -= Gdx.graphics.getDeltaTime();
        this.scale *= 1.0F + Gdx.graphics.getDeltaTime() * 2.5F;
        this.color.a = Interpolation.fade.apply(0.0F, 0.5F, this.duration / 3.0F);
        if (this.color.a < 0.0F) {
            this.color.a = 0.0F;
        }

        if (this.duration < 0.0F) {
            this.isDone = true;
        }
    }

    public void render(SpriteBatch sb) {
        sb.setColor(this.color);
        sb.draw(this.img, this.x, this.y, (float)this.img.packedWidth / 2.0F, (float)this.img.packedHeight / 2.0F, (float)this.img.packedWidth, (float)this.img.packedHeight, this.scale * 1.5F, this.scale * 1.5F, this.rotation);

    }


    public void dispose() {
    }
}
