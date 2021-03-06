package deadCellsMod.cn.infinite.stsmod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import deadCellsMod.cn.infinite.stsmod.action.UseTheSameCardAgainAction;
import deadCellsMod.cn.infinite.stsmod.enums.DeadCellsTags;

import java.util.ArrayList;

public abstract class GrenadeCard extends DeadCellsCard {

    private CardStrings STRINGS = CardCrawlGame.languagePack.getCardStrings("deadCells:GrenadeCard");

    public GrenadeCard(String id, String name, String img, int cost, String rawDescription, CardType type, CardColor color, CardRarity rarity, CardTarget target) {
        super(id, name, img, cost, rawDescription, type, color, rarity, target);
        this.tags.add(DeadCellsTags.GRENADE);
        this.rawDescription += STRINGS.DESCRIPTION;
        this.initializeDescription();
    }

    public GrenadeCard(String id, String name, RegionName img, int cost, String rawDescription, CardType type, CardColor color, CardRarity rarity, CardTarget target) {
        super(id, name, img, cost, rawDescription, type, color, rarity, target);
        this.tags.add(DeadCellsTags.GRENADE);
        this.rawDescription +=  STRINGS.DESCRIPTION;
        this.initializeDescription();
    }

    @Override
    public void upgrade() {

    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {

    }

    @Override
    public AbstractCard makeCopy() {
        return super.makeCopy();
    }

    @Override
    public void triggerOnManualDiscard() {
        /*AbstractDungeon.actionManager.addCardQueueItem(new CardQueueItem(this,
                true,AbstractDungeon.player.energy.energy,true,true));*/
        AbstractCard thisCard = this;
        addToBot(new UseTheSameCardAgainAction(this, AbstractDungeon.player, null));

        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                ArrayList<AbstractCard> list = new ArrayList<>(AbstractDungeon.player.discardPile.group);
                for (AbstractCard card : list) {
                    if (thisCard.equals(card)) {
                        if (thisCard.exhaust) {
                            System.out.println("removeCardFromDisCardPile");
                            /*AbstractDungeon.player.discardPile.group.remove(thisCard);*/
                            AbstractDungeon.player.discardPile.moveToExhaustPile(thisCard);
                        }
                        isDone = true;
                        return;
                    }
                }

                list.clear();
                list.addAll(AbstractDungeon.player.hand.group);

                for (AbstractCard card : list) {
                    if (thisCard.equals(card)) {
                        System.out.println("removeCardFromHand");
                        /*AbstractDungeon.player.hand.group.remove(thisCard);*/
                        if (thisCard.exhaust) {
                            AbstractDungeon.player.hand.moveToExhaustPile(thisCard);
                        }else{
                            AbstractDungeon.player.hand.moveToDiscardPile(thisCard);
                        }
                        isDone = true;
                        return;
                    }
                }

                list.clear();
                list.addAll(AbstractDungeon.player.drawPile.group);

                for (AbstractCard card : list ) {
                    if (thisCard.equals(card)) {
                        System.out.println("removeCardFormDrawPile");
                        /*AbstractDungeon.player.drawPile.group.remove(thisCard);*/
                        if (thisCard.exhaust) {
                            AbstractDungeon.player.drawPile.moveToExhaustPile(thisCard);
                        }else{
                            AbstractDungeon.player.drawPile.moveToDiscardPile(thisCard);
                        }
                        isDone = true;
                        return;
                    }
                }
                isDone = true;
            }
        });
    }
}
