package deadCellsMod.cn.infinite.stsmod.powers;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;

public class NightLightPower extends DeadCellsPower {

    private static final String BASE_ID = "deadCells:NightLightPower";
    private static final PowerStrings STRINGS = CardCrawlGame.languagePack.getPowerStrings(BASE_ID);

    public NightLightPower(int amount) {
        super(BASE_ID,STRINGS, AbstractDungeon.player,amount,PowerType.BUFF,false);
    }

    @Override
    public void updateDescription() {
        this.description = STRINGS.DESCRIPTIONS[0] + this.amount + STRINGS.DESCRIPTIONS[1];
    }
}
