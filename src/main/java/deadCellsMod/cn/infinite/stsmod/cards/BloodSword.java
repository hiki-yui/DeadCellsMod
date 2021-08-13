package deadCellsMod.cn.infinite.stsmod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import deadCellsMod.cn.infinite.stsmod.action.GainBleedingPowerAction;
import deadCellsMod.cn.infinite.stsmod.enums.AbstractDeadCellsEnum;

//血之刃
public class BloodSword extends DeadCellsCard{

    public static final String BASE_ID = "deadCells:BloodSword";
    private static final CardStrings STRINGS = CardCrawlGame.languagePack.getCardStrings(BASE_ID);
    private static final String IMG = "img/card/BloodSword.png";

    public BloodSword(){
        super(BASE_ID,STRINGS.NAME,IMG,1,STRINGS.DESCRIPTION,CardType.ATTACK, AbstractDeadCellsEnum.DEAD_CELLS,CardRarity.COMMON,CardTarget.ENEMY);

        this.baseDamage = 6;
        this.magicNumber = this.baseMagicNumber = this.baseDamage;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded){
            this.upgradeDamage(3);
            this.upgradeMagicNumber(3);
            this.upgradeName();
        }
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new DamageAction(abstractMonster,new DamageInfo(abstractPlayer,this.damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        addToBot(new GainBleedingPowerAction(abstractPlayer,abstractMonster,this.magicNumber));
    }
}
