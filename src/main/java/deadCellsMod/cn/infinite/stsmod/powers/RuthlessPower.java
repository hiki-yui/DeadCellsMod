package deadCellsMod.cn.infinite.stsmod.powers;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class RuthlessPower extends DeadCellsPower {
    public static final String BASE_ID = "deadCells:RuthlessPower";
    private static final PowerStrings STRINGS = CardCrawlGame.languagePack.getPowerStrings(BASE_ID);
    private int counter = 0;
    private int needDebuff = 2;

    public RuthlessPower(AbstractCreature owner,int amount){
        super(BASE_ID,STRINGS,owner,amount,PowerType.BUFF,false);
    }

    @Override
    public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        if (target instanceof AbstractMonster && power.type == PowerType.DEBUFF){
            this.counter++;
            if (counter%needDebuff == 0) {
                addToBot(new DrawCardAction(this.amount));
            }
        }
        super.onApplyPower(power, target, source);
    }

    @Override
    public void updateDescription() {
        this.description = STRINGS.DESCRIPTIONS[0] + 2 +STRINGS.DESCRIPTIONS[1] +this.amount + STRINGS.DESCRIPTIONS[2];
    }
}
