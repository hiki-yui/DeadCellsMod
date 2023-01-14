package deadCellsMod.cn.infinite.stsmod.powers;

import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import deadCellsMod.cn.infinite.stsmod.utils.ImgUtils;

public class FrostbitePower extends AbstractPower {

    public static final String BASE_ID = "deadCells:FrostbitePower";
    private static final PowerStrings STRINGS = CardCrawlGame.languagePack .getPowerStrings(BASE_ID);
    private AbstractCreature source;
    private static float damageLose = 0.10F;
    private static int powerLose = 2;
    private boolean justApply = true;

    public FrostbitePower(AbstractCreature owner,AbstractCreature source, int amount){
        this.ID = BASE_ID;
        this.name = STRINGS.NAME;
        this.amount = amount;
        this.owner = owner;
        this.source = source;
        this.type = PowerType.DEBUFF;
        this.isTurnBased = true;
        this.updateDescription();
        ImgUtils.setPowerImg(this);
    }

    @Override
    public float atDamageGive(float damage, DamageInfo.DamageType type) {
        damage = damage * (1-damageLose) - this.amount;
        return super.atDamageGive(damage, type);
    }

    @Override
    public void updateDescription() {
        this.description = STRINGS.DESCRIPTIONS[0] +10+//(damageLose*100)
                STRINGS.DESCRIPTIONS[1] + amount +
                STRINGS.DESCRIPTIONS[2];
    }


    @Override
    public void atEndOfTurn(boolean isPlayer) {
        /*if (!this.justApply) {*/
        addToBot(new ReducePowerAction(this.owner, this.owner, this, powerLose));
        /*}else{
            this.justApply = false;
        }*/
        super.atEndOfTurn(isPlayer);
    }
}
