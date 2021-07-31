package deadCellsMod.cn.infinite.stsmod.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.BufferPower;
import com.megacrit.cardcrawl.powers.WeakPower;
import deadCellsMod.cn.infinite.stsmod.enums.AbstractCardEnum;


public class EmergencyDoor extends DeadCellsCard {

    public static final String ID = "deadCells:EmergencyDoor";
    private static final CardStrings strings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = strings.NAME;
    private static final String DESCRIPTION = strings.DESCRIPTION;
    private static final String IMG = "img/card/theDoor.png";
    private static final int BASE_COST = 3;
    private static final int BASE_BLOCK = 20;

    private static final int BASE_MAGIC_NUMBER = 2;
    private static final int UPGRADE_COST =1;
    private static final int UPGRADE_BLOCK = 10;


    public EmergencyDoor(){
        super(ID,NAME,IMG,BASE_COST,DESCRIPTION,CardType.SKILL,
                AbstractCardEnum.DEAD_CELLS,CardRarity.RARE,CardTarget.SELF);


        this.exhaust=true;
        this.baseMagicNumber = BASE_MAGIC_NUMBER;
        this.baseBlock = BASE_BLOCK;

    }
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeBlock(UPGRADE_BLOCK);
            this.upgradeBaseCost(this.cost - UPGRADE_COST);
            this.upgradeName();
        }
    }

    @Override
    public void use(AbstractPlayer paramAbstractPlayer, AbstractMonster paramAbstractMonster) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(paramAbstractPlayer,
                paramAbstractPlayer,new BufferPower(paramAbstractPlayer,this.baseMagicNumber)));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(paramAbstractPlayer,
                paramAbstractPlayer,new WeakPower(paramAbstractPlayer,2,
                false),2));
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(paramAbstractPlayer
                ,paramAbstractPlayer,this.block));
    }

    /*@Override
    public AbstractCard makeCopy(){
        AbstractCard card = new EmergencyDoor();
        if (upgraded){
            card.upgrade();
        }
        return card;
    }*/
}
