package deadCellsMod.cn.infinite.stsmod.powers;

import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import deadCellsMod.cn.infinite.stsmod.utils.ImgUtils;

public class OilPower extends AbstractPower {

    public static final String BASE_ID = "deadCells:OilPower";
    public static final PowerStrings STRINGS = CardCrawlGame.languagePack.getPowerStrings(BASE_ID);

    public OilPower(AbstractCreature owner, int amount){
        this.ID = BASE_ID;
        this.name = STRINGS.NAME;
        this.amount = amount;
        this.owner = owner;
        this.type = PowerType.DEBUFF;
        this.isTurnBased = true;
        ImgUtils.setPowerImg(this);
        this.updateDescription();
    }

    @Override
    public void updateDescription() {
        this.description = STRINGS.DESCRIPTIONS[0] + this.amount +STRINGS.DESCRIPTIONS[1];
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        addToBot(new ReducePowerAction(this.owner,this.owner,this,1));
        super.atEndOfTurn(isPlayer);
    }
}
