package deadCellsMod.cn.infinite.stsmod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import deadCellsMod.cn.infinite.stsmod.action.GainBurnsPowerAction;
import deadCellsMod.cn.infinite.stsmod.enums.AbstractCardEnum;
import deadCellsMod.cn.infinite.stsmod.powers.BurnsPower;

//火把
public class Torch extends DeadCellsCard{

    public static final String BASE_ID = "deadCells:Torch";
    private static final CardStrings STRINGS = CardCrawlGame.languagePack.getCardStrings(BASE_ID);
    private static final String IMG = "img/card/Torch.png";

    public Torch(){
        super(BASE_ID,STRINGS.NAME,IMG,1,STRINGS.DESCRIPTION,CardType.ATTACK, AbstractCardEnum.DEAD_CELLS,CardRarity.COMMON,CardTarget.ENEMY);

        this.baseDamage = 5;
        this.magicNumber = this.baseMagicNumber = 6;
        this.burnNumber = this.baseBurnNumber = 3;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded){
            this.upgradeMagicNumber(3);
            this.upgradeName();
        }
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new DamageAction(abstractMonster,new DamageInfo(abstractPlayer,this.damage), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        if (abstractMonster.hasPower("deadCells:BurnsPower")){
            addToBot(new GainBurnsPowerAction(abstractMonster,abstractPlayer,this.magicNumber+this.burnNumber));
        }else{
            addToBot(new GainBurnsPowerAction(abstractMonster,abstractPlayer,this.magicNumber));
        }
    }
}
