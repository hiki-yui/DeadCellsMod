package deadCellsMod.cn.infinite.stsmod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import deadCellsMod.cn.infinite.stsmod.DeadCellsModInitializer;
import deadCellsMod.cn.infinite.stsmod.enums.AbstractDeadCellsEnum;
import deadCellsMod.cn.infinite.stsmod.utils.CardUtils;

import java.util.ArrayList;

public class APlan extends DeadCellsCard {

    public static final String BASE_ID = "deadCells:APlan";
    private static final CardStrings STRINGS = CardCrawlGame.languagePack.getCardStrings(BASE_ID);

    public APlan(){
        super(BASE_ID,STRINGS.NAME,"img/card/APlan.png",0,STRINGS.DESCRIPTION,
                CardType.SKILL, AbstractDeadCellsEnum.DEAD_CELLS,CardRarity.UNCOMMON,CardTarget.SELF);

        this.setBackgroundTexture(DeadCellsModInitializer.GOLD_SKILL_CARD, DeadCellsModInitializer.GOLD_SKILL_CARD_PORTRAIT);
        this.magicNumber =  this.baseMagicNumber = 1;
        this.exhaust = true;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded){
            this.exhaust = false;
            this.rawDescription = STRINGS.UPGRADE_DESCRIPTION;
            this.initializeDescription();
            this.upgradeName();
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        ArrayList<AbstractCard> cards = p.drawPile.group;
        ArrayList<AbstractCard> attacks = new ArrayList<>();
        ArrayList<AbstractCard> willMoveCards = new ArrayList<>();

        for (AbstractCard card : cards){
            if (card.type == CardType.ATTACK){
                attacks.add(card);
            }
        }

        int skillCardNum = 0;
        for (AbstractCard card : p.hand.group){
            if (card.type == CardType.SKILL && card != this){
                willMoveCards.add(card);
                skillCardNum++;
            }
        }

        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                for (AbstractCard card:willMoveCards){
                    p.hand.moveToDiscardPile(card);
                    /*addToBot(new DiscardSpecificCardAction(card));*/
                }
                isDone = true;
            }
        });

        if (skillCardNum<=attacks.size()) {
            for (int i = 0; i < skillCardNum; i++) {
                addToBot(new AbstractGameAction() {
                    @Override
                    public void update() {
                        int rnd = CardUtils.getRandomItemFromList(attacks);
                        AbstractCard willMoveCard = attacks.get(rnd);
                        p.drawPile.moveToHand(willMoveCard);
                        attacks.remove(rnd);

                        this.isDone = true;
                    }
                });
            }
        }else if (attacks.size()>0){
            for (AbstractCard card:attacks){
                addToBot(new AbstractGameAction() {
                    @Override
                    public void update() {
                        p.drawPile.moveToHand(card);
                        this.isDone = true;
                    }
                });
            }
        }
        addToBot(new GainEnergyAction(this.baseMagicNumber));
    }


}
