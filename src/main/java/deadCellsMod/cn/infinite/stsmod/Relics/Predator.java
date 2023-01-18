package deadCellsMod.cn.infinite.stsmod.Relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import deadCellsMod.cn.infinite.stsmod.powers.SmokeBombPower;

public class Predator extends DeadCellsRelic {

    public static final String BASE_ID = "deadCells:Predator";
    private static final RelicStrings STRINGS = CardCrawlGame.languagePack.getRelicStrings(BASE_ID);
    private static final int POWER_AMOUNT = 2;

    public Predator(){
        super(BASE_ID,new Texture("img/relics/Predator.png"),RelicTier.UNCOMMON,LandingSound.MAGICAL);
    }

    @Override
    public void onMonsterDeath(AbstractMonster m) {
        this.flash();
        addToBot(new ApplyPowerAction(AbstractDungeon.player,null,new SmokeBombPower(AbstractDungeon.player,POWER_AMOUNT),POWER_AMOUNT));
        super.onMonsterDeath(m);
    }

    @Override
    public String getUpdatedDescription() {
        return STRINGS.DESCRIPTIONS[0] + POWER_AMOUNT + STRINGS.DESCRIPTIONS[1];
    }
}
