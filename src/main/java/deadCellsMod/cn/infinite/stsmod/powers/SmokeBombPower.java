package deadCellsMod.cn.infinite.stsmod.powers;


import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;

public class SmokeBombPower extends DeadCellsPower {

    public static final String BASE_ID = "deadCells:SmokeBombPower";
    private static final PowerStrings STRINGS = CardCrawlGame.languagePack.getPowerStrings(BASE_ID);
    private static final float DAMAGE_LOSE = 0.20F;

    public SmokeBombPower(AbstractCreature p, int amount) {
        super(BASE_ID,STRINGS,p,amount,PowerType.BUFF,true);
    }

    @Override
    public void updateDescription() {
        this.description = STRINGS.DESCRIPTIONS[0]+ (int)(DAMAGE_LOSE*100) +STRINGS.DESCRIPTIONS[1];
    }

    @Override
    public float atDamageFinalReceive(float damage, DamageInfo.DamageType type) {
        if (type == DamageInfo.DamageType.NORMAL){
            damage *= (1 - DAMAGE_LOSE);
        }
        return super.atDamageFinalReceive(damage, type);
    }

    /* @Override
    public int onAttackedToChangeDamage(DamageInfo info, int damageAmount) {


        return super.onAttackedToChangeDamage(info, damageAmount);
    }*/

    @Override
    public void atStartOfTurn() {
        addToBot(new ReducePowerAction(this.owner,this.owner,this,1));
        super.atStartOfTurn();
    }
}
