package deadCellsMod.cn.infinite.stsmod.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerToRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PoisonPower;
import deadCellsMod.cn.infinite.stsmod.enums.AbstractCardEnum;

import java.util.Random;

public class AlchemyCarbine extends DeadCellsCard {

    public static final String ID = "deadCells:AlchemyCarbine";
    private static final CardStrings strings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = strings.NAME;
    private static final String DESCRIPTION = strings.DESCRIPTION;
    private static final String IMG = "img/card/AlchemyCarbine.png";
    private static final int UPGRADE_M = 2;
    private static final int UPGRADE_DRAM = 1;

    public AlchemyCarbine(){
        super(ID,NAME,IMG,1,DESCRIPTION,CardType.SKILL, AbstractCardEnum.DEAD_CELLS,
        CardRarity.COMMON,CardTarget.SELF);



        this.baseMagicNumber = 3;
        this.baseDraw = 2;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeMagicNumber(UPGRADE_M);
            this.baseDraw += UPGRADE_DRAM;
            this.rawDescription = strings.UPGRADE_DESCRIPTION;
            this.upgradeName();
            this.initializeDescription();
        }
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        AbstractDungeon.actionManager.addToBottom(new SFXAction("deadCells:ALCHEMY_CARBINE"));
        for (int i=0;i<baseMagicNumber;i++){
            //stackAmount是能力层数
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerToRandomEnemyAction(abstractPlayer,
                    new PoisonPower(abstractMonster,abstractPlayer, 1),1,true, AbstractGameAction.AttackEffect.POISON));
        }
        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(this.baseDraw));
    }
   /* @Override
    public AbstractCard makeCopy(){
        AbstractCard card = new AlchemyCarbine();
        if (upgraded){
            card.upgrade();
        }
        return card;
    }*/
}
