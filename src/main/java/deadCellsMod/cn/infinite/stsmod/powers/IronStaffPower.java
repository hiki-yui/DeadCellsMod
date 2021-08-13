package deadCellsMod.cn.infinite.stsmod.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import deadCellsMod.cn.infinite.stsmod.utils.CardUtils;

public class IronStaffPower extends DeadCellsPower {

    public static final String BASE_ID = "deadCells:IronStaffPower";
    private static final PowerStrings STRINGS = CardCrawlGame.languagePack.getPowerStrings(BASE_ID);
    private int damage;

    public IronStaffPower(AbstractCreature owner, int amount) {
        super(BASE_ID,STRINGS,owner,amount,PowerType.BUFF,true);
        this.damage = owner.currentBlock;
    }

    @Override
    public void atStartOfTurn() {
        System.out.println(this.owner.currentBlock);
        this.damage = this.owner.currentBlock;
        /*int thisAmount = this.amount;*/
        AbstractCreature thisOwner = this.owner;
        int[] i = CardUtils.forDamageAllEnemies(damage);
        for (int c = 0; c < this.amount; c++) {
            flash();
            addToBot(new DamageAllEnemiesAction(thisOwner, i, DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.BLUNT_HEAVY, true));
        }
        addToBot(new ReducePowerAction(thisOwner, thisOwner, "deadCells:IronStaffPower", this.amount));
    }


    /*@Override
    public void update(int slot) {
        super.update(slot);
        this.damage = owner.currentBlock;
    }*/

    /*@Override
    public int onAttackedToChangeDamage(DamageInfo info, int damageAmount) {
        return super.onAttackedToChangeDamage(info, damageAmount);
    }*/

    @Override
    public void updateDescription() {
        this.description = STRINGS.DESCRIPTIONS[0] + this.amount +STRINGS.DESCRIPTIONS[1];
    }
}
