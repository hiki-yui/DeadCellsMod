package deadCellsMod.cn.infinite.stsmod.powers;

import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import deadCellsMod.cn.infinite.stsmod.utils.ImgUtils;

public class RampartPower extends AbstractPower {

    public static final String BASE_ID = "deadCells:RampartPower";
    private static final PowerStrings STRING = CardCrawlGame.languagePack.getPowerStrings(BASE_ID);

    public RampartPower(AbstractCreature owner,int amount){
        this.ID = BASE_ID;
        this.name = STRING.NAME;
        this.owner = owner;
        this.amount = amount;
        this.type = PowerType.BUFF;
        this.isTurnBased = false;
        ImgUtils.setPowerImg(this);
        updateDescription();
    }

    @Override
    public void updateDescription(){
        this.description = STRING.DESCRIPTIONS[0] + amount +STRING.DESCRIPTIONS[1];
    }

    @Override
    public int onAttackedToChangeDamage(DamageInfo info, int damageAmount) {
        return 0;
    }

    @Override
    public void atStartOfTurn() {
        addToBot(new ReducePowerAction(this.owner,this.owner,this,1));
        super.atStartOfTurn();
    }
}
