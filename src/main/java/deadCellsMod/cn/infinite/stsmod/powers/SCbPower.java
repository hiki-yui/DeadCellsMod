package deadCellsMod.cn.infinite.stsmod.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DiscardSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import deadCellsMod.cn.infinite.stsmod.action.UseTheSameCardAgainAction;


public class SCbPower extends DeadCellsPower {

    public static final String BASE_ID = "deadCells:SCbPower";
    private static final PowerStrings STRINGS = CardCrawlGame.languagePack.getPowerStrings(BASE_ID);

    public SCbPower(AbstractPlayer player,int amount ) {
        super(BASE_ID, STRINGS, player, amount,PowerType.BUFF, true);
    }

    @Override
    public void updateDescription() {
        this.description = STRINGS.DESCRIPTIONS[0]+amount+STRINGS.DESCRIPTIONS[1];
    }




    @Override
    public void onCardDraw(AbstractCard card) {
        if (card.type == AbstractCard.CardType.ATTACK && this.amount > 0){
            this.amount --;
            AbstractMonster m = AbstractDungeon.getRandomMonster();
            addToBot(new AbstractGameAction() {
                @Override
                public void update() {
                    if (AbstractDungeon.player.discardPile.contains(card)){
                        AbstractDungeon.player.discardPile.moveToHand(card);
                    }
                    isDone = true;
                }
            });
            addToBot(new NewQueueCardAction(card,m,true,true));
        }

        if (amount == 0){
            addToBot(new RemoveSpecificPowerAction(this.owner,this.owner,this));
        }
    }
}
