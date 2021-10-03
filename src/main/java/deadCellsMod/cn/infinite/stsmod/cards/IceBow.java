package deadCellsMod.cn.infinite.stsmod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import deadCellsMod.cn.infinite.stsmod.enums.AbstractDeadCellsEnum;
import deadCellsMod.cn.infinite.stsmod.powers.FrostbitePower;

public class IceBow extends DeadCellsCard{

    public static final String BASE_ID = "deadCells:IceBow";
    private static final CardStrings STRINGS = CardCrawlGame.languagePack.getCardStrings(BASE_ID);

    public IceBow(){
        super(BASE_ID,STRINGS.NAME,"img/card/IceBow.png",1,STRINGS.DESCRIPTION,CardType.ATTACK, AbstractDeadCellsEnum.DEAD_CELLS,CardRarity.UNCOMMON,CardTarget.ENEMY);

        this.magicNumber = this.baseMagicNumber = 4;
        this.baseDamage = 4;
        this.baseChangeNum = this.changeNum = 1;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded){
            this.upgradeDamage(2);
            this.upgradeMagicNumber(1);
            this.upgradeName();
        }
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new DamageAction(abstractMonster,new DamageInfo(abstractPlayer,this.damage), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        addToBot(new ApplyPowerAction(abstractMonster,abstractPlayer,new FrostbitePower(abstractMonster,abstractPlayer,this.magicNumber),this.magicNumber));
        addToBot(new DiscardAction(abstractPlayer,abstractPlayer,1,true));
    }
}
