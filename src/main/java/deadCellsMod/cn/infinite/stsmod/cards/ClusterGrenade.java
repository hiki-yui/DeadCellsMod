package deadCellsMod.cn.infinite.stsmod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import deadCellsMod.cn.infinite.stsmod.enums.AbstractDeadCellsEnum;
import deadCellsMod.cn.infinite.stsmod.enums.DeadCellsTags;


//集束手雷
public class ClusterGrenade extends GrenadeCard {

    public static final String BASE_ID = "deadCells:ClusterGrenade";
    private static final CardStrings STRINGS = CardCrawlGame.languagePack.getCardStrings(BASE_ID);

    public ClusterGrenade(){
        super(BASE_ID,STRINGS.NAME,"img/card/ClusterGrenade.png",2,STRINGS.DESCRIPTION,CardType.ATTACK, AbstractDeadCellsEnum.DEAD_CELLS,CardRarity.RARE,CardTarget.ENEMY);

        this.baseDamage = 10;
        this.magicNumber = this.baseMagicNumber = 5;
        this.changeNum = this.baseChangeNum = 5;
        this.exhaust = true;
        this.tags.add(DeadCellsTags.GRENADE);
    }

    @Override
    public void upgrade() {
        if (!this.upgraded){
            this.upgradeDamage(4);
            this.upgradeName();
        }
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new DamageAction(abstractMonster,new DamageInfo(abstractPlayer,this.damage), AbstractGameAction.AttackEffect.BLUNT_HEAVY));

        for (int i = 0;i<this.magicNumber;i++){
            addToBot(new DamageRandomEnemyAction(new DamageInfo(abstractPlayer,this.changeNum), AbstractGameAction.AttackEffect.FIRE));
        }
    }
}
