package deadCellsMod.cn.infinite.stsmod.Relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class OpenWounds extends CustomRelic {

    public static final String BASE_ID = "deadCells:OpenWounds";
    private static final RelicStrings STRINGS = CardCrawlGame.languagePack.getRelicStrings(BASE_ID);
    private static final String IMG = "img/relics/OpenWounds.png";
    private static final int amount = 1;
    public OpenWounds(){
        super(BASE_ID,new Texture(IMG),RelicTier.COMMON,LandingSound.MAGICAL);
    }

    @Override
    public String getUpdatedDescription() {
        return STRINGS.DESCRIPTIONS[0] + amount +STRINGS.DESCRIPTIONS[1];
    }


    @Override
    public AbstractRelic makeCopy() {
        return new OpenWounds();
    }

    public int getAmount(){
        return amount;
    }
}
