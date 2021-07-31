package deadCellsMod.cn.infinite.stsmod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import deadCellsMod.cn.infinite.stsmod.enums.AbstractCardEnum;

//魔法飞弹
public class MagicMissiles extends DeadCellsCard{

    public static final String BASE_ID = "deadCells:MagicMissiles";
    private static final CardStrings STRINGS = CardCrawlGame.languagePack.getCardStrings(BASE_ID);
    private static final String IMG = "img/card/MagicMissiles.png";

    public MagicMissiles(){
        super(BASE_ID,STRINGS.NAME,IMG,1,STRINGS.DESCRIPTION,CardType.ATTACK, AbstractCardEnum.DEAD_CELLS,CardRarity.COMMON,CardTarget.ALL_ENEMY);

        this.magicNumber = this.baseMagicNumber = 7;
        this.baseDamage = 1;
        this.damageType = DamageInfo.DamageType.NORMAL;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded){
            this.upgradeDamage(1);
            this.upgradeName();
        }
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        for (int i = 0;i<this.magicNumber;i++){
            addToBot(new SFXAction("deadCells:MAGIC_MISSILES"));
            addToBot(new DamageRandomEnemyAction(new DamageInfo(abstractPlayer,this.damage,this.damageType), AbstractGameAction.AttackEffect.FIRE));
        }
    }
}
