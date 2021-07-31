package deadCellsMod.cn.infinite.stsmod.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import deadCellsMod.cn.infinite.stsmod.utils.ImgUtils;

public class IceShieldPower extends AbstractPower {

    public static final String BASE_ID = "deadCells:IceShieldPower";
    private static final PowerStrings STRINGS = CardCrawlGame.languagePack.getPowerStrings(BASE_ID);
    private  boolean IS_OWNER_TURN = true;

    public IceShieldPower(AbstractCreature owner, int amount) {
        this.ID = BASE_ID;
        this.name = STRINGS.NAME;
        this.owner = owner;
        this.amount = amount;
        this.type = PowerType.BUFF;
        this.isTurnBased = false;
        ImgUtils.setPowerImg(this);
        updateDescription();
    }

    @Override
    public void updateDescription() {
        this.description = STRINGS.DESCRIPTIONS[0] + amount + STRINGS.DESCRIPTIONS[1];
    }

    @Override
    public void atStartOfTurn() {
        IS_OWNER_TURN = true;
        addToBot(new ReducePowerAction(owner, owner, this, amount));
    }

    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {
        if (!IS_OWNER_TURN) {
            this.flash();
            /* if (!info.owner.isPlayer) {*/
            for (AbstractCreature monster : AbstractDungeon.getMonsters().monsters) {
                addToBot(new ApplyPowerAction(monster, this.owner, new StrengthPower(monster, -amount), -amount));
                if (monster != null && !monster.hasPower("Artifact")) {
                    addToBot(new ApplyPowerAction(monster, this.owner, new GainStrengthPower(monster, amount), amount));
                }
            }
        }
           /* } else {
                addToBot(new ApplyPowerAction(AbstractDungeon.player, this.owner, new StrengthPower(AbstractDungeon.player, -amount), -amount));
                addToBot(new ApplyPowerAction(AbstractDungeon.player, this.owner, new GainStrengthPower(AbstractDungeon.player, amount), amount));
            }*/

        return super.onAttacked(info, damageAmount);

    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        IS_OWNER_TURN = false;
        super.atEndOfTurn(isPlayer);
    }

    class GainStrengthPower extends com.megacrit.cardcrawl.powers.GainStrengthPower {
        private boolean justApply;

        GainStrengthPower(AbstractCreature owner, int amount) {
            super(owner, amount);
            this.justApply = true;
        }

        @Override
        public void atEndOfTurn(boolean isPlayer) {
            if (!justApply&&!isPlayer) {
                flash();
                addToBot(new ApplyPowerAction(this.owner, this.owner, new StrengthPower(this.owner, this.amount), this.amount));
                addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, "Shackled"));
            }else{
                justApply = false;
            }
        }
    }
}