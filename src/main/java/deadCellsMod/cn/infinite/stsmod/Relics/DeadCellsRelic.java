package deadCellsMod.cn.infinite.stsmod.Relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public abstract class DeadCellsRelic extends CustomRelic {


    public DeadCellsRelic(String id, Texture texture, RelicTier tier, LandingSound sfx) {
        super(id, texture, tier, sfx);
    }

    public DeadCellsRelic(String id, Texture texture, Texture outline, RelicTier tier, LandingSound sfx) {
        super(id, texture, outline, tier, sfx);
    }

    public DeadCellsRelic(String id, String imgName, RelicTier tier, LandingSound sfx) {
        super(id, imgName, tier, sfx);
    }

    @Override
    public void setTexture(Texture t) {
        super.setTexture(t);
    }

    @Override
    public void setTextureOutline(Texture t, Texture o) {
        super.setTextureOutline(t, o);
    }

    @Override
    public AbstractRelic makeCopy() {
        return super.makeCopy();
    }
}
