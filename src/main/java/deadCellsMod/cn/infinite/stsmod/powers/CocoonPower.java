package deadCellsMod.cn.infinite.stsmod.powers;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static com.megacrit.cardcrawl.powers.AbstractPower.PowerType.BUFF;

public class CocoonPower extends DeadCellsPower {

    public static final String BASE_ID = "deadCells:CocoonPower";
    private static final PowerStrings STRINGS = CardCrawlGame.languagePack.getPowerStrings(BASE_ID);
    private boolean canGive = true;

    public CocoonPower(AbstractCreature owner, int amount) {
        super(BASE_ID,STRINGS,owner,amount,BUFF,false);
    }

    @Override
    public void onGainedBlock(float blockAmount) {
        if (canGive) {
            this.flash();
            addToBot(new GainBlockAction(this.owner, this.amount, true));
            this.canGive = false;
        }else{
            this.canGive = true;
        }

        super.onGainedBlock(blockAmount);
    }

    @Override
    public void updateDescription() {
        this.description = STRINGS.DESCRIPTIONS[0] + this.amount + STRINGS.DESCRIPTIONS[1];
    }
}
