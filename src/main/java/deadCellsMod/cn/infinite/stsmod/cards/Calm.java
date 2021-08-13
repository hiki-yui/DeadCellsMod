package deadCellsMod.cn.infinite.stsmod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import deadCellsMod.cn.infinite.stsmod.enums.AbstractDeadCellsEnum;
import deadCellsMod.cn.infinite.stsmod.utils.CardUtils;

import java.util.ArrayList;

public class Calm extends DeadCellsCard {

    public static final String BASE_ID = "deadCells:Calm";
    private static final CardStrings STRINGS = CardCrawlGame.languagePack.getCardStrings(BASE_ID);

    public Calm(){
        super(BASE_ID,STRINGS.NAME,"img/card/Calm.png",0,STRINGS.DESCRIPTION,CardType.SKILL, AbstractDeadCellsEnum.DEAD_CELLS,CardRarity.COMMON,CardTarget.SELF);

        this.magicNumber = this.baseMagicNumber = 2;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded){
            this.upgradeMagicNumber(-1);
            this.upgradeName();
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainEnergyAction(2));
        ArrayList<AbstractCard> cards = new ArrayList<>();
        for (AbstractCard card:p.hand.group){
            if (card.type == CardType.ATTACK){
                cards.add(card);
            }
        }
        int disNum = this.magicNumber;

        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                if (cards.size()>=disNum){
                    for (int i = 0;i<disNum;i++){
                        int rnd = CardUtils.getRandomItemFromList(cards);
                        p.hand.moveToDiscardPile(cards.get(rnd));
                        cards.get(rnd).triggerOnManualDiscard();
                        cards.remove(rnd);
                    }
                }else{
                    for (AbstractCard card : cards) {
                        card.triggerOnManualDiscard();
                        p.hand.moveToDiscardPile(card);
                    }
                }
                isDone = true;
            }
        });

    }
}
