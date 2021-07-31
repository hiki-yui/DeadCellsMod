package deadCellsMod.cn.infinite.stsmod.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import deadCellsMod.cn.infinite.stsmod.utils.ImgUtils;

public class BleedingSpreadPower extends AbstractPower {

    public static final String BASE_ID = "deadCells:BleedingSpreadPower";
    private static final PowerStrings STRINGS = CardCrawlGame.languagePack.getPowerStrings(BASE_ID);

    public BleedingSpreadPower (AbstractCreature owner,int amount){
        this.name = STRINGS.NAME;
        this.ID = BASE_ID;
        this.type = PowerType.BUFF;
        this.amount = amount;
        this.owner = owner;
        this.isTurnBased = false;
        ImgUtils.setPowerImg(this);
        this.updateDescription();
    }

    @Override
    public void updateDescription() {
        this.description = STRINGS.DESCRIPTIONS[0]+amount+STRINGS.DESCRIPTIONS[1];
    }
}
