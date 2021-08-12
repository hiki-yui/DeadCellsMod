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

public class IronStaffPower extends DeadCellsPower {

    public static final String BASE_ID = "deadCells:IronStaffPower";
    private static final PowerStrings STRINGS = CardCrawlGame.languagePack.getPowerStrings(BASE_ID);

    public IronStaffPower(AbstractCreature owner, int amount) {
        super(BASE_ID,STRINGS,owner,amount,PowerType.BUFF,true);
    }

    @Override
    public void atStartOfTurn() {
        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                int num = 0;
                for (AbstractMonster monster: AbstractDungeon.getMonsters().monsters){
                    if (!monster.isDying){
                        num++;
                    }
                }
                int[] i = new int[num];
                for (int n = 0;n<i.length;n++){
                    i[n] = owner.currentBlock;
                }
                for (int c = 0;c<this.amount;c++) {
                    flash();
                    addToBot(new DamageAllEnemiesAction(owner, i, DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.BLUNT_HEAVY, true));
                }
                isDone = true;
            }
        });

        addToBot(new ReducePowerAction(this.owner,this.owner,this,this.amount));
    }

    @Override
    public void updateDescription() {
        this.description = STRINGS.DESCRIPTIONS[0] + this.amount +STRINGS.DESCRIPTIONS[1];
    }
}
