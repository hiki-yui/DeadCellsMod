package deadCellsMod.cn.infinite.stsmod.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import deadCellsMod.cn.infinite.stsmod.powers.BurnsPower;

public class GainBurnsPowerAction extends AbstractGameAction {
    AbstractCreature source;

    public GainBurnsPowerAction(AbstractCreature target,AbstractCreature source,int amount){
        this.target = target;
        this.amount = amount;
        this.source = source;
    }

    @Override
    public void update() {
        addToTop(new ApplyPowerAction(this.target,this.source,new BurnsPower(this.target,this.amount),this.amount));
        isDone = true;
    }
}
