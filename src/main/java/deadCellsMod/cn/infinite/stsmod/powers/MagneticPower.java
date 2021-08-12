package deadCellsMod.cn.infinite.stsmod.powers;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;

//磁场
public class MagneticPower extends DeadCellsPower {
    public static final String BASE_ID = "deadCells:MagneticPower";
    private static final PowerStrings STRINGS = CardCrawlGame.languagePack.getPowerStrings(BASE_ID);
    private AbstractCreature source;

    public MagneticPower(AbstractCreature owner,AbstractCreature source,int amount ){
        super(BASE_ID,STRINGS,owner,amount,PowerType.DEBUFF,false);
        this.source = source;
    }

    @Override
    public void wasHPLost(DamageInfo info, int damageAmount) {
        if (info.type == DamageInfo.DamageType.NORMAL && info.owner instanceof AbstractPlayer) {
            this.flash();
            addToBot(new GainBlockAction(source, this.amount, true));
        }
        super.wasHPLost(info, damageAmount);
    }


    @Override
    public void atStartOfTurn() {
        addToBot(new ReducePowerAction(this.owner,this.owner,this,this.amount));
        super.atStartOfTurn();
    }

    @Override
    public void updateDescription() {
        this.description = STRINGS.DESCRIPTIONS[0] + amount + STRINGS.DESCRIPTIONS[1];
    }
}
