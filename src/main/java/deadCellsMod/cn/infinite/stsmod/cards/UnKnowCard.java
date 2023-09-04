package deadCellsMod.cn.infinite.stsmod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.TransformCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import deadCellsMod.cn.infinite.stsmod.DeadCellsModInitializer;
import deadCellsMod.cn.infinite.stsmod.action.AddCardToHandAction;
import deadCellsMod.cn.infinite.stsmod.enums.AbstractDeadCellsEnum;
import deadCellsMod.cn.infinite.stsmod.utils.ImgUtils;

import java.util.ArrayList;


public class UnKnowCard extends DeadCellsCard {

    private static final String BASE_ID = "UnKnowCard";
    private static final CardStrings STRINGS = CardCrawlGame.languagePack.getCardStrings(BASE_ID);
    private static final String IMG = "img/card/defend.png";

    public UnKnowCard (){
        super(BASE_ID,STRINGS.NAME,IMG,0,STRINGS.DESCRIPTION,CardType.SKILL, AbstractDeadCellsEnum.DEAD_CELLS,CardRarity.SPECIAL,CardTarget.SELF);
        this.setBackgroundTexture(DeadCellsModInitializer.GRAY_ATTACK_CARD, DeadCellsModInitializer.GRAY_ATTACK_CARD_PORTRAIT);
        this.magicNumber = this.baseMagicNumber = 1;
    }

    @Override
    public void upgrade() {

    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        AbstractCard willBeMarkCard = abstractPlayer.hand.group.get(0);
        if (!abstractPlayer.hasPower("UnKnowPower")) {
            addToBot(new ApplyPowerAction(abstractPlayer, abstractPlayer, new UnKnowPower(abstractPlayer, willBeMarkCard)));
        }
        addToBot(new UnKnowAction(abstractPlayer,willBeMarkCard));

    }
    private static class UnKnowPower extends AbstractPower {

        private static final String BASE_ID = "UnKnowPower";
        private static final PowerStrings STRINGS = CardCrawlGame.languagePack.getPowerStrings(BASE_ID);
        private static final ArrayList<AbstractCard> cards = new ArrayList<>();
        public UnKnowPower(AbstractCreature owner,AbstractCard card){
            this.ID= BASE_ID;
            this.name = STRINGS.NAME;
            this.owner = owner;
            this.type = PowerType.BUFF;
            this.isTurnBased = false;
            ImgUtils.setPowerImg(this);
            this.updateDescription();
        }

        public void setCards(AbstractCard card){
            if(card!=null){
                card.flash();
                if (!card.isEthereal){
                    card.rawDescription += " NL 虚无 。";
                }
                card.isEthereal = true;
                card.rawDescription += " NL *标记 。";

                card.initializeDescription();
                cards.add(card);
            }
        }

        @Override
        public void updateDescription() {
            this.description = STRINGS.DESCRIPTIONS[0];
        }

        public boolean contains(AbstractCard card){
            return cards.contains(card);
        }

        public AbstractCard replaceCardIfContains(ArrayList<AbstractCard> hand,AbstractCard card){
            while(contains(card)){
                if (hand.size()-1 >= hand.indexOf(card)+1 ) {
                    card = hand.get(hand.indexOf(card) + 1);
                }else{
                    card = null;
                }
            }
            return card;
        }

        @Override
        public void onUseCard(AbstractCard card, UseCardAction action) {
            if (contains(card)){
                addToBot(new DrawCardAction(1));
            }
            super.onUseCard(card, action);
        }

        @Override
        public void onExhaust(AbstractCard card) {
            if (cards.contains(card)) {
                addToBot(new MakeTempCardInHandAction(card.makeSameInstanceOf(), true, true));
            }
            super.onExhaust(card);
        }
    }

    static class UnKnowAction extends AbstractGameAction{

        private AbstractCard willBeMarkCard;
        private AbstractPlayer player;
        public UnKnowAction(AbstractPlayer player,AbstractCard willBeMarkCard){
            this.willBeMarkCard = willBeMarkCard;
            this.player = player;
        }

        @Override
        public void update() {
            AbstractPower power = player.getPower("UnKnowPower");
            willBeMarkCard = ((UnKnowPower) power).replaceCardIfContains(player.hand.group, willBeMarkCard);
            /*if (!((UnKnowPower) power).contains(willBeMarkCard)) {*/
            ((UnKnowPower) power).setCards(willBeMarkCard);
            isDone = true;
            /*}*/
        }
    }
}
