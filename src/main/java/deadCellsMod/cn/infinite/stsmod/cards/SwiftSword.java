package deadCellsMod.cn.infinite.stsmod.cards;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import deadCellsMod.cn.infinite.stsmod.enums.AbstractCardEnum;


public class SwiftSword extends DeadCellsCard {

    public static final String BASE_ID = "deadCells:SwiftSword";
    private static final CardStrings STRINGS = CardCrawlGame.languagePack.getCardStrings(BASE_ID);
    private static final String IMG = "img/card/SwiftSword.png";
    private int turnCounter = 0;

    public SwiftSword(){
        super(BASE_ID,STRINGS.NAME,IMG,1,STRINGS.DESCRIPTION,CardType.ATTACK, AbstractCardEnum.DEAD_CELLS,CardRarity.UNCOMMON,CardTarget.SELF_AND_ENEMY);


        this.baseDamage = 8;
        this.magicNumber = this.baseMagicNumber = 2;
        this.changeNum = this.baseChangeNum = 3;
    }


    @Override
    public void upgrade() {
        if (!this.upgraded){
            this.upgradeMagicNumber(1);
            this.upgradeChangeNum(1);
            this.upgradeName();
        }
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new DamageAction(abstractMonster,new DamageInfo(abstractPlayer,this.damage), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        if(turnCounter<=changeNum){
            addToBot(new DrawCardAction(this.magicNumber));
        }
    }

    @Override
    public void atTurnStart() {
        turnCounter++;
        super.atTurnStart();
    }

    @Override
    public void triggerOnGlowCheck() {
        this.glowColor = this.turnCounter<=this.changeNum? AbstractCard.GOLD_BORDER_GLOW_COLOR : AbstractCard.BLUE_BORDER_GLOW_COLOR;
    }

}
