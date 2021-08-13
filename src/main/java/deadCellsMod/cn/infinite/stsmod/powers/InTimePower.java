package deadCellsMod.cn.infinite.stsmod.powers;

import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import deadCellsMod.cn.infinite.stsmod.DeadCellsModInitializer;

public class InTimePower extends DeadCellsPower {

    public static final String BASE_ID = "deadCells:InTimePower";
    private static final PowerStrings STRINGS = CardCrawlGame.languagePack.getPowerStrings(BASE_ID);

    public InTimePower(AbstractCreature owner, int amount) {
        super(BASE_ID,STRINGS,owner,amount,PowerType.BUFF,true);
    }

    @Override
    public void atStartOfTurnPostDraw() {
        boolean canGive = false;
        for (AbstractMonster m : AbstractDungeon.getMonsters().monsters){
            if (m.intent == AbstractMonster.Intent.ATTACK || m.intent == AbstractMonster.Intent.ATTACK_DEBUFF ||
                    m.intent == AbstractMonster.Intent.ATTACK_BUFF || m.intent == AbstractMonster.Intent.ATTACK_DEFEND){
                canGive = true;
            }
        }
        if (canGive) {
            for (int i = 0; i < this.amount; i++) {
                AbstractCard card = DeadCellsModInitializer.SHIELD_POOL.
                        get(AbstractDungeon.cardRng.random(DeadCellsModInitializer.SHIELD_POOL.size() - 1)).makeCopy();
                card.exhaust = true;
                addToBot(new NewQueueCardAction(card, true, true, true));
            }
        }

        super.atStartOfTurn();
    }

    @Override
    public void updateDescription() {
        this.description = STRINGS.DESCRIPTIONS[0] + this.amount + STRINGS.DESCRIPTIONS[1];
    }
}
