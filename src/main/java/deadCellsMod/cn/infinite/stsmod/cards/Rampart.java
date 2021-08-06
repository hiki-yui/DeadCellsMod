package deadCellsMod.cn.infinite.stsmod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DiscardSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import deadCellsMod.cn.infinite.stsmod.enums.AbstractCardEnum;
import deadCellsMod.cn.infinite.stsmod.powers.RampartPower;

//壁垒
public class Rampart extends DeadCellsCard{

    public static final String BASE_ID = "deadCells:Rampart";
    private static final CardStrings STRINGS = CardCrawlGame.languagePack.getCardStrings(BASE_ID);
    private static final String IMG = "img/card/Rampart.png";

    public Rampart(){
        super(BASE_ID,STRINGS.NAME,IMG,3,STRINGS.DESCRIPTION,CardType.SKILL, AbstractCardEnum.DEAD_CELLS,CardRarity.RARE,CardTarget.SELF);
        this.exhaust = true;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeBaseCost(2);
            this.upgradeName();
        }
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                for (AbstractCard card : abstractPlayer.hand.group){
                    if (card.type==CardType.SKILL){
                        addToBot(new DiscardSpecificCardAction(card));
                        /*card.moveToDiscardPile();*/
                        card.onMoveToDiscard();
                    }
                }
                isDone = true;
            }
        });

        addToBot(new ApplyPowerAction(abstractPlayer,abstractPlayer,new RampartPower(abstractPlayer,1),1));
    }
}
