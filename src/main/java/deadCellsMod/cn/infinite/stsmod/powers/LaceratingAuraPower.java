package deadCellsMod.cn.infinite.stsmod.powers;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import deadCellsMod.cn.infinite.stsmod.action.GainBleedingPowerAction;

public class LaceratingAuraPower extends DeadCellsPower {

    public static final String BASE_ID = "deadCells:LaceratingAuraPower";
    private static final PowerStrings STRINGS = CardCrawlGame.languagePack.getPowerStrings(BASE_ID);

    public LaceratingAuraPower(AbstractPlayer owner, int amount) {
        super(BASE_ID,STRINGS,owner,amount,PowerType.BUFF,false);
    }

    @Override
    public void updateDescription() {
        this.description = STRINGS.DESCRIPTIONS[0] + amount + STRINGS.DESCRIPTIONS[1];
    }

    @Override
    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
        if (damageAmount > 0 && target != this.owner && info.type == DamageInfo.DamageType.NORMAL) {
            flash();
            addToTop(new GainBleedingPowerAction(this.owner,target,this.amount));
        }
    }
}
