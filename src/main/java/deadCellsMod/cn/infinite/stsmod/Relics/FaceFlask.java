package deadCellsMod.cn.infinite.stsmod.Relics;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class FaceFlask extends DeadCellsRelic{

    public static final String BASE_ID = "deadCells:FaceFlask";
    private static final RelicStrings STRINGS = CardCrawlGame.languagePack.getRelicStrings(BASE_ID);
    private static final String IMG = "img/relics/FaceFlask.png";

    public FaceFlask() {
        super(BASE_ID, IMG, RelicTier.UNCOMMON,LandingSound.FLAT);
    }

    @Override
    public String getUpdatedDescription() {
        return STRINGS.DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new FaceFlask();
    }
}
