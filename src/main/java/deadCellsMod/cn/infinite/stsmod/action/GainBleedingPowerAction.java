package deadCellsMod.cn.infinite.stsmod.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import deadCellsMod.cn.infinite.stsmod.powers.BleedingPower;

public class GainBleedingPowerAction extends AbstractGameAction {

    public GainBleedingPowerAction(AbstractCreature source,AbstractCreature target,int amount){
        this.target = target;
        this.amount = amount;
        this.source = source;
    }

    @Override
    public void update() {
        AbstractPower bleedingSpread = source.getPower("deadCells:BleedingSpreadPower");
        if (bleedingSpread!=null){
            for (AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
                if (!monster.isDead) {
                    addToBot(new ApplyPowerAction(monster, this.source, new BleedingPower(monster, (amount+bleedingSpread.amount)), (amount+bleedingSpread.amount), true));
                }
            }
        }else{
            addToBot(new ApplyPowerAction(this.target,this.source,new BleedingPower(this.target,amount),amount,true));
        }
        isDone = true;
    }
}
