package deadCellsMod.cn.infinite.stsmod.powers;

import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import deadCellsMod.cn.infinite.stsmod.action.GainBleedingPowerAction;
import deadCellsMod.cn.infinite.stsmod.utils.ImgUtils;

public class BloodthirstyShieldPower extends AbstractPower {

    public static final String BASE_ID = "deadCells:BloodthirstyShieldPower";
    private static final PowerStrings STRINGS = CardCrawlGame.languagePack.getPowerStrings(BASE_ID);
    private boolean isOwnerTurn = true;

    public BloodthirstyShieldPower(AbstractCreature owner,int amount){
        this.name = STRINGS.NAME;
        this.ID = BASE_ID;
        this.owner = owner;
        this.amount = amount;
        this.type = PowerType.BUFF;
        this.isTurnBased = false;
        ImgUtils.setPowerImg(this);
        this.updateDescription();
    }

    @Override
    public void updateDescription() {
        this.description = STRINGS.DESCRIPTIONS[0]+this.amount+STRINGS.DESCRIPTIONS[1];
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        this.isOwnerTurn = false;
        super.atEndOfTurn(isPlayer);
    }

    @Override
    public void atStartOfTurn() {
        this.isOwnerTurn = true;
        addToBot(new ReducePowerAction(this.owner,this.owner,this,this.amount));
        super.atStartOfTurn();
    }

    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {
        if (info.type == DamageInfo.DamageType.NORMAL&&!this.isOwnerTurn&&this.owner.currentBlock>=0) {
            this.flash();
            for (AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
                addToBot(new GainBleedingPowerAction(this.owner, monster, this.amount));
            }
        }
        return super.onAttacked(info, damageAmount);
    }
}
