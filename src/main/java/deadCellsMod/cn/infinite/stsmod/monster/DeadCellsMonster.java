package deadCellsMod.cn.infinite.stsmod.monster;

import basemod.abstracts.CustomMonster;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class DeadCellsMonster extends CustomMonster {
    public DeadCellsMonster(String name, String id, int maxHealth, float hb_x, float hb_y, float hb_w, float hb_h, String imgUrl, float offsetX, float offsetY) {
        super(name, id, maxHealth, hb_x, hb_y, hb_w, hb_h, imgUrl, offsetX, offsetY);
    }

    public DeadCellsMonster(String name, String id, int maxHealth, float hb_x, float hb_y, float hb_w, float hb_h, String imgUrl, float offsetX, float offsetY, boolean ignoreBlights) {
        super(name, id, maxHealth, hb_x, hb_y, hb_w, hb_h, imgUrl, offsetX, offsetY, ignoreBlights);
    }

    public DeadCellsMonster(String name, String id, int maxHealth, float hb_x, float hb_y, float hb_w, float hb_h, String imgUrl) {
        super(name, id, maxHealth, hb_x, hb_y, hb_w, hb_h, imgUrl);
    }

    @Override
    public void render(SpriteBatch sb) {
        super.render(sb);
    }

    @Override
    protected void renderDamageRange(SpriteBatch sb) {
        super.renderDamageRange(sb);
    }

    @Override
    protected void renderIntentVfxBehind(SpriteBatch sb) {
        super.renderIntentVfxBehind(sb);
    }

    @Override
    protected void renderIntent(SpriteBatch sb) {
        super.renderIntent(sb);
    }

    @Override
    protected void renderIntentVfxAfter(SpriteBatch sb) {
        super.renderIntentVfxAfter(sb);
    }

    @Override
    protected void renderName(SpriteBatch sb) {
        super.renderName(sb);
    }
}
