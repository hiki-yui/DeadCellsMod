package deadCellsMod.cn.infinite.stsmod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import deadCellsMod.cn.infinite.stsmod.enums.AbstractCardEnum;

import java.util.ArrayList;

public class Backtrack extends DeadCellsCard {

    public static final String BASE_ID = "deadCells:Backtrack";
    private static final CardStrings STRINGS = CardCrawlGame.languagePack.getCardStrings(BASE_ID);
    private static final String IMG = "img/card/Backtrack.png";

    public Backtrack(){
        super(BASE_ID,STRINGS.NAME,IMG,0,STRINGS.DESCRIPTION,CardType.SKILL, AbstractCardEnum.DEAD_CELLS,CardRarity.UNCOMMON,CardTarget.SELF);

        this.exhaust = true;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded){
            this.rawDescription = STRINGS.UPGRADE_DESCRIPTION;
            this.initializeDescription();
            this.upgradeName();
        }
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        ArrayList<AbstractCard> list = new ArrayList<>();
        AbstractCard currentCard = this;

        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                ArrayList<AbstractCard> drawList;
                for (AbstractCard card:abstractPlayer.hand.group){
                    if (!currentCard.equals(card)){
                        list.add(card);
                    }
                }
                for (AbstractCard card:list){
                    card.triggerOnManualDiscard();
                    abstractPlayer.hand.moveToDeck(card,false);
                }

                if (list.size() > abstractPlayer.discardPile.group.size()){
                    drawList = new ArrayList<>(abstractPlayer.discardPile.group);
                }else{
                    drawList = new ArrayList<>();
                    for (int i = 0;i<list.size();i++) {
                        drawList.add(abstractPlayer.discardPile.group.get(abstractPlayer.discardPile.group.size()-1-i));
                    }
                }

                addToBot(new AbstractGameAction() {
                    @Override
                    public void update() {
                        for (AbstractCard card : drawList) {
                            System.out.println(card.hashCode()+"moveToHand!!!");
                            abstractPlayer.discardPile.moveToHand(card);
                        }
                        this.isDone = true;
                    }
                });
                isDone = true;
            }
        });

    }
}