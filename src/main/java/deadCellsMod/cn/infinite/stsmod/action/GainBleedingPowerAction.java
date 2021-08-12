package deadCellsMod.cn.infinite.stsmod.action;

import basemod.devcommands.power.Power;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.PoisonPower;
import deadCellsMod.cn.infinite.stsmod.powers.BleedingPower;

public class GainBleedingPowerAction extends AbstractGameAction {

    public GainBleedingPowerAction(AbstractCreature source,AbstractCreature target,int amount){
        this.target = target;
        this.amount = amount;
        this.source = source;
    }

    @Override
    public void update() {
        AbstractPower bleedingSpread = null;
        AbstractPower corrosiveCloudPower = null;
        if (source != null) {
            bleedingSpread = source.getPower("deadCells:BleedingSpreadPower");
        }
        if (source !=null ){
            corrosiveCloudPower = source.getPower("deadCells:CorrosiveCloudPower");
        }
        if (bleedingSpread!=null){
            bleedingSpread.flash();
            for (AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
                if (!monster.isDying) {
                    addToBot(new ApplyPowerAction(monster, this.source, new BleedingPower(monster, (amount+bleedingSpread.amount)), (amount+bleedingSpread.amount), true));
                }
                givePoison(corrosiveCloudPower,bleedingSpread,monster);
            }
        }else{
            addToBot(new ApplyPowerAction(this.target,this.source,new BleedingPower(this.target,amount),amount,true));
            givePoison(corrosiveCloudPower,null,this.target);
        }
        isDone = true;
    }

    public void givePoison(AbstractPower corrosiveCloudPower,AbstractPower bleedingSpread,AbstractCreature target){
        if (corrosiveCloudPower!=null){
            int poisonAmount;
            if (bleedingSpread!=null) {
                poisonAmount = (this.amount + bleedingSpread.amount) / 3;
            }else{
                poisonAmount = this.amount/3;
            }
            if (poisonAmount > 0) {
                corrosiveCloudPower.flash();
                for (int i = 0; i < poisonAmount; i++) {
                    addToBot(new ApplyPowerAction(target, this.source, new PoisonPower(target, source, corrosiveCloudPower.amount), corrosiveCloudPower.amount));
                }
            }
        }
    }
}
