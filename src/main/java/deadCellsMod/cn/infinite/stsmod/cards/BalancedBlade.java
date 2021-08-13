package deadCellsMod.cn.infinite.stsmod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ModifyDamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import deadCellsMod.cn.infinite.stsmod.enums.AbstractDeadCellsEnum;

//均衡之刃
public class BalancedBlade extends DeadCellsCard {

    public static final String BASE_ID = "deadCells:BalancedBlade";
    private static final CardStrings STRINGS = CardCrawlGame.languagePack.getCardStrings(BASE_ID);
    private static final String IMG = "img/card/BalancedBlade.png";
    private static final int MAGIC_NUM_UP_NUM = 1;

    public BalancedBlade(){
        super(BASE_ID,STRINGS.NAME,IMG,1,STRINGS.DESCRIPTION, CardType.ATTACK, AbstractDeadCellsEnum.DEAD_CELLS,CardRarity.UNCOMMON,CardTarget.ENEMY);

        this.magicNumber = this.baseMagicNumber = 3;
        this.baseDamage = 9;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded){
            this.upgradeMagicNumber(2);
            this.upgradeName();
        }
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new DamageAction(abstractMonster,new DamageInfo(abstractPlayer,damage,this.damageTypeForTurn), AbstractGameAction.AttackEffect.LIGHTNING,true));
        addToBot(new ModifyDamageAction(this.uuid,this.magicNumber));
        this.baseMagicNumber += MAGIC_NUM_UP_NUM;
    }

}
