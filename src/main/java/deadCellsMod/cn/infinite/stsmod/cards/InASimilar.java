package deadCellsMod.cn.infinite.stsmod.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import deadCellsMod.cn.infinite.stsmod.action.UseTheSameCardAgainAction;
import deadCellsMod.cn.infinite.stsmod.enums.AbstractCardEnum;

import java.util.ArrayList;

public class InASimilar extends DeadCellsCard {

    public static final String BASE_ID = "deadCells:InASimilar";
    private static final CardStrings STRINGS = CardCrawlGame.languagePack.getCardStrings(BASE_ID);
    private static final String IMG = "img/card/InASimilar.png";

    public InASimilar(){
        super(BASE_ID,STRINGS.NAME,IMG,1,STRINGS.DESCRIPTION,CardType.SKILL, AbstractCardEnum.DEAD_CELLS,CardRarity.RARE,CardTarget.NONE);

    }

    @Override
    public void upgrade() {
        if (!this.upgraded){
            this.upgradeName();
            this.rawDescription = STRINGS.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        ArrayList<AbstractCard> cards = AbstractDungeon.actionManager.cardsPlayedThisCombat;
        //use方法在调用前会将该卡放进使用历史中,即cards
        if (cards.size()>=2) {
            //由于这张牌打出后会立即打出下一张牌,所以这张卡打出前的上一张卡绝不可能是同类型的卡
            AbstractCard card = cards.get(cards.size() - 2);
            if (this.upgraded) {
                if (cards.size() >= 3) {
                    int index = cards.size() - 3;
                    AbstractCard secondCard = cards.get(index);
                    while(secondCard instanceof InASimilar){
                        index--;
                        if (index<0) {
                            //以防万一
                            secondCard = card;
                        }else{
                            secondCard = cards.get(index);
                        }
                    }
                    addToBot(new UseTheSameCardAgainAction(secondCard, abstractPlayer, abstractMonster));
                }else {
                    addToBot(new UseTheSameCardAgainAction(card,abstractPlayer,abstractMonster));
                }
            }
            addToBot(new UseTheSameCardAgainAction(card, abstractPlayer, abstractMonster));
        }
    }

    @Override
    public void applyPowers() {
        changeDescription();
        super.applyPowers();
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        changeDescription();
        super.calculateCardDamage(mo);
    }

    @Override
    public void onMoveToDiscard() {
        this.rawDescription = this.upgraded?STRINGS.UPGRADE_DESCRIPTION:STRINGS.DESCRIPTION;
        super.onMoveToDiscard();
    }

    private void changeDescription(){
        ArrayList<AbstractCard> cards = AbstractDungeon.actionManager.cardsPlayedThisCombat;
        this.rawDescription = this.upgraded?STRINGS.UPGRADE_DESCRIPTION:STRINGS.DESCRIPTION;
        if (cards.size()>=1) {
            this.rawDescription += STRINGS.EXTENDED_DESCRIPTION[0] +
                    cards.get(cards.size() - 1).name;
            if (this.upgraded) {
                if (cards.size() >= 2) {
                    int index = cards.size() - 2;
                    AbstractCard secondCard = cards.get(index);
                    while (secondCard instanceof InASimilar) {
                        index--;
                        if (index < 0) {
                            //以防万一
                            secondCard = cards.get(cards.size() - 1);
                        } else {
                            secondCard = cards.get(index);
                        }
                    }
                    this.rawDescription += STRINGS.EXTENDED_DESCRIPTION[1] +
                            secondCard.name ;
                }
            }
            this.rawDescription += STRINGS.EXTENDED_DESCRIPTION[2];
        }
        this.initializeDescription();
    }
}
