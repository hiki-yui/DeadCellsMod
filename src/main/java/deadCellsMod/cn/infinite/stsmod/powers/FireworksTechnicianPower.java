package deadCellsMod.cn.infinite.stsmod.powers;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import deadCellsMod.cn.infinite.stsmod.cards.GrenadeCard;

public class FireworksTechnicianPower extends DeadCellsPower {

    public static final String BASE_ID = "deadCells:FireworksTechnicianPower";
    private static final PowerStrings STRINGS = CardCrawlGame.languagePack.getPowerStrings(BASE_ID);

    public FireworksTechnicianPower(AbstractCreature owner, int amount) {
        super(BASE_ID,STRINGS,owner,amount,PowerType.BUFF,false);
    }

    @Override
    public void updateDescription() {
        this.description = STRINGS.DESCRIPTIONS[0] + this.amount + STRINGS.DESCRIPTIONS[1];
    }

    @Override
    public float atDamageGive(float damage, DamageInfo.DamageType type, AbstractCard card) {
        if (card instanceof GrenadeCard){
            damage += this.amount;
        }
        return damage;
    }

    @Override
    public void onDamageAllEnemies(int[] damage) {
        super.onDamageAllEnemies(damage);
    }

    @Override
    public float atDamageFinalGive(float damage, DamageInfo.DamageType type, AbstractCard card) {

        return super.atDamageFinalGive(damage, type, card);
    }
}
