package deadCellsMod.cn.infinite.stsmod.Relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;

//被封印的秘籍
public class SealsRecipe extends CustomRelic {

    public static final String BASE_ID = "deadCells:SealsRecipe";
    private static final RelicStrings STRINGS = CardCrawlGame.languagePack.getRelicStrings(BASE_ID);
    private static final String IMG = "img/relics/SealsRecipe.png";

    public SealsRecipe(){
        super(BASE_ID,new Texture(IMG),RelicTier.UNCOMMON,LandingSound.FLAT);
    }


    @Override
    public String getUpdatedDescription() {
        return STRINGS.DESCRIPTIONS[0]+1+STRINGS.DESCRIPTIONS[1] + 1 +STRINGS.DESCRIPTIONS[2];
    }

    @Override
    public void atPreBattle() {
        addToBot(new ApplyPowerAction(AbstractDungeon.player,null,new StrengthPower(AbstractDungeon.player,1),1));
        addToBot(new ApplyPowerAction(AbstractDungeon.player,null,new DexterityPower(AbstractDungeon.player,1),1));
        super.atPreBattle();
    }

    @Override
    public AbstractRelic makeCopy() {
        return new SealsRecipe();
    }
}
